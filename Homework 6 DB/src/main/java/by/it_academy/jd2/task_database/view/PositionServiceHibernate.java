package by.it_academy.jd2.task_database.view;

import by.it_academy.jd2.task_database.model.Position;
import by.it_academy.jd2.task_database.storage.PositionStorageHibernate;
import by.it_academy.jd2.task_database.view.api.IPositionServiceHibernate;

public class PositionServiceHibernate implements IPositionServiceHibernate {
    private static final PositionServiceHibernate instance = new PositionServiceHibernate();
    private final PositionStorageHibernate positionStorage;

    private PositionServiceHibernate() {
        this.positionStorage = PositionStorageHibernate.getInstance();
    }

    public static PositionServiceHibernate getInstance() {
        return instance;
    }

    @Override
    public long addPosition(Position position) {
        return positionStorage.addPosition(position);
    }
}
