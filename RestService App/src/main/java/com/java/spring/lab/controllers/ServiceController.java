package com.java.spring.lab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.spring.lab.services.ActionService;

@RestController
public class ServiceController {

	@Autowired
	private ActionService service;

	@GetMapping("/service")
	public @ResponseBody String callService() {
		return service.doAction();
	}
}
