package by.it_academy.jd2.task_database.storage;

import by.it_academy.jd2.task_database.model.Employee;
import by.it_academy.jd2.task_database.storage.api.IEmployeeStorageHibernate;
import by.it_academy.jd2.task_database.view.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;

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

    @Override
    public Employee getEmployee(long id) {
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);

        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));
        Query<Employee> query = session.createQuery(criteriaQuery);

        return query.getSingleResult();
    }

    @Override
    public Collection<Employee> getALLEmployersLimit(long limit, long offset) {
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);

        criteriaQuery.select(root);
        Query<Employee> query = session.createQuery(criteriaQuery);
        query.setFirstResult((int) offset);
        query.setMaxResults((int) limit);

        return query.list();
    }

    @Override
    public long getCountAllEntries() {
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);

        criteriaQuery.select(criteriaBuilder.count(root));
        Query<Long> query = session.createQuery(criteriaQuery);

        return query.getSingleResult();
    }
}
