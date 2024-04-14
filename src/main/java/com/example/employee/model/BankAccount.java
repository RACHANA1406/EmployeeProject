package com.example.employee.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
@Entity
@Table(name="bankaccount")
public class BankAccount {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer bankAccountId;
	@Column(name="name")
	private String bankName;
	@Column(name="balance")
	private Double balance;
	@Column(name="isprimary")
	private Boolean isPrimary;
	
	@ManyToOne
	@JoinColumn(name="employeeid")
	@JsonIgnoreProperties("bankAccounts")
	private Employee employee;
	
	public BankAccount() {}

	public BankAccount(Integer bankAccountId, String bankName, Double balance, Boolean isPrimary, Employee employee) {
		this.bankAccountId = bankAccountId;
		this.bankName = bankName;
		this.balance=balance;
		this.isPrimary=isPrimary;
		this.employee=employee;
	}

	public Integer getBankAccountId() {
		return bankAccountId;
	}

	public void setBankAccountId(Integer bankAccountId) {
		this.bankAccountId = bankAccountId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	public Boolean getIsPrimary() {
		return isPrimary;
	}

	public void setIsPrimary(Boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}
