package com.weallys.teams.domain;

public abstract class SectionDecorator {

	private final String key;
	private final String value;

	protected SectionDecorator(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public abstract String decorate();
}
