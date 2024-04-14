package com.example.employee.model;
import jakarta.persistence.*;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="employee")
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer employeeId;
	@Column(name="name")
	private String employeeName;
	@Column(name="experience")
	private Integer yearsOfExperience;
	@Column(name="grade")
	private String grade;
	@Column(name="salary")
	private Double salary;
	
	@OneToMany(mappedBy="employee")
	@JsonIgnoreProperties("employee")
	private List<BankAccount> bankAccounts=new ArrayList<>();
	
	public Employee() {}

	public Employee(Integer employeeId, String employeeName, Integer yearsOfExperience, String grade,
			Double salary, List<BankAccount> bankAccounts) {
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.yearsOfExperience = yearsOfExperience;
		this.grade = grade;
		this.salary = salary;
		this.bankAccounts = bankAccounts;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Integer getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(Integer yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Double getSalary() {
		return salary;
	}
	
	public void setSalary(String grade, int experience) {
		Double sal=0.00;
		if (grade.equals("A")) {
			if (experience<=2) {
				sal=30000.00;
			}
			else if (experience>2 && experience<=4) {
				sal=35000.00;
			}
			else if(experience>4){
				sal=45000.00;
			}
		}
		else if (grade.equals("B")) {
			if (experience<=2) {
				sal=25000.00;
			}
			else if (experience>2 && experience<=4) {
				sal=30000.00;
			}
			else if(experience>4) {
				sal=40000.00;
			}
		}
		else if (grade.equals("C")) {
			if (experience<=2) {
				sal=20000.00;
			}
			else if (experience>2 && experience<=4) {
				sal=25000.00;
			}
			else if(experience>4){
				sal=35000.00;
			}
		}
		else if (grade.equals("D")) {
			if (experience<=2) {
				sal=15000.00;
			}
			else if (experience>2 && experience<=4) {
				sal=20000.00;
			}
			else if(experience>4) {
				sal=30000.00;
			}
		}
		
		this.salary=sal;
	}
	
	public List<BankAccount> getBankAccounts() {
		return bankAccounts;
	}

	public void setBankAccounts(List<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}
}

	