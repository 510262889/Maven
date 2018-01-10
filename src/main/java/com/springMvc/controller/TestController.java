package com.springMvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.chart.EchartArea;
import com.springMvc.annotation.Json;
import com.springMvc.annotation.JspView;
import com.springMvc.annotation.XmlView;
import com.springMvc.dao.HibernateDao;

@Controller
@RequestMapping("/test")
public class TestController {
	@Autowired
	private HibernateDao hibernateDao;
	@Autowired
	private EchartArea areaFactory;
	
	@RequestMapping(value = "/test.action")
	@Json
	public Object test( HttpServletRequest request , HttpServletResponse response ){
		return hibernateDao.selectAll();
	}
	
	@RequestMapping(value = "/testXml.action")
	@XmlView
	public Object testXml( HttpServletRequest request , HttpServletResponse response ){
		return "test.xml";
	}
	
	@RequestMapping(value = "/testJsp.action")
	@JspView
	public Object testJsp( HttpServletRequest request , HttpServletResponse response ){
		request.setAttribute( "resultJs" , areaFactory.createChart( request ));
		return "component/demo/demo.jsp";
	}
	
	
}
