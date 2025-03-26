package com.weallys.teams.aop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.weallys.teams.TeamsSectionTextFactory;
import com.weallys.teams.domain.TeamsSection;
import com.weallys.teams.annotation.MessageField;

public final class SectionExtractor {

	private final TeamsSectionTextFactory textFactory;

	public SectionExtractor(TeamsSectionTextFactory textFactory) {
		this.textFactory = textFactory;
	}

	public List<TeamsSection> extractSections(Object[] args) {
		List<TeamsSection> sections = new ArrayList<>();

		for (Object arg : args) {
			if(arg == null) continue;

			Class<?> clazz = arg.getClass();
			com.weallys.teams.annotation.TeamsSection annotation = clazz.getAnnotation(
				com.weallys.teams.annotation.TeamsSection.class);

			if(annotation == null) continue;

			String title = annotation.sectionTitle();
			Map<String, String> values = getFieldValues(arg);

			if (!values.isEmpty()) {
				TeamsSection section = new TeamsSection(title);
				values.forEach((k, v) -> section.addText(textFactory.create(k, v)));
				sections.add(section);
			}
		}

		return sections;
	}

	private Map<String, String> getFieldValues(Object obj) {
		return Arrays.stream(obj.getClass().getDeclaredFields())
			.filter(field -> field.isAnnotationPresent(MessageField.class))
			.peek(field -> field.setAccessible(true))
			.map(field -> {
				try {
					Object value = field.get(obj);
					String key = field.getAnnotation(MessageField.class).key();
					return Map.entry(key, value != null ? value.toString() : "");
				} catch (IllegalAccessException e) {
					return null;
				}
			})
			.filter(Objects::nonNull)
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b, LinkedHashMap::new));
	}

}
