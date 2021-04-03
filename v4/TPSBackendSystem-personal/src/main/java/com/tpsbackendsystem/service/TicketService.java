package com.tpsbackendsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tpsbackendsystem.model.Ticket;
import com.tpsbackendsystem.repository.TicketRepository;

@Service
public class TicketService {
	
	@Autowired
	private TicketRepository ticketRepository;

	public List<Ticket> fetchTicketByCustomerID(Long custID) {
		return ticketRepository.fetchTicketByCustomerID(custID);
	}

	public List<Ticket> fetchTicketByCustomerEmail(String custEmail) {
		return ticketRepository.fetchTicketByCustomerEmail(custEmail);
	}

	public List<Ticket> fetchTicketByCustomerMobile(String custMobile) {
		return ticketRepository.fetchTicketByCustomerMobile(custMobile);
	}

	public List<Ticket> fetchTicketByCustomerCode(String custCode) {
		return ticketRepository.fetchTicketByCustomerCode(custCode);
	}

}
