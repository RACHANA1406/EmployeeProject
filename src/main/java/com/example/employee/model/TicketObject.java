package com.example.employee.model;
import java.time.LocalDate;

public class TicketObject {
	private Integer ticketId;
	private Integer employeeId;
	private String employeeName;
	private LocalDate date;
	private String type;
	private String issue;
	private String status;
	
	public TicketObject() {}

	public TicketObject(Integer ticketId, Integer employeeId, String employeeName, LocalDate date, String type,
			String issue, String status) {
		this.ticketId = ticketId;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.date = date;
		this.type = type;
		this.issue = issue;
		this.status = status;
	}

	public Integer getTicketId() {
		return ticketId;
	}

	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
