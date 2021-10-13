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
import java.util.List;

public class PositionStorageHibernate implements IPositionStorageHibernate {
    private final SessionFactory sessionFactory;

    public PositionStorageHibernate(SessionFactory sessionFactory) {
        this.sessionFactory=sessionFactory;
    }

    @Override
    public long addPosition(Position position) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Position position1 = new Position();
        position1.setName(position.getName());

        session.save(position1);

        session.getTransaction().commit();

        session.close();

        return position1.getId();

    }

    @Override
    public Position getPosition(long id) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Position> criteriaQuery = criteriaBuilder.createQuery(Position.class);
        Root<Position> itemRoot = criteriaQuery.from(Position.class);

        criteriaQuery.where(
                criteriaBuilder.equal(itemRoot.get("id"), id)
        );
        Query<Position> query = session.createQuery(criteriaQuery);
        Position singleResult = query.getSingleResult();

        session.close();

        return singleResult;
    }

    @Override
    public long getCountAllEntries() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Position> itemRoot = criteriaQuery.from(Position.class);

        criteriaQuery.select(criteriaBuilder.count(itemRoot));
        Query<Long> query = session.createQuery(criteriaQuery);
        Long singleResult = query.getSingleResult();
        session.close();

        return singleResult;
    }

    @Override
    public Collection<Position> getAllPositionsLimit(long limit, long offset) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Position> criteriaQuery = criteriaBuilder.createQuery(Position.class);
        Root<Position> itemRoot = criteriaQuery.from(Position.class);

        criteriaQuery.select(itemRoot);
        Query<Position> query = session.createQuery(criteriaQuery);
        query.setFirstResult((int) offset);
        query.setMaxResults((int) limit);
        List<Position> list = query.list();
        session.close();

        return list;
    }

    @Override
    public Collection<Position> getAllPositions() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Position> criteriaQuery = criteriaBuilder.createQuery(Position.class);
        Root<Position> root = criteriaQuery.from(Position.class);

        criteriaQuery.select(root);
        Query<Position> query = session.createQuery(criteriaQuery);
        List<Position> list = query.list();
        session.close();

        return list;
    }
}