package com.weallys.teams.aop;

import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.weallys.teams.ab.TeamsContent;
import com.weallys.teams.ab.TeamsMessage;
import com.weallys.teams.ab.TeamsSection;
import com.weallys.teams.annotation.TeamsEvent;
import com.weallys.teams.service.TeamsNotification;

@Aspect
public class TeamsSpringAop {

	private final TeamsNotification teamsNotification;
	private final SectionExtractor sectionExtractor;

	public TeamsSpringAop(TeamsNotification teamsNotification, SectionExtractor sectionExtractor) {
		this.teamsNotification = teamsNotification;
		this.sectionExtractor = sectionExtractor;
	}

	@Around("@annotation(event)")
	public Object process(ProceedingJoinPoint joinPoint, TeamsEvent event) throws Throwable {
		Object result = joinPoint.proceed();

		TeamsContent content = new TeamsContent(event.title(), event.message());

		List<TeamsSection> sections = sectionExtractor.extractSections(joinPoint.getArgs());
		sections.forEach(content::addSection);

		TeamsMessage message = TeamsMessage.from(content);
		teamsNotification.sendToMessage(message);

		return result;
	}
}
