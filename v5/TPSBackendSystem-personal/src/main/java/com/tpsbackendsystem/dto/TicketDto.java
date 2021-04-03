package com.tpsbackendsystem.dto;

import java.time.LocalDate;

public class TicketDto {
	
	private Long id;
	private String description;
	private LocalDate createdDate;
	private String actionTaken;
	private String status;
	private String customerName;
	private String customerEmail;
	private String executiveName;
	private String customerMobile;
	private String customerCode;
	
	public TicketDto(Long id, String description, LocalDate createdDate, String actionTaken, String status,
			String customerName, String customerEmail, String executiveName, String customerMobile,
			String customerCode) {
		super();
		this.id = id;
		this.description = description;
		this.createdDate = createdDate;
		this.actionTaken = actionTaken;
		this.status = status;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.executiveName = executiveName;
		this.customerMobile = customerMobile;
		this.customerCode = customerCode;
	}

	public TicketDto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public String getActionTaken() {
		return actionTaken;
	}

	public void setActionTaken(String actionTaken) {
		this.actionTaken = actionTaken;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getExecutiveName() {
		return executiveName;
	}

	public void setExecutiveName(String executiveName) {
		this.executiveName = executiveName;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	
	
	

	
	
	
	
}
