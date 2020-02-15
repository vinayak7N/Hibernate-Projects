
package com.hibernate.client;

import java.time.LocalDate;

import com.hibernate.model.Address;
import com.hibernate.model.Employee;
import com.hibernate.model.SkillSet;
import com.hibernate.service.EmployeeService;
import com.hibernate.service.EmployeeServiceImplementation;

/*
 * @author Vinayak Nair$
 *
 */
public class ClientClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EmployeeService employeeService = new EmployeeServiceImplementation();

		System.out.println("------------------Adding new Employee------------");
		Employee employeeNew = new Employee();
		employeeNew.setEmpName("William Shakespeare");
		employeeNew.setEmail("William.12@gmail.com");
		employeeNew.setDoj(LocalDate.now());
		employeeNew.setSalary(76000.345);
	
		Address officeAddress=new Address();
		officeAddress.setCity("Delhi");
		officeAddress.setPincode(213454L);
		officeAddress.setState("Delhi");
		officeAddress.setStreet("East Delhi");
		employeeNew.setOfficeAddress(officeAddress);
		
		Address homeAddress=new Address();
		homeAddress.setCity("Pune");
		homeAddress.setPincode(613454L);
		homeAddress.setState("MH");
		homeAddress.setStreet("Pune Central");
		employeeNew.setHomeAddress(homeAddress);
		
		SkillSet skillSet1=new SkillSet();
		skillSet1.setSkillName("Java");
		skillSet1.setSkillRating(7);
		
		SkillSet skillSet2=new SkillSet();
		skillSet2.setSkillName("MySQL");
		skillSet2.setSkillRating(8);
		employeeNew.getSkillSet().add(skillSet1);
		employeeNew.getSkillSet().add(skillSet2);
		
		employeeService.addEmployee(employeeNew);
		

//		System.out.println("------------------Get Employee by id-------------");
//		Employee employee = employeeService.getEmployeeById(1);
//		if (employee != null)
//			System.out.println(employee);
//		else {
//			System.out.println("Employee record doest not exist!!!");
//		}
//		System.out.println("------------------Complete List of employees------------");
//		List<Employee> employeeList = employeeService.getAllEmployees();
//		if (employeeList.size() != 0) {
//			employeeList.forEach(System.out::println);
//		}
//
//		System.out.println("------------------Updating Employee------------");
//		// employee.setEmail("Martin.G@gmail.com");
//		employee.setSalary(99000.95);
//		employeeService.updateEmployee(employee);
//		System.out.println("------------------Deleting Employee------------");
//		employeeService.deleteEmployee(2);
	}
}
