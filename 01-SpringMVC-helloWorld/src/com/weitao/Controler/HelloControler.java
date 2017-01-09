package com.weitao.Controler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class HelloControler implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		ModelAndView mv=new ModelAndView();
		//封装显示在视图中的数据
		mv.addObject("msg","helloWorld");
		mv.setViewName("hello");
		return mv;
	}
}
