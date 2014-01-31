package com.java.spring.lab.services.impl;

import com.java.spring.lab.services.Service;

@org.springframework.stereotype.Service
public class NameService implements Service {

	public String doAction() {
		return "NameService";
	}

}
