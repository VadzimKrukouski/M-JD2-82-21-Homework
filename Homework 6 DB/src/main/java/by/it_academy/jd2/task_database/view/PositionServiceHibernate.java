package by.it_academy.jd2.task_database.view;

import by.it_academy.jd2.task_database.model.Position;
import by.it_academy.jd2.task_database.storage.PositionStorageHibernate;
import by.it_academy.jd2.task_database.storage.api.IPositionStorageHibernate;
import by.it_academy.jd2.task_database.view.api.IPositionServiceHibernate;

import java.util.Collection;

public class PositionServiceHibernate implements IPositionServiceHibernate {
    private final IPositionStorageHibernate positionStorageHibernate;

    public PositionServiceHibernate(IPositionStorageHibernate positionStorageHibernate) {
        this.positionStorageHibernate = positionStorageHibernate;
    }

    @Override
    public long addPosition(Position position) {
        return positionStorageHibernate.addPosition(position);
    }

    @Override
    public Position getPosition(long id) {
        return positionStorageHibernate.getPosition(id);
    }

    @Override
    public Collection<Position> getAllPositionsLimit(long limit, long page) {
        long offset = limit*(page-1);
        return positionStorageHibernate.getAllPositionsLimit(limit,offset);
    }

    @Override
    public Collection<Position> getAllPositions() {
        return positionStorageHibernate.getAllPositions();
    }

    @Override
    public long getCountAllEntries() {
        return positionStorageHibernate.getCountAllEntries();
    }
}
