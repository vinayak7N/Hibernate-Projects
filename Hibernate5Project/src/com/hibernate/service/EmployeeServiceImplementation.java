package com.hibernate.service;

import java.util.ArrayList;
import java.util.List;

import com.hibernate.dao.EmployeeDao;
import com.hibernate.dao.EmployeeDaoImplementation;
import com.hibernate.model.Employee;
import com.hibernate.util.HibernateUtil;

public class EmployeeServiceImplementation implements EmployeeService {

	private static EmployeeDao employeeDao;

	public EmployeeServiceImplementation() {
		employeeDao = new EmployeeDaoImplementation();
	}

	@Override
	public Employee getEmployeeById(int empId) {

		return employeeDao.fetchEmployeeById(empId);
	}

	@Override
	public List<Employee> getAllEmployees() {

		return employeeDao.fetchAllEmployees();
	}

	@Override
	public void addEmployee(Employee employee) {

		employeeDao.addEmployee(employee);
	}

	@Override
	public void updateEmployee(Employee employee) {
		employeeDao.updateEmployee(employee);
	}

	@Override
	public Employee deleteEmployee(int empId) {
		return employeeDao.deleteEmployee(empId);
	}

}
