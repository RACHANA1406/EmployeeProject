package com.example.employee.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.employee.model.*;
import com.example.employee.repository.*;
import java.time.LocalDate;
import java.util.*;

@Service
public class AdminService {
	@Autowired
	private AdminJpaRepository adminRepo;
	@Autowired
	private TicketJpaRepository ticketRepo;
	@Autowired
	private BankAccountJpaRepository bankRepo;
	
	public List<TicketObject> getAllAdminTickets(int adminId){
		try {
			Admin admin=adminRepo.findById(adminId).get();
			List<Ticket> tickets= admin.getTickets();
			List<TicketObject> ticketObjects=new ArrayList<>();
			for (Ticket t: tickets) {
				TicketObject ticketObject=new TicketObject();
				ticketObject.setTicketId(t.getTicketId());
				ticketObject.setEmployeeId(t.getEmployee().getEmployeeId());
				ticketObject.setEmployeeName(t.getEmployee().getEmployeeName());
				ticketObject.setDate(t.getDate());
				ticketObject.setType(t.getType());
				ticketObject.setIssue(t.getIssue());
				ticketObject.setStatus(t.getStatus());
				ticketObjects.add(ticketObject);
			}
			return ticketObjects;
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin Id not found");
		}
	}
	
	public List<TicketObject> getAdminTicketsByDate(int adminId, String date1, String date2, String status){
		try {
			Admin admin=adminRepo.findById(adminId).get();
			String typeOfTicket=admin.getTypeOfTicket();
			List<Ticket> requiredTickets=new ArrayList<>();
			List<TicketObject> ticketObjects=new ArrayList<>();
			LocalDate fromDate=LocalDate.parse(date1);
			LocalDate toDate=LocalDate.parse(date2);
			List<Ticket> ticketsInGivenDates=ticketRepo.findByDateBetween(fromDate, toDate);
			for (Ticket t: ticketsInGivenDates) {
				if (t.getStatus().equalsIgnoreCase(status) && t.getType().equals(typeOfTicket)) {
					requiredTickets.add(t);
				}
			}
			for (Ticket t: requiredTickets) {
				TicketObject ticketObject=new TicketObject();
				ticketObject.setTicketId(t.getTicketId());
				ticketObject.setEmployeeId(t.getEmployee().getEmployeeId());
				ticketObject.setEmployeeName(t.getEmployee().getEmployeeName());
				ticketObject.setDate(t.getDate());
				ticketObject.setType(t.getType());
				ticketObject.setIssue(t.getIssue());
				ticketObject.setStatus(t.getStatus());
				ticketObjects.add(ticketObject);
			}
			return ticketObjects;
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin Id not found");
		}
		
	}
	
	
	public String solveTicket(int id) {
		try {
			Ticket ticket=ticketRepo.findById(id).get();
			Employee employee=ticket.getEmployee();
			List<BankAccount> bankAccounts=employee.getBankAccounts();
			if (ticket.getType().equalsIgnoreCase("salary")) {
				BankAccount primaryAcc=null;
				for (BankAccount b: bankAccounts) {
					if (b.getIsPrimary()) {
						primaryAcc=b;
					}
				}
				Double salary=employee.getSalary();
				Double balance=primaryAcc.getBalance();
				primaryAcc.setBalance(salary+balance);
				bankRepo.save(primaryAcc);
				ticket.setStatus("solved");
				ticketRepo.save(ticket);
				return ("Ticket solved... Salary Credited successfully"+"\n"+"Balance: "+primaryAcc.getBalance()+"\n"+"EmployeeId: "+employee.getEmployeeId());
			}
			else {
				return "Ticket is still in pending!!";
			}
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket Id not found");
		}
	} 
	
	public Admin addAdmin(Admin admin) {
		return adminRepo.save(admin);
	}
}