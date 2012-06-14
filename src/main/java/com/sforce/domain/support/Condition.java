package com.sforce.domain.support;

public interface Condition {
	public String toSqlString();
	public ConditionEnum getConditionEnum();
}
