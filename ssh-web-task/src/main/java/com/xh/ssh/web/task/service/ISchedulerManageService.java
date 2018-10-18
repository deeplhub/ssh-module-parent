package com.xh.ssh.web.task.service;

import com.xh.ssh.web.mapper.model.WebTask;

/**
 * <b>Title: </b>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2018年9月4日
 */
public interface ISchedulerManageService {

	/**
	 * 部署作业
	 * @param task
	 * @return
	 */
	public int deployTask(WebTask task);

	/** 
	 * 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名) 
	 *  
	 */
	public int modifyTaskCron(String taskId, String cron);

	/** 
	 * 移除一个任务(使用默认的任务组名，触发器名，触发器组名) 
	 *  
	 */
	public int undeployTask(String taskId);

	/** 
	 * 立即执行
	 *  
	 */
	public int execTask(WebTask task);

	/**
	 * 重启任务
	 * @param taskId
	 * @return
	 */
	public int restartTask(String taskId);

	/**
	 * 暂停定时任务
	 * @param taskId
	 * @return
	 */
	public int pauseTask(String taskId);

	/**
	 * 关闭定时任务
	 * @param taskId
	 * @return
	 */
	public int shutdownTask(String taskId);

	/** 
	 * 启动所有定时任务 
	 *  
	 */
	public int startAllTask();

	/** 
	 * 关闭所有定时任务 
	 */
	public int shutdownAllTask();

	/**
	 * <b>Title: 更新任务</b>
	 * <p>Description: </p>
	 * 
	 * @author H.Yang
	 * 
	 * @param entity
	 */
	public void updateWebTask(WebTask entity);
}
