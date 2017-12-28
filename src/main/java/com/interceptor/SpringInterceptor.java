package com.interceptor;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.common.RequestAnalysis;
import com.springMvc.annotation.Json;
import com.springMvc.annotation.JspView;
import com.springMvc.annotation.XmlView;
import com.util.ObjectUtil;

/**
 * @author ykzhu
 * 
 * spring拦截器
 */
public class SpringInterceptor implements HandlerInterceptor  {
	
	private static final Class<Json> JSON_CLASS = Json.class;
	private static final Class<XmlView> XMLVIEW_CLASS = XmlView.class;
	private static final Class<JspView> JSPVIEW_CLASS = JspView.class;
	private static RequestAnalysis analysis = RequestAnalysis.newInstance();

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response,Object handler, ModelAndView modelAndView) throws Exception {
		if( handler instanceof HandlerMethod ){
			HandlerMethod hm = (HandlerMethod) handler;  
            Class<?> clazz = hm.getBeanType();  
            Method m = hm.getMethod();  
            if ( clazz != null && m != null ) {
            	analysis.checkAnnotation( m.getAnnotation( JSON_CLASS ) , m.getAnnotation( XMLVIEW_CLASS ) , m.getAnnotation( JSPVIEW_CLASS ) );
            	// JSON对象返回
            	if( ObjectUtil.nonNull( m.getAnnotation( JSON_CLASS ) ) ) analysis.handlerJson( request , response , modelAndView );
            	// Xml页面返回
            	if( ObjectUtil.nonNull( m.getAnnotation( XMLVIEW_CLASS ) ) ) analysis.handlerXml(  request , response , modelAndView );
            	// Jsp页面返回
            	if( ObjectUtil.nonNull( m.getAnnotation( JSPVIEW_CLASS ) ) ) analysis.handlerJsp(  request , response , modelAndView );
            }
		}
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception paramException) throws Exception { }
}
