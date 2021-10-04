package by.it_academy.jd2.task_database.view.api;

import by.it_academy.jd2.task_database.model.Position;

import java.util.Collection;

public interface IPositionServiceHibernate {
    long addPosition(Position position);
    Position getPosition(long id);
    long getCountAllEntries();
    Collection<Position> getAllPositionsLimit(long limit, long page);
    Collection<Position> getAllPositions();





}
