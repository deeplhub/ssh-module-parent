package com.xh.ssh.web.common.scan.impl;

import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Description;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xh.ssh.web.common.annotation.AfterThrowingAspect;
import com.xh.ssh.web.common.annotation.AroundAspect;
import com.xh.ssh.web.common.scan.ILogAspect;
import com.xh.ssh.web.common.tool.LogTool;
import com.xh.ssh.web.mapper.model.WebLog;
import com.xh.ssh.web.service.common.service.IWebLogService;

/**
 * <p>Title: AOP 日志</p>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @date 2018年3月6日
 *
 */
@Aspect
@Component
@Order(999)
public class LogAspectImpl implements ILogAspect {

	private static final String LOG_CONTENT = "[类名]:%s,[方法]:%s,[参数]:%s";
	private static final String[] METHOD_CONTENT = { "insert", "delete", "update", "save", "edit", "remove" };

	@Resource
	private IWebLogService webLogService;

	/**
	 * <b>Title: </b>
	 * <p>Description: 
	 * within(@org.springframework.stereotype.Controller *)匹配指定类型内的任何方法方法执行；
	 * @within(org.springframework.stereotype.Controller)，匹配所以持有指定注解类型内的方法；注解类型也必须是全限定类型名；
	 * @annotation(org.springframework.web.bind.annotation.RequestMapping)，用于匹配当前执行方法持有指定注解的方法；
	 * </p>
	 * 
	 * @author H.Yang
	 * 
	 */
	@Pointcut("@annotation(com.xh.ssh.web.common.annotation.BeforAspect)")
	private void beforAspect() {
	}

	@Pointcut("@annotation(com.xh.ssh.web.common.annotation.AroundAspect)")
	private void aroundAspect() {
	}

	@Pointcut("@annotation(com.xh.ssh.web.common.annotation.AfterAspect)")
	private void afterAspect() {
	}

	@Pointcut("@annotation(com.xh.ssh.web.common.annotation.AfterThrowingAspect)")
	private void afterThrowingAspect() {
	}

	@Before("beforAspect()")
	@Override
	public void before(JoinPoint point) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String methodName = point.getSignature().getName();
		String className = point.getTarget().getClass().getName();
		Object[] params = point.getArgs();
		String message = this.operateContent(className, methodName, params, request);

		LogTool.info(this.getClass(), message);
	}

	@Around("aroundAspect()")
	@Override
	public Object around(ProceedingJoinPoint point) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String className = point.getTarget().getClass().getName();
		String methodName = point.getSignature().getName();
		Object[] params = point.getArgs();
		Method[] methods = point.getTarget().getClass().getMethods();
		Method method = this.currentMethod(methods, methodName);
		AroundAspect log = method.getAnnotation(AroundAspect.class);

		String message = this.operateContent(className, methodName, params, request);
		LogTool.info(this.getClass(), message);

		Object obj = null;
		// 打印日志
		if (log.value()) {
			obj = point.proceed();
			LogTool.info(this.getClass(), obj);
		}
		// 保存日志
		if (log.isSave()) {
			this.saveLog(className, point.getTarget().getClass().getSimpleName(), methodName, params, methods, request, null);
		}
		return obj;
	}

	@After("afterAspect()")
	@Override
	public void after(JoinPoint point) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String className = point.getTarget().getClass().getName();
		String methodName = point.getSignature().getName();
		Object[] params = point.getArgs();
		String message = this.operateContent(className, methodName, params, request);

		LogTool.info(this.getClass(), message);
	}

	@AfterThrowing(value = "afterThrowingAspect()", throwing = "exception")
	@Override
	public void afterThrowing(JoinPoint point, Exception exception) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String methodName = point.getSignature().getName();
		Method[] methods = point.getTarget().getClass().getMethods();
		Method method = this.currentMethod(methods, methodName);
		AfterThrowingAspect log = method.getAnnotation(AfterThrowingAspect.class);

		// 保存日志
		if (log.value()) {
			this.saveLog(point.getTarget().getClass().getName(), point.getTarget().getClass().getSimpleName(), methodName, point.getArgs(),
					methods, request, exception);
		}

	}

	/**
	 * <b>Title: 保存日志到数据库</b>
	 * <p>Description: </p>
	 * 
	 * @author H.Yang
	 * 
	 * @param className
	 * @param simpleName
	 * @param methodName
	 * @param params
	 * @param methods
	 * @param request
	 * @param exception
	 */
	private void saveLog(String className, String simpleName, String methodName, Object[] params, Method[] methods,
			HttpServletRequest request, Exception exception) {

		Method method = this.currentMethod(methods, methodName);
		Description des = method.getAnnotation(Description.class);
		String message = operateContent(className, methodName, params, request);
		WebLog webLog = new WebLog();
		webLog.setMsgCode(simpleName);
		if (exception != null) {
			webLog.setOperateStatus(1);
			webLog.setOperateType("异常");
			webLog.setMsgInfo(this.operateContent(className, methodName, params, request) + "\n" + exception.toString());
			webLogService.save(webLog);
		} else {
			if (isWriteLog(methodName)) {
				webLog.setOperateStatus(1);
				webLog.setOperateType((des != null) ? des.value() : null);
				webLogService.save(webLog);
			}
		}

	}

	/**
	 * 判断是哪些方法可以写入LOG
	 * 
	 * @author Dingdong
	 * @date 2017年5月24日
	 * 
	 * @param method
	 * @return
	 */
	private boolean isWriteLog(String method) {
		boolean falg = false;
		for (String s : LogAspectImpl.METHOD_CONTENT) {
			if (method.indexOf(s) > -1) {
				falg = true;
				break;
			}
		}
		return falg;
	}

	/**
	 * <b>Title: 获取当前执行的方法并判断</b>
	 * <p>Description: </p>
	 * 
	 * @author H.Yang
	 * 
	 * @param point
	 * @param methodName
	 * @return
	 */
	private Method currentMethod(Method[] methods, String methodName) {
		Method resultMethod = null;
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				resultMethod = method;
				break;
			}
		}
		return resultMethod;
	}

	/**
	 * <b>Title: 获取当前传递的参数</b>
	 * <p>Description: </p>
	 * 
	 * @author H.Yang
	 * 
	 * @param className
	 * @param methodName
	 * @param params
	 * @param request
	 * @return
	 */
	private String operateContent(String className, String methodName, Object[] params, HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		if (params != null && params.length > 0) {
			Enumeration<String> paraNames = request.getParameterNames();
			while (paraNames.hasMoreElements()) {
				String key = paraNames.nextElement();
				sb.append(key).append("=");
				sb.append(request.getParameter(key)).append("&");
			}
			if (StringUtils.isBlank(sb.toString())) {
				sb.append(request.getQueryString());
			}
		}
		return String.format(LogAspectImpl.LOG_CONTENT, className, methodName, sb.toString());
	}
}
