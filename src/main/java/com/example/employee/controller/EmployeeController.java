package com.example.employee.controller;
import com.example.employee.service.*;
import com.example.employee.model.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController{
	@Autowired
	public EmployeeService employeeService;
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeeService.addEmployee(employee);
	}
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployees();
	}
	
	@GetMapping("/employees/{id}")
	public Employee getEmployeeById(@PathVariable("id") int id) {
		return employeeService.getEmployeeById(id);
	}
}
