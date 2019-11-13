package com.java.spring.lab.services.impl;

import com.java.spring.lab.services.ActionService;

@org.springframework.stereotype.Service
public class PrincipalActionServiceImpl implements ActionService {

	@Override
	public String doAction() {
		return "PrincipalActionServiceImpl";
	}

}
