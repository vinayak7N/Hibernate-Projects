package com.hibernate.dao;

import java.util.List;

import com.hibernate.model.Employee;

public interface EmployeeDao {

	Employee fetchEmployeeById(int empId);

	List<Employee> fetchAllEmployees();

	void addEmployee(Employee employee);

	void updateEmployee(Employee employee);

	Employee deleteEmployee(int empId);

}
