package com.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.exception.MyExpection;
import com.springMvc.annotation.Json;
import com.springMvc.annotation.XmlView;
import com.util.JsonUtil;

/**
 * @author ykzhu
 * 
 * spring拦截器
 */
public class SpringInterceptor implements HandlerInterceptor  {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response,Object handler, ModelAndView modelAndView) throws Exception {
		if( handler instanceof HandlerMethod ){
			HandlerMethod hm = (HandlerMethod) handler;  
            Class<?> clazz = hm.getBeanType();  
            Method m = hm.getMethod();  
            if (clazz != null && m != null) { 
            	// 页面返回和JSON对象返回不能同时生效
            	if( m.getAnnotation( Json.class ) != null && m.getAnnotation( XmlView.class ) != null ) throw new MyExpection("不能同时使用json和xmlView两种返回方式");
            	// JSON对象返回
            	if( m.getAnnotation( Json.class ) != null ) handlerJson( request , response , modelAndView );
            	// 页面返回
            	if( m.getAnnotation( XmlView.class ) != null ) handlerXml(  request , response , modelAndView );
            }
		}
	}

	public void afterCompletion(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Object handler, Exception paramException) throws Exception {
		
	}

	/**
	 * 处理Json返回
	 */
	private void handlerJson( HttpServletRequest request, HttpServletResponse response , ModelAndView modelAndView ){
		Map<String, Object> map = modelAndView.getModel();
	    Set<String> keys = map.keySet();
	    Iterator<String> iterator = keys.iterator();
	    StringBuffer buffer = new StringBuffer();
	    while ( iterator.hasNext() ) {
	    	Object object = map.get( iterator.next() );
	    	buffer.append( JsonUtil.toJson( object ) );
	    }
	    modelAndView.clear();
	    
        response.setCharacterEncoding( "utf-8" );
        response.setContentType( "text/html;charset=UTF-8" );
        response.setHeader( "pragma", "no-cache" );
        response.setHeader( "cache-control", "no-cache" );
        PrintWriter writer = null;
        try {
			writer = response.getWriter();
			writer.write( buffer.toString() );
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if( writer != null ) writer.close();
		}
  	}
	
	/**
	 * 处理xml页面返回
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void handlerXml( HttpServletRequest request, HttpServletResponse response,ModelAndView modelAndView ) throws ServletException, IOException{
		
        response.setCharacterEncoding( "utf-8" );
        response.setContentType( "text/html;charset=UTF-8" );
        response.setHeader( "pragma", "no-cache" );
        response.setHeader( "cache-control", "no-cache" );
       
	}
}
