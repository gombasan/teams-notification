package com.weallys.teams.config;

import java.util.logging.Logger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import com.weallys.teams.aop.SectionExtractor;
import com.weallys.teams.aop.TeamsSpringAop;
import com.weallys.teams.init.DefaultSectionText;
import com.weallys.teams.TeamsSectionTextFactory;
import com.weallys.teams.init.DefaultTeamsNotification;
import com.weallys.teams.init.TeamsProperties;
import com.weallys.teams.service.TeamsNotification;

@Configuration
@EnableConfigurationProperties(TeamsProperties.class)
@ConditionalOnProperty(prefix = "teams.notification", name = "enabled", havingValue = "true")
public class TeamsNotificationAutoConfigure {

	@Bean
	@ConditionalOnMissingBean
	public TeamsNotification teamsNotification(WebClient.Builder builder, TeamsProperties props) {
		Logger logger = Logger.getLogger(TeamsNotificationAutoConfigure.class.getName());
		logger.info("[TeamsNotification] Bean is being created with baseUrl : " + props.getBaseUrl());
		return new DefaultTeamsNotification(builder, props);
	}

	@Bean
	@ConditionalOnMissingBean
	public TeamsSectionTextFactory sectionTextFactory() {
		return (DefaultSectionText::new);
	}

	@Bean
	@ConditionalOnMissingBean
	public SectionExtractor sectionExtractor(TeamsSectionTextFactory sectionTextFactory) {
		return new SectionExtractor(sectionTextFactory);
	}

	@Bean
	@ConditionalOnMissingBean
	public TeamsSpringAop teamsSpringAop(TeamsNotification teamsNotification, SectionExtractor sectionExtractor) {
		return new TeamsSpringAop(teamsNotification, sectionExtractor);
	}
}
