package com.hibernate.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "Employee")
@DynamicUpdate
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "empid_generator")
	@SequenceGenerator(name = "empid_generator", initialValue = 101, allocationSize = 1, sequenceName = "emp_seq")
	@Column(name = "EmpID")
	private Integer empId;

	@Column(name = "EmpName", length = 20, nullable = false)
	private String empName;

	@Column(name = "EmpEmail", length = 30, nullable = false, unique = true)
	private String email;

	@Column(name = "DateOFJoining")
	private LocalDate doj;

	@Column(name = "EmpSalary")
	private Double salary;

	// Added as Value Type(Value Object)
	// No separate table is created for Address but all its fields are added as
	// employee fields in DB.
	@Embedded

	// Added to resolve conflict between officeAddress and homeAddress fields
	@AttributeOverrides(value = { @AttributeOverride(column = @Column(name = "home_street_name"), name = "street"),
			@AttributeOverride(column = @Column(name = "home_city_name"), name = "city"),
			@AttributeOverride(column = @Column(name = "home_state_name"), name = "state"),
			@AttributeOverride(column = @Column(name = "home_pin_code"), name = "pincode") })
	private Address homeAddress;

	@Embedded
	@AttributeOverrides(value = { @AttributeOverride(column = @Column(name = "office_street_name"), name = "street"),
			@AttributeOverride(column = @Column(name = "office_city_name"), name = "city"),
			@AttributeOverride(column = @Column(name = "office_state_name"), name = "state"),
			@AttributeOverride(column = @Column(name = "office_pin_code"), name = "pincode") })
	private Address officeAddress;

	// For adding collection of elements.
	@ElementCollection
	//To change the name of table and foreign key column name.
	@JoinTable(name = "employee_skills", joinColumns = @JoinColumn(name = "employee_id"))
	private Collection<SkillSet> skillSet = new HashSet<>();

	public Employee() {
	}

	public Collection<SkillSet> getSkillSet() {
		return skillSet;
	}

	public void setSkillSet(Collection<SkillSet> skillSet) {
		this.skillSet = skillSet;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Address getOfficeAddress() {
		return homeAddress;
	}

	public void setOfficeAddress(Address officeAddress) {
		this.officeAddress = officeAddress;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDoj() {
		return doj;
	}

	public void setDoj(LocalDate doj) {
		this.doj = doj;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", email=" + email + ", doj=" + doj + ", salary="
				+ salary + "]";
	}

}
