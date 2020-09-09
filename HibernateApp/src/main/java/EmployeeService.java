import entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeService {

    private final SessionFactory sessionFactory;

    EmployeeService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public int createEmployee(Employee employee) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(employee);
        transaction.commit();
        System.out.println(id + " saved successfully!");
        session.close();
        return id;
    }

    public List<Employee> getEmployees() {
        Session session = sessionFactory.openSession();
        Query employees = session.createQuery("From Employee");
        return employees.list();
    }
}
