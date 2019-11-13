package com.java.spring.lab.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemCriteria {
	private int page = 0;
	private int size = 25;
	private List<String> filter;
}
