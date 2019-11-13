package com.java.spring.lab.services.impl;

import com.java.spring.lab.services.ActionService;

public class CustomActionServiceImpl implements ActionService {

	@Override
	public String doAction() {
		return "CustomActionServiceImpl";
	}

}
