package by.it_academy.jd2.task_database.storage;

import by.it_academy.jd2.task_database.model.Position;
import by.it_academy.jd2.task_database.storage.api.IPositionStorageHibernate;
import by.it_academy.jd2.task_database.view.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class PositionStorageHibernate implements IPositionStorageHibernate {
    private static final PositionStorageHibernate instance = new PositionStorageHibernate();

    private PositionStorageHibernate() {
    }

    public static PositionStorageHibernate getInstance() {
        return instance;
    }


    @Override
    public long addPosition(Position position) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = HibernateUtil.getSessionFactory().createEntityManager().getCriteriaBuilder();
        CriteriaQuery<Position> criteriaQuery = criteriaBuilder.createQuery(Position.class);
        Root<Position> itemRoot = criteriaQuery.from(Position.class);

        session.beginTransaction();

        Position position1 = new Position();
        position1.setName(position.getName());

        session.save(position1);

        session.getTransaction().commit();

        HibernateUtil.shutdown();

        return position1.getId();

    }
}
