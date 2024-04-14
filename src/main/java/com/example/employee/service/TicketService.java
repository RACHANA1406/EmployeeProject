package com.example.employee.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.employee.model.*;
import com.example.employee.repository.*;
import java.util.*;

@Service
public class TicketService {
	@Autowired
	private TicketJpaRepository ticketRepo;
	@Autowired
	private EmployeeJpaRepository employeeRepo;
	@Autowired
	private AdminJpaRepository adminRepo;
	public List<TicketObject> getEmployeeTickets(int employeeId){
		try {
			Employee employee=employeeRepo.findById(employeeId).get();
			List<Ticket> tickets=ticketRepo.findByEmployee(employee);
			List<TicketObject> ticketObjects=new ArrayList<>();
			for (Ticket t: tickets) {
				TicketObject ticketObject=new TicketObject();
				ticketObject.setTicketId(t.getTicketId());
				ticketObject.setEmployeeId(employee.getEmployeeId());
				ticketObject.setEmployeeName(employee.getEmployeeName());
				ticketObject.setDate(t.getDate());
				ticketObject.setType(t.getType());
				ticketObject.setIssue(t.getIssue());
				ticketObject.setStatus(t.getStatus());
				ticketObjects.add(ticketObject);
			}
			return ticketObjects;
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Id not found");
		}
	}
	
	public TicketObject getTicketById(int id) {
		try {
			Ticket ticket=ticketRepo.findById(id).get();
			TicketObject ticketObject=new TicketObject();
			ticketObject.setTicketId(id);
			ticketObject.setEmployeeId(ticket.getEmployee().getEmployeeId());
			ticketObject.setEmployeeName(ticket.getEmployee().getEmployeeName());
			ticketObject.setDate(ticket.getDate());
			ticketObject.setType(ticket.getType());
			ticketObject.setIssue(ticket.getIssue());
			ticketObject.setStatus(ticket.getStatus());
			return ticketObject;
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket Id not found");
		}
	}
	
	public TicketObject addTicket(int employeeId, Ticket ticket) {
		try {
			Employee employee=employeeRepo.findById(employeeId).get();
			ticket.setEmployee(employee);
			ticket.setStatus("unsolved");
			TicketObject ticketObject=new TicketObject();
			ticketObject.setEmployeeId(employeeId);
			ticketObject.setEmployeeName(employee.getEmployeeName());
			String type=ticket.getType();
			ticketObject.setType(type);
			ticketObject.setDate(ticket.getDate());
			ticketObject.setIssue(ticket.getIssue());
			List<Admin> admins=adminRepo.findAll();
			ticketObject.setStatus("unsolved");
			for (Admin admin: admins) {
				if (admin.getTypeOfTicket().equalsIgnoreCase(type)) {
					ticket.setAdmin(admin);
					admin.getTickets().add(ticket);
					adminRepo.save(admin);
				}
			}
			adminRepo.saveAll(admins);
			Ticket t=ticketRepo.save(ticket);
			int id=t.getTicketId();
			ticketObject.setTicketId(id);
			return ticketObject;
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Id not found");
		}
	}
}
