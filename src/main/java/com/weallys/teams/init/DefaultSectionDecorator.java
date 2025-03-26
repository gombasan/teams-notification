package com.weallys.teams.init;

import com.weallys.teams.domain.SectionDecorator;

import lombok.Getter;

@Getter
public class DefaultSectionDecorator extends SectionDecorator {

	public DefaultSectionDecorator(String key, String value) {
		super(key, value);
	}

	@Override
	public String decorate() {
		return "- **" + getKey() + " :** " + getValue();
	}
}
