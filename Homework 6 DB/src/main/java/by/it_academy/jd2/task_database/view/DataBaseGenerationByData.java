package by.it_academy.jd2.task_database.view;

import by.it_academy.jd2.task_database.model.Department;
import by.it_academy.jd2.task_database.model.Position;
import by.it_academy.jd2.task_database.view.api.IDataBaseGenerationByData;

import java.util.Arrays;
import java.util.List;

public class DataBaseGenerationByData implements IDataBaseGenerationByData {
    private static final DataBaseGenerationByData instance = new DataBaseGenerationByData();
    private final DepartmentService departmentService;
    private final PositionService positionService;
    private final EmployeeService employeeService;

    private final List<String> positions = Arrays.asList("Директор", "Гл.бухгалтер", "Заместитель директора",
            "Бухгалтер", "Менеджер", "Начальник отдела продаж", "Слесарь", "Начальник отдела сервиса",
            "Уборщица", "Экономист");
    private final List<String> departments = Arrays.asList("Управление", "Отдел продаж",
            "Сервис", "Вспомогательный персонал", "Финансовый отдел");

    public DataBaseGenerationByData() {
        this.employeeService = EmployeeService.getInstance();
        this.departmentService = DepartmentService.getInstance();
        this.positionService = PositionService.getInstance();
    }

    @Override
    public void generationPosition() {
        for (String positionName : positions) {
            Position position = new Position();
            position.setName(positionName);
            positionService.addPosition(position);
        }

    }

    @Override
    public void generationDepartment() {


    }

    @Override
    public void generationEmployers() {

    }

    public static DataBaseGenerationByData getInstance() {
        return instance;
    }
}
