package com.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.exception.MyExpection;
import com.util.JsonUtil;
import com.util.ObjectUtil;

public class RequestAnalysis {
	
	private static RequestAnalysis analysis;
	private RequestAnalysis(){}

	public static RequestAnalysis newInstance(){ if( ObjectUtil.isNull( analysis ) ) analysis = new RequestAnalysis(); return analysis; }
	
	/**
	 * 检测是否存在多个的返回注解
	 * @param object
	 */
	public void checkAnnotation( Object... objects ){
		boolean firstAnnotation = true;
		for( Object object : objects ){ if( ObjectUtil.nonNull( object ) ){ if( firstAnnotation ) firstAnnotation = false; else throw new MyExpection( " 不能使用多种方式返回 " ); } }
	}

	/**
	 * 处理Json返回
	 */
	public void handlerJson( HttpServletRequest request, HttpServletResponse response , ModelAndView modelAndView ){
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
	 * 处理jsp页面返回
	 */
	public void handlerJsp( HttpServletRequest request, HttpServletResponse response,ModelAndView modelAndView ){
        response.setCharacterEncoding( "utf-8" );
        response.setContentType( "text/html;charset=UTF-8" );
        response.setHeader( "pragma", "no-cache" );
        response.setHeader( "cache-control", "no-cache" );
	}
	
	/**
	 * 处理xml页面返回
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void handlerXml( HttpServletRequest request, HttpServletResponse response,ModelAndView modelAndView ) throws ServletException, IOException{
        response.setCharacterEncoding( "utf-8" );
        response.setContentType( "text/html;charset=UTF-8" );
        response.setHeader( "pragma", "no-cache" );
        response.setHeader( "cache-control", "no-cache" );
        
        PrintWriter writer = response.getWriter();
        writer.println( "<html><body><form>用户名：<input type=\"text\">密码：<input type=\"password\"></form></body></html>" );
        writer.close();
        
        
//        String filePath = "/WEB-INF/views/" + modelAndView.getViewName();
//        InputStream fileIps = request.getServletContext().getResourceAsStream(filePath);
//        if( fileIps == null ) throw new MyExpection( "访问的路径不存在" );
//        else //XmlUtil.parserXml( request.getServletContext().getResourceAsStream(filePath) );
	}
	
	
}
