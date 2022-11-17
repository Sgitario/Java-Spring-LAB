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
	CONTAINS(":>", (b, k, v) -> b.like(k.as(String.class), b.literal("%" + v + "%"))),
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

	public Predicate build(CriteriaBuilder builder, Root<?> entity, String key, String value) {
		Path<Comparable> path = entity.get(key);
		Class<?> valueType = path.getModel().getBindableJavaType();
		Comparable filterValue = value;
		if (isBoolean(valueType)) {
			filterValue = Boolean.valueOf(value);
		} else if (isInteger(valueType)) {
			filterValue = Integer.valueOf(value);
		} else if (isDouble(valueType)) {
			filterValue = Double.valueOf(value);
		} else if (isFloat(valueType)) {
			filterValue = Float.valueOf(value);
		} else if (isLong(valueType)) {
			filterValue = Long.valueOf(value);
		}

		return operation.predicate(builder, path, filterValue);
	}

	static FilterOperation parse(String str) {
		for (FilterOperation filter : FilterOperation.values()) {
			if (StringUtils.equals(str, filter.getOperationName())) {
				return filter;
			}
		}

		throw new WrongFilterException(String.format("Filter operation '%s' not found", str));
	}

	private static boolean isBoolean(Class<?> clazz) {
		return clazz.equals(Boolean.class) || clazz.equals(boolean.class);
	}

	private static boolean isInteger(Class<?> clazz) {
		return clazz.equals(Integer.class) || clazz.equals(int.class);
	}

	private static boolean isLong(Class<?> clazz) {
		return clazz.equals(Long.class) || clazz.equals(long.class);
	}

	private static boolean isDouble(Class<?> clazz) {
		return clazz.equals(Double.class) || clazz.equals(double.class);
	}

	private static boolean isFloat(Class<?> clazz) {
		return clazz.equals(Float.class) || clazz.equals(float.class);
	}

	@FunctionalInterface
	interface FilterPredicateFunction {
		Predicate predicate(CriteriaBuilder builder, Path<Comparable> key, Comparable value);
	}
}