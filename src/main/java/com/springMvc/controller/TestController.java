package com.springMvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springMvc.dao.HibernateDao;

@Controller
@RequestMapping("/test")
public class TestController {
	@Autowired
	private HibernateDao hibernateDao ;
	
	@RequestMapping(value = "/test.action")  
	public ModelAndView test( HttpServletRequest request , HttpServletResponse response  ){
		hibernateDao.selectAll();
		ModelAndView modelAndView = new ModelAndView("index.jsp");
		return modelAndView;
	}
}
