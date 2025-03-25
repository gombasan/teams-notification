package com.weallys.teams.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import com.weallys.teams.init.DefaultSectionText;
import com.weallys.teams.TeamsSectionTextFactory;
import com.weallys.teams.init.DefaultTeamsNotification;
import com.weallys.teams.init.TeamsProperties;
import com.weallys.teams.service.TeamsNotification;

@Configuration
@EnableConfigurationProperties(TeamsProperties.class)
public class TeamsNotificationAutoConfigure {

	@Bean
	@ConditionalOnMissingBean
	public TeamsNotification teamsNotification(WebClient.Builder builder, TeamsProperties props) {
		return new DefaultTeamsNotification(builder, props);
	}

	@Bean
	@ConditionalOnMissingBean
	public TeamsSectionTextFactory sectionTextFactory() {
		return (DefaultSectionText::new);
	}

}
