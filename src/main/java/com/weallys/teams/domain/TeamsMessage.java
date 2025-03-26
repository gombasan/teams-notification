package com.weallys.teams.domain;

import java.util.List;
import java.util.Objects;

import lombok.Getter;
import lombok.ToString;

@Getter
public final class TeamsMessage {
	private final String title;
	private final String text;
	private final String themeColor;
	private final List<Section> sections;

	public static TeamsMessage from(TeamsContent content) {
		return new TeamsMessage(
			content.getTitle(),
			content.getMessage(),
			"0076d7",
			Section.from(content.getSections())
		);
	}

	private TeamsMessage(String title, String text, String themeColor, List<Section> sections) {
		this.title = title;
		this.text = text;
		this.themeColor = themeColor;
		this.sections = sections;
	}

	@ToString
	@Getter
	static class Section {
		private final String activityTitle;
		private final String text;

		private Section(String activityTitle, String text) {
			this.activityTitle = activityTitle;
			this.text = text;
		}

		private static List<Section> from(List<TeamsSection> sections) {
			return sections.stream()
				.map(Section::from)
				.toList();
		}

		private static Section from(TeamsSection section) {
			if(Objects.isNull(section)) return null;
			return new Section(section.getTitle(), section.assembleText());
		}
	}
}
