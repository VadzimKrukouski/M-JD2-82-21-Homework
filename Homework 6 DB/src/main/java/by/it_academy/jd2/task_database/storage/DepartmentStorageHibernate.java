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

public class DepartmentStorageHibernate implements IDepartmentStorageHibernate {
    private final SessionFactory sessionFactory;
    private static final String NAME_EXCEPTION = "Ошибка в работе с базой данных";

    public DepartmentStorageHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public long addDepartment(Department department) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.save(department);
            session.getTransaction().commit();

            return department.getId();
        }
        catch (Exception e){
            throw new IllegalStateException(NAME_EXCEPTION);
        }

    }

    @Override
    public Department getDepartment(long id) {
        try (Session session = sessionFactory.openSession()){
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Department> criteriaQuery = criteriaBuilder.createQuery(Department.class);
            Root<Department> root = criteriaQuery.from(Department.class);

            criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));
            Query<Department> query = session.createQuery(criteriaQuery);

            return query.getSingleResult();
        }
        catch (Exception e){
            throw new IllegalStateException(NAME_EXCEPTION);
        }
    }

    @Override
    public Collection<Department> getAllDepartments() {
        try (Session session = sessionFactory.openSession()){
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Department> criteriaQuery = criteriaBuilder.createQuery(Department.class);
            Root<Department> root = criteriaQuery.from(Department.class);

            criteriaQuery.select(root);
            Query<Department> query = session.createQuery(criteriaQuery);

            return query.list();
        }
        catch (Exception e){
            throw new IllegalStateException(NAME_EXCEPTION);
        }
    }

    @Override
    public long getCountAllEntries() {
        try (Session session = sessionFactory.openSession()){
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            Root<Department> root = criteriaQuery.from(Department.class);

            criteriaQuery.select(criteriaBuilder.count(root));
            Query<Long> query = session.createQuery(criteriaQuery);

            return query.getSingleResult();
        }
        catch (Exception e){
            throw new IllegalStateException(NAME_EXCEPTION);
        }
    }

    @Override
    public Collection<Department> getAllDepartmentsLimit(long limit, long offset) {
        try (Session session = sessionFactory.openSession()){
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Department> criteriaQuery = criteriaBuilder.createQuery(Department.class);
            Root<Department> root = criteriaQuery.from(Department.class);

            criteriaQuery.select(root);
            Query<Department> query = session.createQuery(criteriaQuery);
            query.setFirstResult((int) offset);
            query.setMaxResults((int) limit);

            return query.list();
        }
        catch (Exception e){
            throw new IllegalStateException(NAME_EXCEPTION);
        }
    }
}
