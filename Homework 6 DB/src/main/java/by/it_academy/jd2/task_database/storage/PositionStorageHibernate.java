package by.it_academy.jd2.task_database.storage;

import by.it_academy.jd2.task_database.model.Position;
import by.it_academy.jd2.task_database.storage.api.IPositionStorageHibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;

public class PositionStorageHibernate implements IPositionStorageHibernate {
    private final SessionFactory sessionFactory;
    private static final String NAME_EXCEPTION = "Ошибка работы с базой данных";

    public PositionStorageHibernate(SessionFactory sessionFactory) {
        this.sessionFactory=sessionFactory;
    }

    @Override
    public long addPosition(Position position) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.save(position);
            session.getTransaction().commit();

            return position.getId();
        }
        catch (Exception e){
            throw new IllegalStateException(NAME_EXCEPTION);
        }
    }

    @Override
    public Position getPosition(long id) {
        try (Session session = sessionFactory.openSession()){
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Position> criteriaQuery = criteriaBuilder.createQuery(Position.class);
            Root<Position> itemRoot = criteriaQuery.from(Position.class);

            criteriaQuery.where(
                    criteriaBuilder.equal(itemRoot.get("id"), id)
            );
            Query<Position> query = session.createQuery(criteriaQuery);

            return query.getSingleResult();
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
            Root<Position> itemRoot = criteriaQuery.from(Position.class);

            criteriaQuery.select(criteriaBuilder.count(itemRoot));
            Query<Long> query = session.createQuery(criteriaQuery);

            return query.getSingleResult();
        }
        catch (Exception e){
            throw new IllegalStateException(NAME_EXCEPTION);
        }
    }

    @Override
    public Collection<Position> getAllPositionsLimit(long limit, long offset) {
        try (Session session = sessionFactory.openSession()){
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Position> criteriaQuery = criteriaBuilder.createQuery(Position.class);
            Root<Position> itemRoot = criteriaQuery.from(Position.class);

            criteriaQuery.select(itemRoot);
            Query<Position> query = session.createQuery(criteriaQuery);
            query.setFirstResult((int) offset);
            query.setMaxResults((int) limit);

            return query.list();
        }
        catch (Exception e){
            throw new IllegalStateException(NAME_EXCEPTION);
        }
    }

    @Override
    public Collection<Position> getAllPositions() {
        try (Session session = sessionFactory.openSession()){
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Position> criteriaQuery = criteriaBuilder.createQuery(Position.class);
            Root<Position> root = criteriaQuery.from(Position.class);

            criteriaQuery.select(root);
            Query<Position> query = session.createQuery(criteriaQuery);

            return query.list();
        }
        catch (Exception e){
            throw new IllegalStateException(NAME_EXCEPTION);
        }
    }
}