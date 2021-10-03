package by.it_academy.jd2.task_database.storage;

import by.it_academy.jd2.task_database.model.Position;
import by.it_academy.jd2.task_database.storage.api.IPositionStorageHibernate;
import by.it_academy.jd2.task_database.view.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.List;

public class PositionStorageHibernate implements IPositionStorageHibernate {
    private static final PositionStorageHibernate instance = new PositionStorageHibernate();
    private Session session = HibernateUtil.getSessionFactory().openSession();
    private CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

    private PositionStorageHibernate() {
    }

    public static PositionStorageHibernate getInstance() {
        return instance;
    }

    @Override
    public long addPosition(Position position) {
        session.beginTransaction();

        Position position1 = new Position();
        position1.setName(position.getName());

        session.save(position1);

        session.getTransaction().commit();

        return position1.getId();

    }

    @Override
    public Position getPosition(long id) {
        CriteriaQuery<Position> criteriaQuery = criteriaBuilder.createQuery(Position.class);
        Root<Position> itemRoot = criteriaQuery.from(Position.class);

        criteriaQuery.where(
                criteriaBuilder.equal(itemRoot.get("id"), id)
        );
        Query<Position> query = session.createQuery(criteriaQuery);

        return query.getSingleResult();
    }

    @Override
    public long getCountAllEntries() {
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Position> itemRoot = criteriaQuery.from(Position.class);

        criteriaQuery.select(criteriaBuilder.count(itemRoot));
        Query<Long> query = session.createQuery(criteriaQuery);

        return query.getSingleResult();
    }

    @Override
    public Collection<Position> getAllPositionsLimit(long limit, long offset) {
        CriteriaQuery<Position> criteriaQuery = criteriaBuilder.createQuery(Position.class);
        Root<Position> itemRoot = criteriaQuery.from(Position.class);

        criteriaQuery.select(itemRoot);
        Query<Position> query = session.createQuery(criteriaQuery);
        query.setFirstResult((int) offset);
        query.setMaxResults((int) limit);

        return query.list();
    }
}