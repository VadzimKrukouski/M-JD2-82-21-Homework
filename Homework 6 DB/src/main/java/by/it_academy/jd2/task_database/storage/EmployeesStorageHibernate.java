package by.it_academy.jd2.task_database.storage;

import by.it_academy.jd2.task_database.model.Employee;
import by.it_academy.jd2.task_database.model.EmployeeDTO;
import by.it_academy.jd2.task_database.storage.api.IEmployeeStorageHibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EmployeesStorageHibernate implements IEmployeeStorageHibernate {
    private final SessionFactory sessionFactory;
    private static final String NAME_EXCEPTION = "Ошибка работы с базой данных";

    public EmployeesStorageHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public long addEmployee(Employee employee) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();

            return employee.getId();
        }
        catch (Exception e){
            throw new IllegalStateException(NAME_EXCEPTION);
        }

    }

    @Override
    public Employee getEmployee(long id) {
        try (Session session = sessionFactory.openSession()){
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
            Root<Employee> root = criteriaQuery.from(Employee.class);

            criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));
            Query<Employee> query = session.createQuery(criteriaQuery);

            return query.getSingleResult();
        }
        catch (Exception e){
            throw new IllegalStateException(NAME_EXCEPTION);
        }

    }

    @Override
    public Collection<Employee> getALLEmployersLimit(long limit, long offset) {
        try (Session session = sessionFactory.openSession()){
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
            Root<Employee> root = criteriaQuery.from(Employee.class);

            criteriaQuery.select(root).orderBy(criteriaBuilder.asc(root.get("id")));
            Query<Employee> query = session.createQuery(criteriaQuery);
            query.setFirstResult((int) offset);
            query.setMaxResults((int) limit);

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
            Root<Employee> root = criteriaQuery.from(Employee.class);

            criteriaQuery.select(criteriaBuilder.count(root));
            Query<Long> query = session.createQuery(criteriaQuery);

            return query.getSingleResult();
        }
        catch (Exception e){
            throw new IllegalStateException(NAME_EXCEPTION);
        }

    }

    @Override
    public long getCountAllEntriesLastVersion(EmployeeDTO employeeDTO) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            Root<Employee> root = criteriaQuery.from(Employee.class);

            List<Predicate> predicates = new ArrayList<>();
            if (employeeDTO.getPosition() != null) {
                predicates.add(criteriaBuilder.equal(root.get("position"), employeeDTO.getPosition().getId()));
            }
            if (employeeDTO.getDepartment() != null) {
                predicates.add(criteriaBuilder.equal(root.get("department"), employeeDTO.getDepartment().getId()));
            }
            if (employeeDTO.getName() != null && employeeDTO.getSalaryFrom() != 0 && employeeDTO.getSalaryTo() != 0) {
                predicates.add(criteriaBuilder.equal(root.get("name"), employeeDTO.getName()));
                predicates.add(criteriaBuilder.between(root.get("salary"), employeeDTO.getSalaryFrom(), employeeDTO.getSalaryTo()));
            }
            Predicate[] predicatesArray = predicates.toArray(new Predicate[0]);

            criteriaQuery.select(
                    criteriaBuilder.count(root))
                    .where(criteriaBuilder.and(predicatesArray));
            Query<Long> query = session.createQuery(criteriaQuery);

            return query.getSingleResult();
        } catch (Exception e) {
            throw new IllegalStateException("Ошибка в работе с базой данных");
        }
    }

    @Override
    public Collection<Employee> getAllEmployersLastVersion(EmployeeDTO employeeDTO, long limit, long offset) {
        try (Session session = sessionFactory.openSession()){
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
            Root<Employee> root = criteriaQuery.from(Employee.class);

            List<Predicate> predicates = new ArrayList<>();
            if (employeeDTO.getPosition()!=null){
                predicates.add(criteriaBuilder.equal(root.get("position"), employeeDTO.getPosition().getId()));
            }
            if (employeeDTO.getDepartment()!=null){
                predicates.add(criteriaBuilder.equal(root.get("department"), employeeDTO.getDepartment().getId()));
            }
            if (employeeDTO.getName()!=null && employeeDTO.getSalaryFrom()!=0 && employeeDTO.getSalaryTo()!=0){
                predicates.add(criteriaBuilder.equal(root.get("name"), employeeDTO.getName()));
                predicates.add(criteriaBuilder.between(root.get("salary"), employeeDTO.getSalaryFrom(), employeeDTO.getSalaryTo()));
            }
            Predicate[] predicatesArray = predicates.toArray(new Predicate[0]);
            criteriaQuery.where(predicatesArray);

            Query<Employee> query = session.createQuery(criteriaQuery);
            query.setFirstResult((int) offset);
            query.setMaxResults((int) limit);

            return query.list();
        }
        catch (Exception e){
            throw new IllegalStateException("Ошибка в работе с базой данных");
        }
    }



//
//    @Override
//    public long getCountAllEntriesByDepartment(long id) {
//        Session session = sessionFactory.openSession();
//        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
//        Root<Employee> root = criteriaQuery.from(Employee.class);
//
//        criteriaQuery.select(
//                criteriaBuilder.count(root))
//                .where(criteriaBuilder.equal(root.get("department"), id));
//
//        Query<Long> query = session.createQuery(criteriaQuery);
//        Long singleResult = query.getSingleResult();
//
//        session.close();
//
//        return singleResult;
//    }
//
//    @Override
//    public long getCountAllEntriesByPosition(long id) {
//        Session session = sessionFactory.openSession();
//        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
//        Root<Employee> root = criteriaQuery.from(Employee.class);
//
//        criteriaQuery.select(
//                criteriaBuilder.count(root))
//                .where(criteriaBuilder.equal(root.get("position"), id));
//
//        Query<Long> query = session.createQuery(criteriaQuery);
//        Long singleResult = query.getSingleResult();
//
//        session.close();
//
//        return singleResult;
//    }
//
//    @Override
//    public long getCountAllEntriesForSearch(EmployeeDTO employeeDTO) {
//        Session session = sessionFactory.openSession();
//        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
//        Root<Employee> root = criteriaQuery.from(Employee.class);
//
//        criteriaQuery.select(
//                criteriaBuilder.count(root))
//                .where(criteriaBuilder.and(
//                        criteriaBuilder.equal(root.get("name"), employeeDTO.getName()),
//                        criteriaBuilder.between(root.get("salary"), employeeDTO.getSalaryFrom(), employeeDTO.getSalaryTo())
//                        )
//                );
//
//        Query<Long> query = session.createQuery(criteriaQuery);
//        Long singleResult = query.getSingleResult();
//
//        session.close();
//
//        return singleResult;

//    }
//    @Override
//    public Collection<Employee> getEmployersByPositionLimit(long idPosition, long limit, long offset) {
//        Session session = sessionFactory.openSession();
//        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
//        Root<Employee> root = criteriaQuery.from(Employee.class);
//
//        criteriaQuery.where(criteriaBuilder.equal(root.get("position"), idPosition));
//
//        Query<Employee> query = session.createQuery(criteriaQuery);
//        query.setFirstResult((int) offset);
//        query.setMaxResults((int) limit);
//
//        List<Employee> list = query.list();
//
//        session.close();
//
//        return list;

//    }
//    @Override
//    public Collection<Employee> getEmployersByDepartmentLimit(long idDepartment, long limit, long offset) {
//        Session session = sessionFactory.openSession();
//        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
//        Root<Employee> root = criteriaQuery.from(Employee.class);
//
//        criteriaQuery.where(criteriaBuilder.equal(root.get("department"), idDepartment));
//
//        Query<Employee> query = session.createQuery(criteriaQuery);
//        query.setFirstResult((int) offset);
//        query.setMaxResults((int) limit);
//        List<Employee> list = query.list();
//
//        session.close();
//
//        return list;

//    }

//    @Override
//    public Collection<Employee> getEmployeesForSearch(String name, long salary1, long salary2, long limit, long offset) {
//        Session session = sessionFactory.openSession();
//        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
//        Root<Employee> root = criteriaQuery.from(Employee.class);
//
//        criteriaQuery.where(
//                criteriaBuilder.and(
//                        criteriaBuilder.equal(root.get("name"), name),
//                        criteriaBuilder.between(root.get("salary"), salary1, salary2)
//                )
//        );
//        Query<Employee> query = session.createQuery(criteriaQuery);
//        query.setFirstResult((int) offset);
//        query.setMaxResults((int) limit);
//
//        List<Employee> list = query.list();
//
//        session.close();
//
//        return list;
//    }
}
