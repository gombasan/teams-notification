package com.weallys.teams.domain;

import java.util.ArrayList;
import java.util.List;

public class TeamsSection {
	private final String title;
	private final List<SectionDecorator> texts = new ArrayList<>();

	public TeamsSection(String title) {
		this.title = title;
	}

	public void addText(SectionDecorator text) {
		texts.add(text);
	}

	public String assembleText() {
		List<String> stringList = texts.stream()
			.map(SectionDecorator::decorate)
			.toList();

		return String.join("\n", stringList);
	}

	public String getTitle() {
		return title;
	}

	public List<SectionDecorator> getTexts() {
		return texts;
	}
}
