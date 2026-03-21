package com.hs.spring_ai_help_desk.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Component
public class EmailTool {

	@Tool(description = "This tool helps to send email to support team once the ticket is created.")
	public void sendEmailToSupportTeam(
			@ToolParam(description = "Email id is associated with ticket for contact information.") String email,
			@ToolParam(description = "Short descriptoin of ticket summary.") String message) {
		System.out.println("going to send email to support team");
		System.out.println("email id : " + email);
		System.out.println("message : " + message);

	}

}
