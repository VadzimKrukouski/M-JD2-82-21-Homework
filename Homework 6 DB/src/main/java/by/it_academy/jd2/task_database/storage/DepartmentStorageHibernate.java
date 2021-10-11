package by.it_academy.jd2.task_database.storage;

import by.it_academy.jd2.task_database.model.Department;
import by.it_academy.jd2.task_database.storage.api.IDepartmentStorageHibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.List;

public class DepartmentStorageHibernate implements IDepartmentStorageHibernate {
    private final SessionFactory sessionFactory;

    private DepartmentStorageHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public long addDepartment(Department department) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Department department1 = new Department();
        department1.setName(department.getName());
        department1.setParentDepartment(department.getParentDepartment());

        session.save(department1);
        session.getTransaction().commit();

        long id = department1.getId();

        session.close();

        return id;
    }

    @Override
    public Department getDepartment(long id) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Department> criteriaQuery = criteriaBuilder.createQuery(Department.class);
        Root<Department> root = criteriaQuery.from(Department.class);

        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));
        Query<Department> query = session.createQuery(criteriaQuery);
        Department singleResult = query.getSingleResult();

        session.close();

        return singleResult;
    }

    @Override
    public Collection<Department> getAllDepartments() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Department> criteriaQuery = criteriaBuilder.createQuery(Department.class);
        Root<Department> root = criteriaQuery.from(Department.class);

        criteriaQuery.select(root);
        Query<Department> query = session.createQuery(criteriaQuery);
        List<Department> list = query.list();

        session.close();

        return list;
    }

    @Override
    public long getCountAllEntries() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Department> root = criteriaQuery.from(Department.class);

        criteriaQuery.select(criteriaBuilder.count(root));
        Query<Long> query = session.createQuery(criteriaQuery);
        Long singleResult = query.getSingleResult();

        session.close();

        return singleResult;
    }

    @Override
    public Collection<Department> getAllDepartmentsLimit(long limit, long offset) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Department> criteriaQuery = criteriaBuilder.createQuery(Department.class);
        Root<Department> root = criteriaQuery.from(Department.class);

        criteriaQuery.select(root);
        Query<Department> query = session.createQuery(criteriaQuery);
        query.setFirstResult((int) offset);
        query.setMaxResults((int) limit);
        List<Department> list = query.list();

        session.close();

        return list;
    }
}
