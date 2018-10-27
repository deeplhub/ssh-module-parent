package com.xh.ssh.web.task.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.xh.ssh.web.common.exception.ResultException;
import com.xh.ssh.web.common.tool.Assert;
import com.xh.ssh.web.common.tool.DateFormatTool;
import com.xh.ssh.web.common.tool.JedisClientTool;
import com.xh.ssh.web.common.tool.LogTool;
import com.xh.ssh.web.mapper.model.WebTask;
import com.xh.ssh.web.service.task.service.IWebTaskService;
import com.xh.ssh.web.task.service.ISchedulerManageService;
import com.xh.ssh.web.task.tool.TaskPoolTool;

/**
 * <b>Title: 任务管理</b>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2018年9月4日
 */
@Controller
public class WebTaskController extends BaseController {

	@Autowired
	private IWebTaskService taskService;
	@Autowired
	private ISchedulerManageService schedulerManageService;

	@RequestMapping("index")
	public String index() {

		return "index";
	}

	@RequestMapping("index2")
	public String index2(HttpServletRequest request, HttpServletResponse response) {

		return "index";
	}

	@RequestMapping("query")
	@ResponseBody
	public String query() {
		List<WebTask> list = taskService.loadAll(WebTask.class);

		return JSON.toJSONString(list);
	}

	/**
	 * <b>Title: 执行</b>
	 * <p>Description: </p>
	 * 
	 * @author H.Yang
	 * 
	 * @param taskId
	 */
	@RequestMapping("execute")
	@ResponseBody
	public Object execute(String taskId) {
		Assert.isBlank(taskId, "任务ID不能为空！");
		boolean isRepeatQuest = false;
		String time = DateFormatTool.parseDateToString(new Date(), DateFormatTool.DATA_FORMATTER3_3);
		String key = this.getClass().getSimpleName() + "_" + taskId;
		try {
			if (JedisClientTool.isExist(key, time)) {
				isRepeatQuest = true;
				throw new ResultException("请求正在处理中，请勿重复提交");
			}
			JedisClientTool.expire(key, 300);

			WebTask task = TaskPoolTool.get(taskId);
			if (task == null) {
				task = (WebTask) taskService.load(WebTask.class, Integer.valueOf(taskId));
			}

			Assert.isNull(task, "未知的任务ID");

			int flag = schedulerManageService.execTask(task);
			LogTool.debug(this.getClass(), "执行结果" + flag);
			if (flag == 1) {
				return super.renderSuccess();
			}
		} finally {
			if (!isRepeatQuest) {
				JedisClientTool.del(key);
			}
		}

		return super.renderError("任务：" + taskId + " - 执行失败");

	}

	/**
	 * <b>Title: 部署</b>
	 * <p>Description: </p>
	 * 
	 * @author H.Yang
	 * 
	 * @param taskId
	 */
	@RequestMapping("deploy")
	@ResponseBody
	public Object deploy(String taskId) {
		Assert.isBlank(taskId, "任务ID不能为空！");
		boolean isRepeatQuest = false;
		String time = DateFormatTool.parseDateToString(new Date(), DateFormatTool.DATA_FORMATTER3_3);
		String key = this.getClass().getSimpleName() + "_" + taskId;

		try {
			if (JedisClientTool.isExist(key, time)) {
				isRepeatQuest = true;
				throw new ResultException("请求正在处理中，请勿重复提交");
			}
			WebTask task = (WebTask) taskService.load(WebTask.class, Integer.valueOf(taskId));

			Assert.isNull(task, "未知的任务ID");
			int flag = schedulerManageService.deployTask(task);
			LogTool.debug(this.getClass(), flag);
			if (flag == 1) {
				return super.renderSuccess();
			}
		} finally {
			if (!isRepeatQuest) {
				JedisClientTool.del(key);
			}
		}

		return super.renderError("任务：" + taskId + " 部署失败");
	}
}
