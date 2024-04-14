package com.example.employee.controller;
import com.example.employee.service.*;
import com.example.employee.model.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {
	
	@Autowired 
	public AdminService adminService;
	
	@GetMapping("/admins/{id}/getAllTickets")
	public List<TicketObject> getAllAdminTickets(@PathVariable("id") int adminId){
		return adminService.getAllAdminTickets(adminId);
	}
	
	@GetMapping("/admins/{id}/tickets/{date1}/{date2}/{status}")
	public List<TicketObject> getAdminTicketsByDate(@PathVariable("id") int adminId, @PathVariable("date1") String date1, @PathVariable("date2") String date2, @PathVariable("status") String status){
		return adminService.getAdminTicketsByDate(adminId, date1, date2, status);
	}
	
	@PutMapping("/tickets/{id}")
	public String solveTicket(@PathVariable("id") int id) {
		return adminService.solveTicket(id);
	} 
	
	@PostMapping("/admins")
	public Admin addAdmin(@RequestBody Admin admin) {
		return adminService.addAdmin(admin);
	}
}
