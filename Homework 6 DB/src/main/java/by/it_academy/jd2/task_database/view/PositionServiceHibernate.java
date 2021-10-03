package by.it_academy.jd2.task_database.view;

import by.it_academy.jd2.task_database.model.Position;
import by.it_academy.jd2.task_database.storage.PositionStorageHibernate;
import by.it_academy.jd2.task_database.view.api.IPositionServiceHibernate;

import java.util.Collection;

public class PositionServiceHibernate implements IPositionServiceHibernate {
    private static final PositionServiceHibernate instance = new PositionServiceHibernate();
    private final PositionStorageHibernate positionStorage;

    private PositionServiceHibernate() {
        this.positionStorage = PositionStorageHibernate.getInstance();
    }

    public static PositionServiceHibernate getInstance() {
        return instance;
    }

    @Override
    public long addPosition(Position position) {
        return positionStorage.addPosition(position);
    }

    @Override
    public Position getPosition(long id) {
        return positionStorage.getPosition(id);
    }

    @Override
    public Collection<Position> getAllPositionsLimit(long limit, long page) {
        long offset = limit*(page-1);
        return positionStorage.getAllPositionsLimit(limit,offset);
    }

    @Override
    public long getCountAllEntries() {
        return positionStorage.getCountAllEntries();
    }
}
