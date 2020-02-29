package com.hibernate.model;

import javax.persistence.Embeddable;

@Embeddable
public class ContactNumbers {

	private String officeNumber;
	private String personalNumber;
	
	public String getOfficeNumber() {
		return officeNumber;
	}
	public void setOfficeNumber(String officeNumber) {
		this.officeNumber = officeNumber;
	}
	
	public String getPersonalNumber() {
		return personalNumber;
	}
	public void setPersonalNumber(String personalNumber) {
		this.personalNumber = personalNumber;
	}
	@Override
	public String toString() {
		return "ContactNumbers [officeNumber=" + officeNumber + ", personalNumber=" + personalNumber + "]";
	}
	
}
