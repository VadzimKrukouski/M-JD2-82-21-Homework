package by.it_academy.jd2.task_database.storage;

import by.it_academy.jd2.task_database.model.Position;
import by.it_academy.jd2.task_database.storage.api.IPositionStorage;
import by.it_academy.jd2.task_database.view.DataBaseConnection;

import java.sql.Connection;

public class PositionStorage implements IPositionStorage {
    private static final PositionStorage instance = new PositionStorage();
    public final Connection con;

    public PositionStorage() {
        this.con= DataBaseConnection.getConnection();
    }

    public static PositionStorage getInstance() {
        return instance;
    }

    @Override
    public long addPosition(Position position) {
        return 0;
    }

    @Override
    public Position getPosition(long id) {
        return null;
    }
}
