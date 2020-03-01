package com.hibernate.client;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.hibernate.model.Address;
import com.hibernate.model.ContactNumbers;
import com.hibernate.model.FourWheelerVehicle;
import com.hibernate.model.Meeting;
import com.hibernate.model.Skills;
import com.hibernate.model.TwoWheelerVehicle;
import com.hibernate.model.UserDetails;
import com.hibernate.model.Vehicle;
import com.hibernate.utils.HibernateUtils;

public class ClientClass {

	public static void main(String[] args) {
		createTwoUsers();
		UserDetails user1 = getUser(1);
		System.out.println(user1);
		//System.out.println("************");
		//System.out.println(user1.getAddresses());
		// getAllUser().forEach(System.out::println);
		//getVehicleDetailFromUser();
		HibernateUtils.closeSessionFactory();
	}

	static void getVehicleDetailFromUser() {
		Session session = getSession();
		session.beginTransaction();
		Vehicle vehicle = session.get(Vehicle.class, 1);
		System.out.println(vehicle);
		System.out.println(vehicle.getUserDetails());
		session.close();
	}

	private static UserDetails getUser(int userId) {
		Session session = getSession();
		session.beginTransaction();
		UserDetails user = session.get(UserDetails.class, userId);
		session.close();
		return user;
	}

	static List<UserDetails> getAllUser() {
		Session session = getSession();
		session.beginTransaction();
		return session.createQuery("FROM UserDetails").getResultList();
	}

	static void createTwoUsers() {
		Session session = getSession();
		UserDetails userDetails = new UserDetails();
		userDetails.setUserName("John Cena");
		userDetails.setJoiningDate(new Date());
		ContactNumbers contactNumbers = new ContactNumbers();
		contactNumbers.setOfficeNumber("1203492039");
		contactNumbers.setPersonalNumber("9876523451");
		userDetails.setContactNumbers(contactNumbers);
		Address address1 = new Address();
		address1.setCity("North Delhi");
		address1.setState("North Delhi");
		address1.setStreet("NE Delhi");
		Address address2 = new Address();
		address2.setCity("South Delhi");
		address2.setState("South Delhi");
		address2.setStreet("SE Delhi");
		userDetails.getAddresses().add(address1);
		userDetails.getAddresses().add(address2);
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleName("Swift Desire Car");
		vehicle.setVehicleNo("DL5632X3");
		vehicle.setUserDetails(userDetails);
		TwoWheelerVehicle bike=new TwoWheelerVehicle();
		bike.setVehicleName("Bike");
		bike.setSteeringHandle("Bike Steering");
		bike.setVehicleNo("UP0000");
		bike.setUserDetails(userDetails);
		userDetails.setVehicle(bike);
		Skills skill1=new Skills();
		skill1.setSkillName("JAVA");
		Skills skill2=new Skills();
		skill2.setSkillName("Spring");
		userDetails.getSkillSet().add(skill1);
		userDetails.getSkillSet().add(skill2);
		userDetails.setVehicle(vehicle);
		skill1.setUserDetails(userDetails);
		skill2.setUserDetails(userDetails);
		
		Meeting quaterlyMeeting=new Meeting();
		quaterlyMeeting.setMeetingDate(new Date());
		quaterlyMeeting.setSubject("Quaterly Meeting");
		Meeting weeklyMeeting=new Meeting();
		weeklyMeeting.setMeetingDate(new Date());
		weeklyMeeting.setSubject("Weekly Meeting");
		Meeting dailyMeeting=new Meeting();
		dailyMeeting.setMeetingDate(new Date());
		dailyMeeting.setSubject("Daily Meeting");
		userDetails.getMeetings().add(quaterlyMeeting);
		userDetails.getMeetings().add(dailyMeeting);
		quaterlyMeeting.getUserList().add(userDetails);
		dailyMeeting.getUserList().add(userDetails);
		session.beginTransaction();
		session.save(userDetails);
		session.save(quaterlyMeeting);
		session.save(dailyMeeting);
		session.save(bike);
		//session.save(skill1);
		//session.save(skill2);
		// session.save(vehicle);
		session.getTransaction().commit();

		session = getSession();
		session.beginTransaction();
		UserDetails userDetails2 = new UserDetails();
		userDetails2.setUserName("Roman Reigns");
		userDetails2.setJoiningDate(new Date());
		contactNumbers = new ContactNumbers();
		contactNumbers.setOfficeNumber("12065434039");
		contactNumbers.setPersonalNumber("2345654351");
		userDetails2.setContactNumbers(contactNumbers);
		Address address3 = new Address();
		address3.setCity("North Pune");
		address3.setState("Pune");
		address3.setStreet("NE Pune");
		Address address4 = new Address();
		address4.setCity("South Pune");
		address4.setState("Pune");
		address4.setStreet("SE Pune");
		userDetails2.getAddresses().add(address3);
		userDetails2.getAddresses().add(address4);
		Vehicle vehicle2 = new Vehicle();
		vehicle2.setVehicleName("Benz Car");
		vehicle2.setVehicleNo("DL0007X");
		userDetails2.setVehicle(vehicle2);
		vehicle2.setUserDetails(userDetails2);
		FourWheelerVehicle car =new FourWheelerVehicle();
		car.setSteeringWheel("Steering Wheels");
		car.setUserDetails(userDetails2);
		car.setVehicleName("Car");
		car.setVehicleNo("DL00000");
		
		Skills skill3=new Skills();
		skill3.setSkillName("PYTHON");
		Skills skill4=new Skills();
		skill4.setSkillName("DJANGO");
		userDetails2.getSkillSet().add(skill3);
		userDetails2.getSkillSet().add(skill4);
		skill3.setUserDetails(userDetails2);
		skill4.setUserDetails(userDetails2);
		userDetails2.getMeetings().add(weeklyMeeting);
		weeklyMeeting.getUserList().add(userDetails2);
		session.save(userDetails2);
		session.save(weeklyMeeting);
		session.save(car);
		//session.save(skill3);
		//session.save(skill4);
		// session.save(vehicle2);
		session.getTransaction().commit();
		session.close();
	}

	static Session getSession() {
		return HibernateUtils.getSessionFactory().getCurrentSession();
	}

}
