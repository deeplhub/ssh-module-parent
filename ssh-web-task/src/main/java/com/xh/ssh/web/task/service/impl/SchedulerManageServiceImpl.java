package com.xh.ssh.web.task.service.impl;

import java.lang.reflect.Method;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.xh.ssh.web.common.tool.LogTool;
import com.xh.ssh.web.common.tool.SpringTool;
import com.xh.ssh.web.mapper.dao.WebTaskDao;
import com.xh.ssh.web.mapper.model.WebTask;
import com.xh.ssh.web.task.service.IDoTaskService;
import com.xh.ssh.web.task.service.ISchedulerManageService;
import com.xh.ssh.web.task.tool.TaskPoolTool;

/**
 * <b>Title: 定时器管理</b>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2018年9月4日
 */
@Service
public class SchedulerManageServiceImpl implements ISchedulerManageService {

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	@Autowired
	private WebTaskDao webTaskDao;

	@Override
	public int deployTask(WebTask task) {
		int flag = 1;
		try {
			// 执行次数到达计划执行次数，不再部署
			if (task.getPlanExec() > 0) {
				if (task.getExecuted().intValue() >= task.getPlanExec().intValue() || task.getStatus() == 2) {
					flag = 2;
					return flag;
				}
			}

			Object target = SpringTool.getSpringBean(task.getTaskClass());
			if (target == null) {
				return 0;
			}

			Class<?> clazz = Class.forName(target.getClass().getName());
			// 把触发器需要调用的方法找出来，还是用反射
			Method method = clazz.getMethod("execute", WebTask.class);
			String taskId = String.valueOf(task.getTaskId());

			// 拿到Quartz中的调度器
			Scheduler scheduler = schedulerFactoryBean.getScheduler();

			// 创建一个 JobDetail
			JobDetail taskDetail = JobBuilder.newJob(TriggerQuartzJobProxy.class)// 任务名，任务组，任务执行类
					.withIdentity(JobKey.jobKey(taskId, task.getTaskClass()))//
					.build();

			// 触发器
			CronTrigger cronTrigger = TriggerBuilder.newTrigger()// 触发器名,触发器组
					.withIdentity(TriggerKey.triggerKey(taskId, "trigger"))//
					.withSchedule(CronScheduleBuilder.cronSchedule(task.getCron()))//
					.build();

			// JobDataMap 用来存储附加信息
			// 利用这么一个API，把自定义的信息添加到Map中
			cronTrigger.getJobDataMap().put(TriggerQuartzJobProxy.TARGET, target);
			cronTrigger.getJobDataMap().put(TriggerQuartzJobProxy.TRIGGER, method);
			cronTrigger.getJobDataMap().put(TriggerQuartzJobProxy.PARAMS, new Object[] { task });

			scheduler.scheduleJob(taskDetail, cronTrigger);

			// 如果这个任务没有被主动关闭，就启动
			if (!scheduler.isShutdown()) {
				scheduler.start();
			}

			// 放入任务池
			boolean result = TaskPoolTool.containsKey(String.valueOf(task.getTaskId()));
			if (!result) {
				TaskPoolTool.put(String.valueOf(task.getTaskId()), task);
			}

		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | SchedulerException e) {
			LogTool.error(this.getClass(), e);
			flag = 0;
		}

		return flag;
	}

	@Override
	public int modifyTaskCron(String taskId, String cron) {
		int flag = 1;
		WebTask task = TaskPoolTool.get(taskId);
		try {
			Scheduler sched = schedulerFactoryBean.getScheduler();
			CronTrigger trigger = (CronTrigger) sched.getTrigger(TriggerKey.triggerKey(taskId, task.getTaskClass()));
			String oldTime = trigger.getCronExpression();
			if (!oldTime.equalsIgnoreCase(cron)) {
				this.undeployTask(taskId);
				task.setCron(cron);
				this.deployTask(task);
			}
		} catch (Exception e) {
			LogTool.error(this.getClass(), e);
			flag = 0;
		}
		return flag;
	}

	@Override
	public int undeployTask(String taskId) {
		int flag = 1;
		try {
			WebTask task = TaskPoolTool.get(taskId);
			if (task == null) {
				return 0;
			}
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			scheduler.pauseTrigger(TriggerKey.triggerKey(taskId, task.getTaskClass()));// 停止触发器
			scheduler.unscheduleJob(TriggerKey.triggerKey(taskId, task.getTaskClass()));// 移除触发器
			scheduler.deleteJob(JobKey.jobKey(taskId, task.getTaskClass()));// 删除任务

			TaskPoolTool.remove(taskId);
		} catch (Exception e) {
			LogTool.error(this.getClass(), e);
			flag = 0;
		}
		return flag;
	}

	@Override
	public int execTask(WebTask task) {
		int flag = 1;
		try {
			LogTool.debug(this.getClass(), "execute: " + task.toString());
			IDoTaskService doTaskService = SpringTool.getSpringBean(task.getTaskClass());
			doTaskService.execute(task);
		} catch (Exception e) {
			LogTool.error(this.getClass(), task, e);
			flag = 0;
		}
		return flag;
	}

	@Override
	public int restartTask(String taskId) {
		int flag = 1;
		try {
			WebTask task = TaskPoolTool.get(taskId);
			Scheduler sched = schedulerFactoryBean.getScheduler();
			// 重启触发器
			sched.pauseTrigger(TriggerKey.triggerKey(taskId, task.getTaskClass()));
		} catch (Exception e) {
			LogTool.error(this.getClass(), e);
			flag = 0;
		}
		return flag;
	}

	@Override
	public int pauseTask(String taskId) {
		int flag = 1;
		try {
			WebTask task = TaskPoolTool.get(taskId);
			Scheduler sched = schedulerFactoryBean.getScheduler();
			// 停止触发器
			sched.pauseTrigger(TriggerKey.triggerKey(taskId, task.getTaskClass()));
		} catch (Exception e) {
			LogTool.error(this.getClass(), e);
			flag = 0;
		}
		return flag;
	}

	@Override
	public int shutdownTask(String taskId) {
		int flag = 1;
		try {
			WebTask task = TaskPoolTool.get(taskId);
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			scheduler.pauseTrigger(TriggerKey.triggerKey(taskId, task.getTaskClass()));// 停止触发器
			scheduler.unscheduleJob(TriggerKey.triggerKey(taskId, task.getTaskClass()));// 移除触发器
			scheduler.deleteJob(JobKey.jobKey(taskId, task.getTaskClass()));// 删除任务
		} catch (Exception e) {
			LogTool.error(this.getClass(), e);
			flag = 0;
		}
		return flag;
	}

	@Override
	public int startAllTask() {
		int flag = 1;
		try {
			Scheduler sched = schedulerFactoryBean.getScheduler();
			sched.start();
		} catch (Exception e) {
			LogTool.error(this.getClass(), e);
			flag = 0;
		}
		return flag;
	}

	@Override
	public int shutdownAllTask() {
		int flag = 1;
		try {
			Scheduler sched = schedulerFactoryBean.getScheduler();
			if (!sched.isShutdown()) {
				sched.shutdown();
			}
		} catch (Exception e) {
			LogTool.error(this.getClass(), e);
			flag = 0;
		}
		return flag;
	}

	@Override
	public void updateWebTask(WebTask entity) {
		webTaskDao.updateObject(entity);
	}

}
