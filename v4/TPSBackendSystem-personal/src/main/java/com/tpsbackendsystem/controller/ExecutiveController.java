package com.tpsbackendsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tpsbackendsystem.model.Executive;
import com.tpsbackendsystem.model.Ticket;
import com.tpsbackendsystem.repository.ExecutiveRepository;
import com.tpsbackendsystem.repository.TicketRepository;

@RestController
public class ExecutiveController {
	
	@Autowired
	private ExecutiveRepository executiveRepository;
	
	@PostMapping("/executive")
	public Executive insertExecutive(@RequestBody Executive newExecutive) {
		return executiveRepository.save(newExecutive);
	}
	
	@GetMapping("/executives")
	public List<Executive> getAllExecutives(@RequestParam(name="page", required = false, defaultValue = "0") Integer page,
										    @RequestParam(name="size", required = false, defaultValue = "100") Integer size){
//		Here we are implementing optional paging. Optional paging can be implemented using @RequestParam and by making required=false and giving them a default value so that when no value is passed pageable doesn't give us error
//		We can even implement paging using @PathVariable but it is not optional. We need to compulsarily pass page and size variable
		Pageable pageable = PageRequest.of(page, size);
		return executiveRepository.findAll(pageable).getContent();
	}
	
	@GetMapping("/executive/{id}")
	public Executive getOneExecutive(@PathVariable("id") Long id) {
		return executiveRepository.getOne(id);
	}
	
	
	@PutMapping("/executive/{id}")
	public Executive updateExecutive(@PathVariable("id") Long id, @RequestBody Executive updatedExecutive) {
//		fetch the executive by its id
		Executive executiveDB = executiveRepository.getOne(id);
//		Update the executiveDB using the updatedExecutive
		executiveDB.setDepartment(updatedExecutive.getDepartment());
//		Save the updated value in executiveDB
		return executiveRepository.save(executiveDB);
	}
	
	@DeleteMapping("/executive/{id}")
	public void deleteExecutive(@PathVariable("id") Long id) {
//		Delete the executive by its id
		executiveRepository.deleteById(id);
	}
	

}
