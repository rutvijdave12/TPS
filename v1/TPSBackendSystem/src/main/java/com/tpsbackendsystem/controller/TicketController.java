package com.tpsbackendsystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tpsbackendsystem.model.Ticket;

//Make the controller RestController to make restful Apis
@RestController
public class TicketController {
	
/*
 * Get api for fetching tickets
 * url- http://localhost:8787/get/ticket
 */
	
	@GetMapping("/get/ticket")
	public List <Ticket> getTicketData(){
		
		Ticket ticket = new Ticket();
		ticket.setIssuer("Harry Potter");
		ticket.setDescription("My Laptop Hangs, Please Help!!");
		ticket.setId(1L);
		
		Ticket ticket1 = new Ticket();
		ticket1.setIssuer("Ronald Weasley");
		ticket1.setDescription("My Mobile Hangs, Please Help!!");
		ticket1.setId(2L);
		
		List <Ticket> list = new ArrayList<>();
		
		list.add(ticket);
		list.add(ticket1);
		
		return list;
	}

}
