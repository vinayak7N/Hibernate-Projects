package uitls;

import model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class EmployeeUtilsCriteriaApi {

    private final SessionFactory sessionFactory;

    public EmployeeUtilsCriteriaApi(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.select(root);
        Query<Employee> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public Employee getEmployeeById(int id) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));
        Query<Employee> query = session.createQuery(criteriaQuery);
        Employee employee = query.getSingleResult();
        session.close();
        return employee;
    }


    public long aggregatedSalaryOfEmployees() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.select(criteriaBuilder.sum(root.get("salary")));
        Query<Long> query = session.createQuery(criteriaQuery);
        Number num = query.getSingleResult();
        session.close();
        return num.longValue();
    }

    public void employeeGroupedByNameSalarySumAge() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.groupBy(root.get("name"));
        criteriaQuery.multiselect(root.get("name"), criteriaBuilder.sum(root.get("salary")), root.get("age"));
        Query<Object[]> query = session.createQuery(criteriaQuery);
        query.getResultList().forEach(object ->
                System.out.printf("Name: %s, Sum Salary: %s, Age: %s%n", object[0], object[1], object[2]));
        session.close();
    }

    public List<Employee> employeeOrderedByIdDesc() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
        Query<Employee> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
