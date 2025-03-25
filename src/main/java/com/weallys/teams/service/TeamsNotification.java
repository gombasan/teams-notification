package com.weallys.teams.service;

import com.weallys.teams.ab.TeamsMessage;

public interface TeamsNotification {
	void sendToMessage(TeamsMessage message);
}
