package com.weallys.teams.ab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TeamsSection {
	private final String title;
	private final List<SectionText> texts = new ArrayList<>();

	public TeamsSection(String title) {
		this.title = title;
	}

	public void addText(SectionText text) {
		texts.add(text);
	}

	public String assembleText() {
		List<String> stringList = texts.stream()
			.map(SectionText::decorate)
			.toList();

		return String.join("\n", stringList);
	}

	public String getTitle() {
		return title;
	}

	public List<SectionText> getTexts() {
		return texts;
	}
}
