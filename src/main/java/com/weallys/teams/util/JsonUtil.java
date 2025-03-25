package com.weallys.teams.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	public static String convertToJson(Object teamsMessage) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(teamsMessage);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
