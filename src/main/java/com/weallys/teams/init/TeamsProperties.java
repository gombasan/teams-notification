package com.weallys.teams.init;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "teams.notification")
public class TeamsProperties {

	private String baseUrl;
	private String teamsEndPoint;
	private boolean enabled = false;

	public String getBaseUrl() {
		return baseUrl;
	}

	public String getTeamsEndPoint() {
		return teamsEndPoint;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public void setTeamsEndPoint(String teamsEndPoint) {
		this.teamsEndPoint = teamsEndPoint;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
