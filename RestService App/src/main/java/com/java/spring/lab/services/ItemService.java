package com.java.spring.lab.services;

import java.util.List;

import com.java.spring.lab.entities.ItemEntity;
import com.java.spring.lab.model.ItemCriteria;

public interface ItemService {

	List<ItemEntity> getItems(ItemCriteria criteria);

}
