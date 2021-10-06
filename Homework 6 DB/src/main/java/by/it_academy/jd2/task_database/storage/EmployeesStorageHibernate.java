package by.it_academy.jd2.task_database.storage;

import by.it_academy.jd2.task_database.model.Employee;
import by.it_academy.jd2.task_database.storage.api.IEmployeeStorageHibernate;
import by.it_academy.jd2.task_database.view.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;

public class EmployeesStorageHibernate implements IEmployeeStorageHibernate {
    private final SessionFactory sessionFactory;

    private EmployeesStorageHibernate(SessionFactory sessionFactory) {
        this.sessionFactory=sessionFactory;
    }

//    public static EmployeesStorageHibernate getInstance() {
//        return instance;
//    }

    @Override
    public long addEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
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
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);

        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));
        Query<Employee> query = session.createQuery(criteriaQuery);

        return query.getSingleResult();
    }

    @Override
    public Collection<Employee> getALLEmployersLimit(long limit, long offset) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
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
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);

        criteriaQuery.select(criteriaBuilder.count(root));
        Query<Long> query = session.createQuery(criteriaQuery);

        return query.getSingleResult();
    }

    @Override
    public long getCountAllEntriesByDepartment(long id) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);

        criteriaQuery.select(
                criteriaBuilder.count(root))
                .where(criteriaBuilder.equal(root.get("department"), id));

        Query<Long> query = session.createQuery(criteriaQuery);

        return query.getSingleResult();
    }

    @Override
    public Collection<Employee> getEmployersByDepartmentLimit(long idDepartment, long limit, long offset) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);

        criteriaQuery.where(criteriaBuilder.equal(root.get("department"), idDepartment));

        Query<Employee> query = session.createQuery(criteriaQuery);
        query.setFirstResult((int) offset);
        query.setMaxResults((int) limit);

        return query.list();
    }

    @Override
    public long getCountAllEntriesByPosition(long id) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);

        criteriaQuery.select(
                criteriaBuilder.count(root))
                .where(criteriaBuilder.equal(root.get("position"), id));

        Query<Long> query = session.createQuery(criteriaQuery);

        return query.getSingleResult();
    }

    @Override
    public Collection<Employee> getEmployeesForSearch(String name, long salary1, long salary2, long limit, long offset) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);

        criteriaQuery.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("name"), name),
                        criteriaBuilder.between(root.get("salary"), salary1, salary2)
                )
        );
        Query<Employee> query = session.createQuery(criteriaQuery);
        query.setFirstResult((int) offset);
        query.setMaxResults((int) limit);

        return query.list();
    }

    @Override
    public long getCountAllEntriesForSearch(String name, long salary1, long salary2) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);

        criteriaQuery.select(
                criteriaBuilder.count(root))
                .where(criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("name"), name),
                        criteriaBuilder.between(root.get("salary"), salary1, salary2)
                        )
                );

        Query<Long> query = session.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    public Collection<Employee> getEmployersByPositionLimit(long idPosition, long limit, long offset) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);

        criteriaQuery.where(criteriaBuilder.equal(root.get("position"), idPosition));

        Query<Employee> query = session.createQuery(criteriaQuery);
        query.setFirstResult((int) offset);
        query.setMaxResults((int) limit);

        return query.list();
    }
}
