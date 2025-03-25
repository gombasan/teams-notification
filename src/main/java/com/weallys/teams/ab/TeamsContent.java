package com.weallys.teams.ab;

import java.util.ArrayList;
import java.util.List;

public class TeamsContent {
	private final String title;
	private final String message;
	private final List<TeamsSection> sections = new ArrayList<>();

	public TeamsContent(String title, String message) {
		this.title = title;
		this.message = message;
	}

	public TeamsContent addSection(TeamsSection section) {
		sections.add(section);
		return this;
	}

	public String getTitle() {
		return title;
	}

	public String getMessage() {
		return message;
	}

	public List<TeamsSection> getSections() {
		return sections;
	}
}
