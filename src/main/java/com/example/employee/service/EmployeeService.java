package com.example.employee.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.employee.model.*;
import com.example.employee.repository.*;
import java.util.*;

@Service
public class EmployeeService {
	
	@Autowired 
	private EmployeeJpaRepository employeeRepo;

	public Employee addEmployee(Employee employee) {
		try {
			employee.setSalary(employee.getGrade(), employee.getYearsOfExperience());
			return employeeRepo.save(employee);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bank account Id not found");
		}
	}
	
	public List<Employee> getAllEmployees(){
		return employeeRepo.findAll();
	}
	
	public Employee getEmployeeById(int id) {
		try {
			return employeeRepo.findById(id).get();
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
}




