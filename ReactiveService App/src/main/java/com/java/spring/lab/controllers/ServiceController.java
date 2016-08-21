package com.java.spring.lab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.java.spring.lab.services.Service;

import reactor.core.publisher.Flux;

@Controller
public class ServiceController {

	@Autowired
	private Service service;

	@GetMapping("/service")
	public Flux<String> callService() {
		return Flux.range(1, 100).delayMillis(2000).map(i -> service.doAction() + Math.random());
	}
}
