package com.weallys.teams;

import com.weallys.teams.domain.SectionDecorator;

@FunctionalInterface
public interface TeamsSectionTextFactory {
	SectionDecorator create(String title, String value);
}
