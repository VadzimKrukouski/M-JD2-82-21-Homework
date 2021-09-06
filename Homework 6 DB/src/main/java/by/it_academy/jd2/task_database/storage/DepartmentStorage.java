package by.it_academy.jd2.task_database.storage;

import by.it_academy.jd2.task_database.model.Department;
import by.it_academy.jd2.task_database.storage.api.IDepartmentStorage;
import by.it_academy.jd2.task_database.view.DataBaseConnection;

import java.sql.Connection;

public class DepartmentStorage implements IDepartmentStorage {
    private static final DepartmentStorage instance = new DepartmentStorage();
    private final Connection con;

    public DepartmentStorage() {
        this.con = DataBaseConnection.getConnection();
    }

    public static DepartmentStorage getInstance() {
        return instance;
    }

    @Override
    public long addDepartment(Department department) {
        return 0;
    }

    @Override
    public Department getDepartment(long id) {
        return null;
    }
}
