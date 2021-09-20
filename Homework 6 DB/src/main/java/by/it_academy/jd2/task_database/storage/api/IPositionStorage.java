package by.it_academy.jd2.task_database.storage.api;

import by.it_academy.jd2.task_database.model.Position;

import java.util.Collection;

public interface IPositionStorage {
    long addPosition(Position position);
    Position getPosition(long id);
    Collection<Position> getAllPositions();
    long getCountAllEntries();
    Collection<Position> getAllPositionsLimit(long limit, long offset);

}
