import org.hibernate.SessionFactory;
import uitls.EmployeeUtilsHql;
import uitls.HibernateUtils;

public class MainClass {

    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        EmployeeUtilsHql employeeUtils = new EmployeeUtilsHql(sessionFactory);
        employeeUtils.getAllEmployees().forEach(System.out::println);
        System.out.println(employeeUtils.getEmployeeById(102).getAddress());
        System.out.println("Sum of all employees salary: " + employeeUtils.aggregatedSalaryOfEmployees());
        employeeUtils.employeeOrderedByIdDesc().forEach(System.out::println);
        /*Employee employee = new Employee(105,"Jack",45,67000);
        System.out.println("Employee created with id: "+employeeUtils.createEmployee(employee));*/
        /*employeeUtils.updateEmployeeName("Miller3",101);*/
        employeeUtils.deleteEmployeeById(105);
    }
}
