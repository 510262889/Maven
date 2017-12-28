package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CoreFilter implements Filter {
	
	public void destroy() {
		
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String currentUrl = request.getRequestURI();
		String contextPath = request.getContextPath();
		if( currentUrl.equals( contextPath + "/" ) ) response.sendRedirect( request.getContextPath() + "/test/test.action" );
		chain.doFilter( request , response);
	}

	
	public void init( FilterConfig config ) throws ServletException {
		initPath( config );
	}
	
	private void initPath( FilterConfig config ){
		ServletContext context = config.getServletContext();
		String app_path = context.getContextPath();
		String res_path = app_path + "/static";
		String css_path = res_path + "/css";
		String js_path = res_path + "/js";
		context.setAttribute( "APP_PATH" , app_path);
		context.setAttribute( "RES_PATH" , res_path);
		context.setAttribute( "CSS_PATH" , css_path);
		context.setAttribute( "JS_PATH"  , js_path);
	}


}
