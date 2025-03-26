package com.weallys.teams.init;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.weallys.teams.domain.TeamsMessage;
import com.weallys.teams.service.TeamsNotification;
import com.weallys.teams.util.JsonUtil;

import reactor.core.publisher.Mono;

public class DefaultTeamsNotification implements TeamsNotification {

	private final WebClient webClient;
	private final TeamsProperties properties;

	public DefaultTeamsNotification(WebClient.Builder builder, TeamsProperties props) {
		this.webClient = builder.baseUrl(props.getBaseUrl()).build();
		this.properties = props;
	}

	@Override
	public void sendToMessage(TeamsMessage message) {
		String jsonMessage = JsonUtil.convertToJson(message);

		webClient.post()
			.uri(properties.getTeamsEndPoint())
			.contentType(MediaType.APPLICATION_JSON)
			.bodyValue(jsonMessage)
			.retrieve()
			.onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
				response -> Mono.error(new RuntimeException("Teams message sending failed. Check if the base URL or endpoint is correct.")))
			.bodyToMono(String.class).subscribe();
	}
}
