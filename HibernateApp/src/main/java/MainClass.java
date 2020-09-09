import entity.Employee;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class MainClass {

    public static void main(String[] args) {

        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        EmployeeService employeeService = new EmployeeService(sessionFactory);

        /*Employee employee1 = new Employee();
        employee1.setId(102);
        employee1.setName("Kelly");
        employee1.setAge(23);
        employee1.setSalary(48000);
        int id = employeeService.createEmployee(employee1);*/
        Employee employee2 = new Employee(104,"Mary",26,3465);
        employeeService.createEmployee(employee2);
        employeeService.getEmployees().forEach(System.out::println);
        sessionFactory.close();
    }
}
