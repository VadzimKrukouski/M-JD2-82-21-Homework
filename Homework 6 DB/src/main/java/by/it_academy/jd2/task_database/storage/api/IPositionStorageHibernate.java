package by.it_academy.jd2.task_database.storage.api;

import by.it_academy.jd2.task_database.model.Position;

import java.util.Collection;

public interface IPositionStorageHibernate {
    long addPosition(Position position);
    Position getPosition(long id);
    long getCountAllEntries();
    Collection<Position> getAllPositionsLimit(long limit, long offset);




}
