package by.it_academy.jd2.task_database.storage;

import by.it_academy.jd2.task_database.model.Employee;
import by.it_academy.jd2.task_database.storage.api.IEmployeeStorageHibernate;
import by.it_academy.jd2.task_database.view.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;

public class EmployeesStorageHibernate implements IEmployeeStorageHibernate {
    private static final EmployeesStorageHibernate instance = new EmployeesStorageHibernate();
    private final Session session = HibernateUtil.getSessionFactory().openSession();
    private final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

    private EmployeesStorageHibernate() {
    }

    public static EmployeesStorageHibernate getInstance() {
        return instance;
    }

    @Override
    public long addEmployee(Employee employee) {
        session.beginTransaction();

        Employee employee1 = new Employee();
        employee1.setName(employee.getName());
        employee1.setSalary(employee.getSalary());
        employee1.setDepartment(employee.getDepartment());
        employee1.setPosition(employee.getPosition());

        session.save(employee1);
        session.getTransaction().commit();

        return employee1.getId();
    }
}
