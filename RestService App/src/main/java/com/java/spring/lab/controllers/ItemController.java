package com.java.spring.lab.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.spring.lab.entities.ItemEntity;
import com.java.spring.lab.model.ItemCriteria;
import com.java.spring.lab.services.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {

	private final ItemService itemService;

	@Autowired
	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}

	@GetMapping
	public List<ItemEntity> listItems(ItemCriteria criteria) {
		return itemService.getItems(criteria);
	}
}