package by.it_academy.jd2.task_database.storage;

import by.it_academy.jd2.task_database.model.Department;
import by.it_academy.jd2.task_database.storage.api.IDepartmentStorageHibernate;
import by.it_academy.jd2.task_database.view.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;

public class DepartmentStorageHibernate implements IDepartmentStorageHibernate {
    private static final DepartmentStorageHibernate instance = new DepartmentStorageHibernate();
    private final Session session = HibernateUtil.getSessionFactory().openSession();
    private final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

    private DepartmentStorageHibernate() {
    }

    public static DepartmentStorageHibernate getInstance() {
        return instance;
    }

    @Override
    public long addDepartment(Department department) {
        session.beginTransaction();

        Department department1 = new Department();
        department1.setName(department.getName());
        department1.setParentDepartment(department.getParentDepartment());

        session.save(department1);
        session.getTransaction().commit();

        return department1.getId();
    }

    @Override
    public Department getDepartment(long id) {
        CriteriaQuery<Department> criteriaQuery = criteriaBuilder.createQuery(Department.class);
        Root<Department> root = criteriaQuery.from(Department.class);

        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));
        Query<Department> query = session.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    public Collection<Department> getAllDepartments() {
        CriteriaQuery<Department> criteriaQuery = criteriaBuilder.createQuery(Department.class);
        Root<Department> root = criteriaQuery.from(Department.class);

        criteriaQuery.select(root);
        Query<Department> query = session.createQuery(criteriaQuery);

        return query.list();
    }

    @Override
    public long getCountAllEntries() {
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Department> root = criteriaQuery.from(Department.class);

        criteriaQuery.select(criteriaBuilder.count(root));
        Query<Long> query = session.createQuery(criteriaQuery);

        return query.getSingleResult();
    }

    @Override
    public Collection<Department> getAllDepartmentsLimit(long limit, long offset) {
        CriteriaQuery<Department> criteriaQuery = criteriaBuilder.createQuery(Department.class);
        Root<Department> root = criteriaQuery.from(Department.class);

        criteriaQuery.select(root);
        Query<Department> query = session.createQuery(criteriaQuery);
        query.setFirstResult((int) offset);
        query.setMaxResults((int) limit);

        return query.list();
    }
}
