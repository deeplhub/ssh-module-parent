package com.xh.ssh.web.test.task;

import java.lang.reflect.Method;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.xh.ssh.web.mapper.model.WebTask;

/**
 * <b>Title: </b>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2018年9月6日
 */
public class SchedulerManage {

	public static void main(String[] args) throws Exception {
		WebTask task = new WebTask();
		task.setTaskId(1);
		task.setTaskName("AAA");

		SchedulerFactory schedulerFactory = new StdSchedulerFactory();

		
		Object target = new MyTaskSericeImpl();
		if (target == null) {
			return;
		}
		
		Class<?> clazz = Class.forName(target.getClass().getName());
		// 把触发器需要调用的方法找出来，还是用反射
		Method method = clazz.getMethod("exec", WebTask.class);

		String taskId = "100001";

		// 拿到Quartz中的调度器
		Scheduler scheduler = schedulerFactory.getScheduler();

		// 创建一个 JobDetail
		// 任务名，任务组，任务执行类
		JobDetail taskDetail = JobBuilder.newJob(TriggerQuartzJobProxy.class)//
				.withIdentity(taskId, "MyTask")//
				.build();

		// 触发器
		// 触发器名,触发器组
		Trigger trigger = TriggerBuilder.newTrigger()//
				.withIdentity(taskId, "MyTask")//
				.withSchedule(CronScheduleBuilder.cronSchedule("*/2 * * * * ?"))//
				.build();

		// JobDataMap 用来存储附加信息
		// 利用这么一个API，把自定义的信息添加到Map中
		trigger.getJobDataMap().put(TriggerQuartzJobProxy.TARGET, target);
		trigger.getJobDataMap().put(TriggerQuartzJobProxy.TRIGGER, method);
		trigger.getJobDataMap().put(TriggerQuartzJobProxy.PARAMS, new Object[] { task });
		scheduler.scheduleJob(taskDetail, trigger);

		// 如果这个任务没有被主动关闭，就启动
		if (!scheduler.isShutdown()) {
			scheduler.start();
		}
	}

}
