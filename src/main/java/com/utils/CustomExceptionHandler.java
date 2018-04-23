package com.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class CustomExceptionHandler implements HandlerExceptionResolver {

	/** 
	 * (方法说明描述) 
	 *
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @return 
	 *
	 * @see org.springframework.web.servlet.HandlerExceptionResolver#resolveException(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)    
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception exception) {

		if (exception instanceof Exception) {
			ModelAndView mv = new ModelAndView("errors/error");
			mv.addObject("exceptionMsg", exception.getMessage());
			return mv;
		}
		return null;
	}

}
