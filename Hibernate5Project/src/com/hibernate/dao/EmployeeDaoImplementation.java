package com.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.hibernate.model.Employee;
import com.hibernate.util.HibernateUtil;

public class EmployeeDaoImplementation implements EmployeeDao {

	private static Session session;

	@Override
	public Employee fetchEmployeeById(int empId) {
		
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Employee employee = session.get(Employee.class, empId);
		session.close();
		return employee;
	}

	@Override
	public List<Employee> fetchAllEmployees() {

		List<Employee> empList = new ArrayList<>();
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		empList = session.createQuery("FROM Employee").list();
		session.close();
		return empList;
	}

	@Override
	public void addEmployee(Employee employee) {

		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		if (employee != null) {
			int check = (Integer) session.save(employee);
			if (check != -1)
				System.out.println("Employee added!");
			session.getTransaction().commit();
		} else {
			System.out.println("Unable to add employee!!!");
		}
		session.close();
	}

	@Override
	public void updateEmployee(Employee employee) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		if (employee != null) {
			employee.setEmail("H.M@gmail.com");
			session.update(employee);
			session.getTransaction().commit();
		}
		session.close();
	}

	@Override
	public Employee deleteEmployee(int empId) {
		Employee employee = fetchEmployeeById(empId);
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		if (employee != null) {
			session.delete(employee);
			session.getTransaction().commit();
		} else {
			System.out.println("Unable to delete.Employee doest not exist!");
		}
		return null;
	}

}
