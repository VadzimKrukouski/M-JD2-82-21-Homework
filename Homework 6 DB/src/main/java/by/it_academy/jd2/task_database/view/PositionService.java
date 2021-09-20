package by.it_academy.jd2.task_database.view;

import by.it_academy.jd2.task_database.model.Position;
import by.it_academy.jd2.task_database.storage.PositionStorage;
import by.it_academy.jd2.task_database.storage.api.IPositionStorage;
import by.it_academy.jd2.task_database.view.api.IPositionService;

import java.util.Collection;

public class PositionService implements IPositionService {
    private static final PositionService instance = new PositionService();
    private final IPositionStorage positionStorage;

    public PositionService() {
        this.positionStorage = PositionStorage.getInstance();
    }

    public static PositionService getInstance() {
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
    public Collection<Position> getAllPositions() {
        return positionStorage.getAllPositions();
    }

    @Override
    public long getCountAllEntries() {
       return positionStorage.getCountAllEntries();
    }

    @Override
    public Collection<Position> getAllPositionsLimit(long limit, long page) {
        long offset = limit*(page-1);
        return positionStorage.getAllPositionsLimit(limit,offset);
    }
}
