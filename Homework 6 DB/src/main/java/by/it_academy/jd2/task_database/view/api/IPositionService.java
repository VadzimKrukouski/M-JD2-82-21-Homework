package by.it_academy.jd2.task_database.view.api;

import by.it_academy.jd2.task_database.model.Position;

public interface IPositionService {
    long addPosition(Position position);
    Position getPosition(long id);
}
