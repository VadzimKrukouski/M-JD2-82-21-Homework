package by.it_academy.jd2.task_database.storage;

import by.it_academy.jd2.task_database.model.Position;
import by.it_academy.jd2.task_database.storage.api.IPositionStorage;
import by.it_academy.jd2.task_database.view.DataBaseConnection;

import java.sql.*;

public class PositionStorage implements IPositionStorage {
    private static final PositionStorage instance = new PositionStorage();
    public final Connection con;

    public PositionStorage() {
        this.con = DataBaseConnection.getConnection();
    }

    public static PositionStorage getInstance() {
        return instance;
    }

    @Override
    public long addPosition(Position position) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "INSERT INTO application.positions(\n" +
                        "\tname)\n" +
                        "\tVALUES(?);", Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, position.getName());

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()
            ) {
                while (generatedKeys.next()) {
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
        try (Statement statement = con.createStatement()){
            try (ResultSet resultSet = statement.executeQuery(
                    "SELECT *" +
                            "FROM application.positions" +
                            "WHERE id=" + id);){
                while (resultSet.next()){
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
}
