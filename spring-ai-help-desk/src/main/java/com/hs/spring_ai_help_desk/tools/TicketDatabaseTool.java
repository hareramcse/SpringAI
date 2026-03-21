package com.hs.spring_ai_help_desk.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hs.spring_ai_help_desk.entity.Status;
import com.hs.spring_ai_help_desk.entity.Ticket;
import com.hs.spring_ai_help_desk.service.TicketService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TicketDatabaseTool {

	@Autowired
	private TicketService ticketService;

	@Tool(description = "This tool helps to create new ticket in database.")
	public Ticket createTicketTool(
			@ToolParam(description = "Ticket fields required to create new ticket") Ticket ticket) {
		try {
			log.debug("Before sanitization: " + ticket);

			ticket.setId(null);
			ticket.setCreatedOn(null);
			ticket.setUpdatedOn(null);

			if (ticket.getStatus() == null) {
				ticket.setStatus(Status.OPEN);
			}

			log.debug("After sanitization: " + ticket);

			return ticketService.createTicket(ticket);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Tool(description = "This tool helps to get ticket by username.")
	public Ticket getTicketByUserName(@ToolParam(description = " email id whose ticket is required ") String emailid) {
		return ticketService.getTicketByEmailId(emailid);
	}

	@Tool(description = "This tool helps to update ticket.")
	public Ticket updateTicket(
			@ToolParam(description = "new ticket fields required to update with ticket id.") Ticket ticket) {
		return ticketService.updateTicket(ticket);
	}

	@Tool(description = "This tool helps to get current system time.")
	public String getCurrentTime() {
		return String.valueOf(System.currentTimeMillis());
	}

}
