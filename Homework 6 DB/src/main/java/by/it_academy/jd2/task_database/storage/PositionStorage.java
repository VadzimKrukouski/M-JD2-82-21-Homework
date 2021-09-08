package by.it_academy.jd2.task_database.storage;

import by.it_academy.jd2.task_database.model.Position;
import by.it_academy.jd2.task_database.storage.api.IPositionStorage;
import by.it_academy.jd2.task_database.view.DataBaseConnection;
import by.it_academy.jd2.task_database.view.DataBaseConnectionCPDS;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PositionStorage implements IPositionStorage {
    private static final PositionStorage instance = new PositionStorage();
    public final ComboPooledDataSource cpds;

    public PositionStorage() {
        this.cpds = DataBaseConnectionCPDS.getInstance().getConnection();
    }

    public static PositionStorage getInstance() {
        return instance;
    }

    @Override
    public long addPosition(Position position) {
        try (Connection con = cpds.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(
                "INSERT INTO application.positions(\n" +
                        "\tname)\n" +
                        "\tVALUES(?);", Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, position.getName());

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()
            ) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                }
            }
            return -1;
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }

    }

    @Override
    public Position getPosition(long id) {
        try (Connection con = cpds.getConnection();
                Statement statement = con.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM application.positions WHERE id=" + id)) {
                if (resultSet.next()) {
                    Position position = new Position();
                    long currentId = resultSet.getLong(1);
                    String name = resultSet.getString(2);

                    position.setId(currentId);
                    position.setName(name);

                    return position;
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
        return null;
    }

    @Override
    public Collection<Position> getAllPositions() {
        List<Position> positionList = new ArrayList<>();
        try (Connection con = cpds.getConnection();
                Statement statement = con.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM application.positions")) {
                while (resultSet.next()){
                    Position position = new Position();
                    long currentId = resultSet.getLong(1);
                    String name = resultSet.getString(2);

                    position.setId(currentId);
                    position.setName(name);

                    positionList.add(position);
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
        return positionList;
    }
}
