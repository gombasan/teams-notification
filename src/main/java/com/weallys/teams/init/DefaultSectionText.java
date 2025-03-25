package com.weallys.teams.init;

import com.weallys.teams.ab.SectionText;

import lombok.Getter;

@Getter
public class DefaultSectionText extends SectionText {
	private final String key;
	private final String value;

	public DefaultSectionText(String key, String value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public String decorate() {
		return "- **" + key + " :** " + value;
	}
}
