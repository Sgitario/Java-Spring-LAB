package com.java.spring.lab.data.filtering;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public class FindAllBuilder<E, R extends PagingAndSortingRepository<E, ?> & JpaSpecificationExecutor<E>> {

	private final R repository;

	private Specification<E> filters;

	private Sort sort = Sort.unsorted();

	public static <E, R extends PagingAndSortingRepository<E, ?> & JpaSpecificationExecutor<E>> FindAllBuilder<E, R> usingRepository(
			R repository) {
		return new FindAllBuilder<>(repository);
	}

	private FindAllBuilder(R repository) {
		this.repository = repository;
	}

	public List<E> findAll(int page, int limit) {
		return new LinkedList<E>(repository.findAll(filters, PageRequest.of(page, limit, sort)).getContent());
	}

	public FindAllBuilder<E, R> filterBy(List<String> listFilters) {
		Optional<Specification<E>> opFilters = EntitySpecificationBuilder.parse(listFilters);
		if (opFilters.isPresent()) {
			if (filters == null) {
				filters = Specification.where(opFilters.get());
			} else {
				filters = filters.and(opFilters.get());
			}
		}

		return this;
	}

	public FindAllBuilder<E, R> sortBy(String orderBy, String orderDir) {
		if (StringUtils.isNotEmpty(orderBy)) {
			sort = Sort.by(Direction.fromOptionalString(orderDir).orElse(Direction.ASC), orderBy);
		}

		return this;
	}
}