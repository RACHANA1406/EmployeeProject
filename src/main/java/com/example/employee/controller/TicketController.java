package com.example.employee.controller;
import com.example.employee.service.*;
import com.example.employee.model.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TicketController {
	@Autowired
	public TicketService ticketService;
	
	@GetMapping("/employees/{id}/tickets")
	public List<TicketObject> getEmployeeTickets(@PathVariable("id") int employeeId){
		return ticketService.getEmployeeTickets(employeeId);
	}
	
	@GetMapping("/tickets/{id}")
	public TicketObject getTicketById(@PathVariable("id") int id) {
		return ticketService.getTicketById(id);
	}
	
	@PostMapping("employees/{id}/addTicket")
	public TicketObject addTicket(@PathVariable("id") int employeeId, @RequestBody Ticket ticket) {
		return ticketService.addTicket(employeeId, ticket);
	}
}
