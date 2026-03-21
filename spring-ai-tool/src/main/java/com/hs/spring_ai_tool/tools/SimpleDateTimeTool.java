package com.hs.spring_ai_tool.tools;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.context.i18n.LocaleContextHolder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleDateTimeTool {

	@Tool(description = "Get the current date and time in users zone.")
	public String getCurrentDateTime() {
		log.info("Tool calling");
		log.info("Get the current date and time in users zone.");
		return LocalDateTime.now().atZone(LocaleContextHolder.getTimeZone().toZoneId()).toString();
	}

	@Tool(description = "Set the alarm for given time.")
	void setAlarm(@ToolParam(description = "Time in ISO-8601 format") String time) {
		var dateTime = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME);
		log.info("Set the alarm for given time. {}", dateTime);
	}

}
