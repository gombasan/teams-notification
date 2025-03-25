package com.weallys.teams;

import com.weallys.teams.ab.SectionText;

@FunctionalInterface
public interface TeamsSectionTextFactory {
	SectionText create(String title, String value);
}
