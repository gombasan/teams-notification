package com.weallys.teams.service;

import com.weallys.teams.domain.TeamsMessage;

public interface TeamsNotification {
	void sendToMessage(TeamsMessage message);
}
