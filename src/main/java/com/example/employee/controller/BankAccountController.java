package com.example.employee.controller;
import com.example.employee.service.*;
import com.example.employee.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BankAccountController {
	@Autowired
	public BankAccountService bankService;
	
	@PutMapping("/employees/{id}/changePrimaryAccount/{pId}")
	public String changePrimaryAccount(@PathVariable("id") int employeeId, @PathVariable("pId") int primaryAccountId) {
		return bankService.changePrimaryAccount(employeeId, primaryAccountId);
	}
	
	@PutMapping("/employees/{id}/debitAmount/{amount}")
	public String debitAmountFromPrimaryAcc(@PathVariable("id") int employeeId, @PathVariable("amount") double amount) {
		return bankService.debitAmountFromPrimaryAcc(employeeId, amount);
	}
	
	@PutMapping("/employees/{id}/creditAmount/{amount}")
	public String creditAmountToPrimaryAcc(@PathVariable("id") int employeeId, @PathVariable("amount") double amount) {
		return bankService.creditAmountToPrimaryAcc(employeeId, amount);
	}
	
	
	@PutMapping("/creditSalary/employees/{id}")
	public String creditSalary(@PathVariable("id") int id) {
		return bankService.creditSalary(id);
	}
	
	@PostMapping("employees/{id}/addBankAccount")
	public BankAccount addBankAccount(@PathVariable("id") int id, @RequestBody BankAccount bankAccount) {
		return bankService.addBankAccount(id, bankAccount);
	}
}
