package com.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;


public class CoreFilter implements Filter {

	private static Log log = LogFactory.getLog( CoreFilter.class );
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		chain.doFilter( request , response);
	}

	@Override
	public void init( FilterConfig config ) throws ServletException {
		// 初始化常用路径
		initPath( config );
	}
	
	// 初始化常用路径
	private void initPath( FilterConfig config ){
		ServletContext context = config.getServletContext();
		// 应用路径
		String app_path = context.getContextPath();
		// 资源路径
		String res_path = app_path + "/views";
		// 样式文件路径
		String css_path = res_path + "/css";
		// 脚本文件路径
		String js_path = res_path + "/js";
		context.setAttribute( "APP_PATH" , app_path);
		context.setAttribute( "RES_PATH" , res_path);
		context.setAttribute( "CSS_PATH" , css_path);
		context.setAttribute( "JS_PATH"  , js_path);
	}


}
