package by.it_academy.jd2.task_database.storage.api;

import by.it_academy.jd2.task_database.model.Position;

public interface IPositionStorage {
    long addPosition(Position position);
    Position getPosition(long id);
}