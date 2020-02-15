package com.hibernate.service;

import java.util.List;

import com.hibernate.model.Employee;

public interface EmployeeService {

	Employee getEmployeeById(int empId);

	List<Employee> getAllEmployees();

	void addEmployee(Employee employee);

	void updateEmployee(Employee employee);

	Employee deleteEmployee(int empId);

}
