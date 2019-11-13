package com.java.spring.lab.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.java.spring.lab.entities.ItemEntity;

public interface ItemRepository
		extends PagingAndSortingRepository<ItemEntity, Long>, JpaSpecificationExecutor<ItemEntity> {

}
