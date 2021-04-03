package com.tpsbackendsystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tpsbackendsystem.model.Ticket;
import com.tpsbackendsystem.repository.TicketRepository;

@Service
public class TicketService {
	
	@Autowired
	private TicketRepository ticketRepository;

	public List<Ticket> fetchTicketByCustomerID(Long custID) {
//		Here we will only show those tickets which have status as "Open"
		List<Ticket> listTicket = ticketRepository.fetchTicketByCustomerID(custID);
		listTicket = listTicket.stream().filter(t -> t.getStatus().equals("open")).collect(Collectors.toList());
		return listTicket;
	}

	public List<Ticket> fetchTicketByCustomerEmail(String custEmail) {
		List<Ticket> listTicket = ticketRepository.fetchTicketByCustomerEmail(custEmail);
		listTicket = listTicket.stream().filter(t -> t.getStatus().equals("open")).collect(Collectors.toList());
		return listTicket;
	}

	public List<Ticket> fetchTicketByCustomerMobile(String custMobile) {
		List<Ticket> listTicket = ticketRepository.fetchTicketByCustomerMobile(custMobile);
		listTicket = listTicket.stream().filter(t -> t.getStatus().equals("open")).collect(Collectors.toList());
		return listTicket;
	}

	public List<Ticket> fetchTicketByCustomerCode(String custCode) {
		List<Ticket> listTicket = ticketRepository.fetchTicketByCustomerCode(custCode);
		listTicket = listTicket.stream().filter(t -> t.getStatus().equals("open")).collect(Collectors.toList());
		return listTicket;
	}

}
