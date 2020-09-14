package uitls;

import model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;

public class EmployeeUtilsHql {

    private final SessionFactory sessionFactory;

    public EmployeeUtilsHql(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public int createEmployee(Employee employee) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        int savedId = (int) session.save(employee);
        session.close();
        return savedId;
    }

    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.openSession();
        return session.createQuery("from Employee ").list();
    }

    public Employee getEmployeeById(int id) {
        Session session = sessionFactory.openSession();
        Employee employee = session.get(Employee.class, id);
        session.close();
        return employee;
    }

    public void updateEmployeeName(String name, int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update Employee set name= :name where id= :id");
        query.setParameter("name", name);
        query.setParameter("id", id);
        int result = query.executeUpdate();
        transaction.commit();
        System.out.println("Employee update status = " + result);
        session.close();
    }

    public void deleteEmployeeById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction =session.beginTransaction();
        Query query = session.createQuery("delete from Employee where id= :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        transaction.commit();
        System.out.println("Employee delete status: " + result);
        session.close();
    }

    public long aggregatedSalaryOfEmployees() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select sum(salary) from Employee");
        long sumSalary = (long) query.getSingleResult();
        session.close();
        return sumSalary;
    }

    public void employeeGroupedBy() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(
                "select e.name, sum(e.salary), count(e) from Employee e group by e.name");
        List<Object[]> groupList = query.getResultList();
        groupList.forEach(arr -> System.out.println(Arrays.toString(arr)));
        session.close();
    }

    public List<Employee> employeeOrderedByIdDesc() {
        Session session = sessionFactory.openSession();
        return session.createQuery("from Employee e order by e.id desc ").list();
    }
}
