package com.java.spring.lab.data.filtering;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

public enum FilterOperation {
	// @formatter:off
	LESS_EQUAL_THAN("<=", (b, k, v) -> b.lessThanOrEqualTo(k, v)),
	GREATER_EQUAL_THAN(">=", (b, k, v) -> b.greaterThanOrEqualTo(k, v)),
	CONTAINS(":>", (b, k, v) -> b.like(k, b.literal("%" + v + "%"))),
	GREATER_THAN(">", (b, k, v) -> b.greaterThan(k, v)), LESS_THAN("<", (b, k, v) -> b.lessThan(k, v)),
	EQUALS("::", (b, k, v) -> b.equal(k, v));
	// @formatter:on

	private final String operationName;
	private final FilterPredicateFunction operation;

	FilterOperation(String operationName, FilterPredicateFunction operation) {
		this.operationName = operationName;
		this.operation = operation;
	}

	public String getOperationName() {
		return operationName;
	}

	public Predicate build(CriteriaBuilder builder, Root<?> entity, String key, Object value) {
		return operation.predicate(builder, entity.get(key), value.toString());
	}

	static FilterOperation parse(String str) {
		for (FilterOperation filter : FilterOperation.values()) {
			if (StringUtils.equals(str, filter.getOperationName())) {
				return filter;
			}
		}

		throw new WrongFilterException(String.format("Filter operation '%s' not found", str));
	}

	@FunctionalInterface
	interface FilterPredicateFunction {
		Predicate predicate(CriteriaBuilder builder, Path<String> key, String value);
	}
}