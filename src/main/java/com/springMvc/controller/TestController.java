package com.springMvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springMvc.annotation.Json;
import com.springMvc.annotation.XmlView;
import com.springMvc.dao.HibernateDao;

@Controller
@RequestMapping("/test")
public class TestController {
	@Autowired
	private HibernateDao hibernateDao;
	
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
}
