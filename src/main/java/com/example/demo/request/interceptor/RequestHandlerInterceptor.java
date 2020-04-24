package com.example.demo.request.interceptor;

import com.example.demo.exception.InvalidHeaderException;
import com.example.demo.exception.RequiredHeaderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHandlerInterceptor extends HandlerInterceptorAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(RequestHandlerInterceptor.class);

	private static final String START_TIME = "startTime";
	private static final String SYSTEM_NAME = "SYSTEM-NAME";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		LOGGER.info("*********************************** START PRE HANDLER ***********************************");
		long startTime = System.currentTimeMillis();
		request.setAttribute(START_TIME, startTime);
		LOGGER.info("Start time: " + startTime);
		LOGGER.info("*********************************** END PRE HANDLER ***********************************");

		final String systemName = request.getHeader(SYSTEM_NAME);

		if(StringUtils.isEmpty(systemName)){
			throw new RequiredHeaderException(SYSTEM_NAME);
		}

		if(!systemName.equals("demo")){
			throw new InvalidHeaderException(SYSTEM_NAME);
		}

		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		LOGGER.info("*********************************** START POST HANDLER ***********************************");
		LOGGER.info("[preHandle][" + request + "]" + "[" + request.getMethod() + "]" + request.getRequestURI());
		Long startTime = (Long) request.getAttribute(START_TIME);
		LOGGER.info("Time result: " + (System.currentTimeMillis() - startTime));
		LOGGER.info("*********************************** END POST HANDLER ***********************************");
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
}
