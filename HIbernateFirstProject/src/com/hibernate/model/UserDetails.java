package com.hibernate.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "USER_DETAILS")
public class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private int userId;

	@Column(name = "USER_NAME")
	private String userName;

	@Transient
	private String country = "INDIA";

	@Temporal(TemporalType.DATE)
	@Column(name = "JOINING_DATE")
	private Date joiningDate;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "officeNumber", column = @Column(name = "OFFICE_NO")),
			@AttributeOverride(name = "personalNumber", column = @Column(name = "PERSONAL_NO")) })
	private ContactNumbers contactNumbers;

	@ElementCollection(fetch = FetchType.EAGER)
	@JoinTable(name = "USER_ADDRESS", joinColumns = @JoinColumn(name = "USER_ID"))
	// @GenericGenerator(name="hilo-gen",strategy="hilo")
	// @CollectionId(columns = { @Column(name="ADDRESS_ID") }, generator =
	// "hilo-gen", type = @Type(type="long"))
	private Collection<Address> addresses = new ArrayList<>(0);

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "VEHICLE_ID")
	private Vehicle vehicle;

	//@OneToMany(cascade=CascadeType.ALL)
	//@JoinTable(name="USER_SKILLS", joinColumns=@JoinColumn(name="USER_ID"),
	//inverseJoinColumns = @JoinColumn(name="SKILLS_ID"))
	@OneToMany(mappedBy="userDetails",cascade = CascadeType.ALL)
	private Set<Skills> skillSet = new HashSet<>(0);

	public Set<Skills> getSkillSet() {
		return skillSet;
	}

	public void setSkillSet(Set<Skills> skillSet) {
		this.skillSet = skillSet;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Collection<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Collection<Address> addresses) {
		this.addresses = addresses;
	}

	public ContactNumbers getContactNumbers() {
		return contactNumbers;
	}

	public void setContactNumbers(ContactNumbers contactNumbers) {
		this.contactNumbers = contactNumbers;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getCountry() {
		return country;
	}

	public int getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "UserDetails [userId=" + userId + ", userName=" + userName + ", country=" + country + ", joiningDate="
				+ joiningDate + ", contactNumbers=" + contactNumbers + "]";
	}
}
