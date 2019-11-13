package com.java.spring.lab.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.spring.lab.data.filtering.FindAllBuilder;
import com.java.spring.lab.entities.ItemEntity;
import com.java.spring.lab.model.ItemCriteria;
import com.java.spring.lab.repositories.ItemRepository;
import com.java.spring.lab.services.ItemService;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

	private final ItemRepository repository;

	@Autowired
	public ItemServiceImpl(ItemRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<ItemEntity> getItems(ItemCriteria criteria) {
		// @formatter:off
		return FindAllBuilder.usingRepository(repository).filterBy(criteria.getFilter()).findAll(criteria.getPage(),
				criteria.getSize());
		// @formatter:on
	}
}