package com.java.spring.lab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.spring.lab.services.Service;

@Controller
public class ServiceController {
	
	@Autowired
	private Service service;
	
	@RequestMapping("/service")
	public @ResponseBody String callService() {
		return service.doAction();
	}
}
