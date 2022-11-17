package com.java.spring.lab;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.spring.lab.entities.ItemEntity;
import com.java.spring.lab.repositories.ItemRepository;

@Component
public class IngestSampleDataService {

	@Autowired
	private ItemRepository repository;

	@PostConstruct
	public void init() {
		repository.save(item("Sam", "Jaus", false, 10));
		repository.save(item("Jose", "Carvajal", true, 20));
		repository.save(item("Samuel", "Ubu", false, 30));
	}

	private ItemEntity item(String field1, String field2, boolean active, int number) {
		ItemEntity entity = new ItemEntity();
		entity.setField1(field1);
		entity.setField2(field2);
		entity.setActive(active);
		entity.setNumber(number);
		return entity;
	}
}
