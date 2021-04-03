package com.tpsbackendsystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tpsbackendsystem.dto.TicketDto;
import com.tpsbackendsystem.model.Customer;
import com.tpsbackendsystem.model.Executive;
import com.tpsbackendsystem.model.Ticket;
import com.tpsbackendsystem.repository.CustomerRepository;
import com.tpsbackendsystem.repository.ExecutiveRepository;
import com.tpsbackendsystem.repository.TicketRepository;
import com.tpsbackendsystem.service.TicketService;

//Make the controller RestController to make restful Apis
@RestController
public class TicketController {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ExecutiveRepository executiveRepository;
	
	@Autowired
	private TicketService ticketService;
	/*
	 * Since we cannot create an object for interface and we cannot use this reference since it pointing to null
	 * we will use the annotation "@Autowired" which will make spring connect it automatically to the interface and it will
	 * handle everything. Now we can use this reference to use different methods of jpaRepository which extends crudRepository.
	 * 
	 * Autowiring feature of spring framework enables you to inject the object dependency implicitly.
	 * It internally uses setter or constructor injection. Autowiring can't be used to inject primitive and string values. 
	 * It works with reference only.
	 */
	
	/*
	 * Take values from user and insert into DB
	 * GET: POST: PUT: DELETE
	 * 
	 {
	 	description: "something",
	 	issuer: "nameOfTheIssuer"
	 }
	 spring will convert this JSON object into Java object using jackson dependencies
	 
	 */
	@PostMapping("/ticket/{cid}/{eid}")
	public void insertTicket(@PathVariable("cid") Long cid,
							 @PathVariable("eid") Long eid,
							 @RequestBody Ticket ticket) {
		
//		Fetch Customer from DB
		Customer customer = customerRepository.getOne(cid);
//		Fetch Executive from DB
		Executive executive  = executiveRepository.getOne(eid);
		
		ticket.setCustomer(customer);
		ticket.setExecutive(executive);
		
		ticketRepository.save(ticket);
	}
	
	
	
	@PostMapping("/ticket")
	public Ticket insertTicket(@RequestBody Ticket ticket) {
		/*
		 @RequestBody is used to convert the body of the HTTP request to the java class object with the aid of selected HTTP message converter. 
		 This annotation will be used in the method parameter and the body of the http request will be mapped to that method parameter.
		 */
		return ticketRepository.save(ticket);
		
	}
	
//	Let's return all the tickets present in our DB
////Now we will work with paging that we see in google or amazon when we click next page we don't see all the items we only see a limited items on a single page so that the db doesn't breakdown when retrieving the items
//@GetMapping("/tickets/{page}/{size}")
//public List<Ticket> getAllTickets(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
//	Pageable pageable = PageRequest.of(page, size);
////	Pageable is an interface.
////	PageRequest is an abstract class which has a static method "of"
//	return ticketRepository.findAll(pageable).getContent(); //Since our return type is a List<Ticket> we need to convert the Pageable type to a List using getContent()
////	jpaRepository provides us with a findAll() method and also findAll() method with an argument for Pageable interface
	@GetMapping("/tickets")
	public List <Ticket> getAllTickets(){
		return ticketRepository.findAll();
	}
	
//	Let's return the ticket that we ask for using an id
	
	@GetMapping("/ticket/{id}")
	public Ticket getSingleTicket(@PathVariable("id") Long id) {
		/*
		 @PathVariable will take the same parameter that we pass in the url as path variable.
		 Here we are using {id} so we use @PathVariable("id") and save it in Long id since that is the datatype of our id.
		
		If we use "/ticket/{abc}" then we need to use "abc" inside @PathVariable("abc")
		 */
		return ticketRepository.getOne(id);
	}
	
	
//	Now let us update the ticket
	
	@PutMapping("/ticket/{id}")
	public Ticket updateTicket(@PathVariable("id") Long id, @RequestBody Ticket newTicketInfo) {
		/* The above method will store the id passed inside the url and store it in variable id of datatype Long.
	 		We are also passing new information(new ticket info) which is to be retrieved using @RequestBody and 
	 		to be stored in the variable newTicketInfo of datatype Ticket
		*/
		
//		Go to DB and fetch the ticket by using the id
		Ticket ticketDB = ticketRepository.getOne(id);
//		now update the fields of ticketDB using the newTicketInfo
		ticketDB.setDescription(newTicketInfo.getDescription());
//		We are just letting the user update their description, so even if they try to update their name (issuer) we won't let them do that
		
//		save updated info into the DB
		return ticketRepository.save(ticketDB);
	}
	
//	Now let's delete a ticket using request params (that we use for queries typically searching)
	
	// Even though the url looks like "localhost:8787/ticket" it will actually be "localhost:8787/ticket?id=2" since we are deleting whatever is present as id=2 using request params
	@DeleteMapping("/ticket") 
	public void deleteTicket(@RequestParam("id") Long id) {
//		@RequestParam() also works like @PathVariable(). The difference is that @PathVariable is a path and @RequestParam takes a query ("localhost:8787/ticket?id=1")
//		We don't use this unless we are using it to search something i.e. in GET request
		ticketRepository.deleteById(id);
	}
	
	@GetMapping("/ticket-info-id/{id}")
	public List<TicketDto> fetchTicketByCustomerID(@PathVariable("id") Long custID){
		List<Ticket> listTicket = ticketService.fetchTicketByCustomerID(custID);
//		Here we will get all the tickets that have a status as "open"
		List<TicketDto> listDto = new ArrayList<>();
//		Iterate-convert-add
		
		listTicket.stream().forEach(t -> {
//			Here we can either initialize the variables using constructor or using getters and setters as we have done below
			TicketDto dto = new TicketDto(); 
			dto.setId(t.getId());    //conversion
			dto.setDescription(t.getDescription());
			dto.setActionTaken(t.getActionTaken());
			dto.setCreatedDate(t.getCreatedDate());
			dto.setStatus(t.getStatus());
			dto.setExecutiveName(t.getExecutive().getName());
			dto.setCustomerName(t.getCustomer().getName());
		//	dto.setCustomerEmail(t.getCustomer().getEmail());
			listDto.add(dto); //add
		});
		
		
		
//		for(Ticket t : listTicket) {
//			TicketDto dto = new TicketDto();
//			dto.setId(t.getId());    //conversion
//			dto.setDescription(t.getDescription());
//			dto.setActionTaken(t.getActionTaken());
//			dto.setCreatedDate(t.getCreatedDate());
//			dto.setStatus(t.getStatus());
//			dto.setExecutiveName(t.getExecutive().getName());
//			dto.setCustomerName(t.getCustomer().getName());
//		//	dto.setCustomerEmail(t.getCustomer().getEmail());
//			listDto.add(dto); //add
//		}
		return listDto;
	}
	
	@GetMapping("/ticket-info-email/{email}")
	public List<TicketDto> fetchTicketByCustomerEmail(@PathVariable("email") String custEmail){
		List<Ticket> listTicket = ticketService.fetchTicketByCustomerEmail(custEmail);
//		Here we will get all the tickets that have a status as "open"
		List<TicketDto> listDto = new ArrayList<>();
//		Iterate-convert-add
		
//		Iterate
		listTicket.stream().forEach(t -> {
//			Here we can either initialize the variables using constructor or using getters and setters as we have done below
//			conversion
			TicketDto dto = new TicketDto(t.getId(), t.getDescription(), t.getCreatedDate(), t.getActionTaken(), t.getStatus(), t.getCustomer().getName(), t.getCustomer().getEmail() , t.getExecutive().getName(), null, null); 

			listDto.add(dto); //add
		});
		return listDto;
	}
	
	@GetMapping("/ticket-info-mobile/{mobile}")
	public List<TicketDto> fetchTicketByCustomerMobile(@PathVariable("mobile") String custMobile){
		List<Ticket> listTicket = ticketService.fetchTicketByCustomerMobile(custMobile);
//		Here we will get all the tickets that have a status as "open"
		List<TicketDto> listDto = new ArrayList<>();
//		Iterate-convert-add
		
//		Iterate
		listTicket.stream().forEach(t -> {
//			Here we can either initialize the variables using constructor or using getters and setters as we have done below
//			conversion
			TicketDto dto = new TicketDto(t.getId(), t.getDescription(), t.getCreatedDate(), t.getActionTaken(), t.getStatus(), t.getCustomer().getName(), null , t.getExecutive().getName(), t.getCustomer().getMobile(), null); 

			listDto.add(dto); //add
		});
		return listDto;
	}
	
	@GetMapping("/ticket-info-code/{code}")
	public List<TicketDto> fetchTicketByCustomerCode(@PathVariable("code") String custCode){
		List<Ticket> listTicket = ticketService.fetchTicketByCustomerCode(custCode);
//		Here we will get all the tickets that have a status as "open"
		List<TicketDto> listDto = new ArrayList<>();
//		Iterate-convert-add
		
//		Iterate
		listTicket.stream().forEach(t -> {
//			Here we can either initialize the variables using constructor or using getters and setters as we have done below
//			conversion
			TicketDto dto = new TicketDto(t.getId(), t.getDescription(), t.getCreatedDate(), t.getActionTaken(), t.getStatus(), t.getCustomer().getName(), null , t.getExecutive().getName(), null, t.getCustomer().getCustomerCode()); 

			listDto.add(dto); //add
		});
		return listDto;
	}
	
	

}
