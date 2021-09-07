package by.it_academy.jd2.task_database.view;

import by.it_academy.jd2.task_database.model.Position;
import by.it_academy.jd2.task_database.storage.PositionStorage;
import by.it_academy.jd2.task_database.view.api.IPositionService;

import java.util.Collection;

public class PositionService implements IPositionService {
    private static final PositionService instance = new PositionService();

    public static PositionService getInstance() {
        return instance;
    }

    @Override
    public long addPosition(Position position) {
        return PositionStorage.getInstance().addPosition(position);
    }

    @Override
    public Position getPosition(long id) {
        return PositionStorage.getInstance().getPosition(id);
    }

    @Override
    public Collection<Position> getAllCollections() {
        return PositionStorage.getInstance().getAllPositions();
    }
}
