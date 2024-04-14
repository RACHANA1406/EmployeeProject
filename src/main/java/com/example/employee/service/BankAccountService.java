package com.example.employee.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.employee.model.*;
import com.example.employee.repository.*;
import java.util.*;

@Service
public class BankAccountService {
	@Autowired
	private BankAccountJpaRepository bankRepo;
	@Autowired 
	private EmployeeJpaRepository employeeRepo;
	
	public String changePrimaryAccount(int employeeId, int primaryAccountId) {
		try {
			Employee employee=employeeRepo.findById(employeeId).get();
			List<BankAccount> bankAccounts=employee.getBankAccounts();
			BankAccount primaryAcc=null;
			for (BankAccount bankAcc: bankAccounts) {
				if (bankAcc.getBankAccountId()==primaryAccountId) {
					primaryAcc=bankAcc;
					bankAcc.setIsPrimary(true);
					bankRepo.save(bankAcc);
				}
				else {
					bankAcc.setIsPrimary(false);
					bankRepo.save(bankAcc);
				}
			}
			bankRepo.saveAll(bankAccounts);
			if (primaryAcc!=null) {
				Double bal=primaryAcc.getBalance();
				return ("Primary account has been changed successfully..."+"\n"+ "Balance: "+bal);
			}
			else {
				return "Primary Account Id is not found";
			}
			
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Id not found");
		}
	}
	
	public String debitAmountFromPrimaryAcc(int employeeId, double amount) {
		try {
			Employee employee=employeeRepo.findById(employeeId).get();
			List<BankAccount> bankAccounts=employee.getBankAccounts();
			BankAccount primaryAcc=null;
			for (BankAccount bankAcc: bankAccounts) {
				if (bankAcc.getIsPrimary()) {
					primaryAcc=bankAcc;
					break;
				}
			}
			if (primaryAcc!=null) {
				Double balance=primaryAcc.getBalance();
				if (balance>=amount) {
					primaryAcc.setBalance(balance-amount);
					bankRepo.save(primaryAcc);
					bankRepo.saveAll(bankAccounts);
					return ("Debit Successful...Balance: "+primaryAcc.getBalance());
				}
				else {
					return "Debit failed due to Insufficient Balance...";
				}
			}
			else {
				return "Primary Account not found!!";
			}
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Id not found");
		}
	}
	
	public String creditAmountToPrimaryAcc(int employeeId, double amount) {
		try {
			Employee employee=employeeRepo.findById(employeeId).get();
			List<BankAccount> bankAccounts=employee.getBankAccounts();
			BankAccount primaryAcc=null;
			for (BankAccount bankAcc: bankAccounts) {
				if (bankAcc.getIsPrimary()) {
					primaryAcc=bankAcc;
					break;
				}
			}
			if (primaryAcc!=null) {
				Double balance=primaryAcc.getBalance();
				primaryAcc.setBalance(balance+amount);
				bankRepo.save(primaryAcc);
				return ("Credit Successful...Balance: "+primaryAcc.getBalance());
			}
			else {
				return "Primary Account not found!!";
			}
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Id not found");
		}
	}
	
	public String creditSalary(int employeeId) {
		try {
			Employee employee=employeeRepo.findById(employeeId).get();
			List<BankAccount> bankAccounts=employee.getBankAccounts();
			BankAccount primaryAcc=null;
			for (BankAccount bankAcc: bankAccounts) {
				if (bankAcc.getIsPrimary()) {
					primaryAcc=bankAcc;
					break;
				}
			}
			if (primaryAcc!=null) {
				Double balance=primaryAcc.getBalance();
				Double salary=employee.getSalary();
				primaryAcc.setBalance(balance+salary);
				bankRepo.save(primaryAcc);
				bankRepo.saveAll(bankAccounts);
				employeeRepo.save(employee);
				return ("Salary credited successfully..."+"\n"+"Balance: "+primaryAcc.getBalance());
			}
			else {
				return "Primary Account not found";
			}
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Id not found");
		}
	}
	
	public BankAccount addBankAccount(int id, BankAccount bankAccount) {
		try {
			Employee employee=employeeRepo.findById(id).get();
			if (bankAccount.getIsPrimary()) {
				List<BankAccount> bankAccounts=employee.getBankAccounts();
				for (BankAccount b: bankAccounts) {
					b.setIsPrimary(false);
				}
				bankRepo.saveAll(bankAccounts);
			}
			bankAccount.setEmployee(employee);
			employee.getBankAccounts().add(bankAccount);
			employeeRepo.save(employee);
			BankAccount bankAcc=bankRepo.save(bankAccount);
			return bankAcc;
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee Id not found");
		}
	}
}
