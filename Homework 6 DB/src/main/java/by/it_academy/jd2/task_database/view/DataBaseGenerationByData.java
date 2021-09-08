package by.it_academy.jd2.task_database.view;

import by.it_academy.jd2.task_database.model.Department;
import by.it_academy.jd2.task_database.model.Employee;
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
    private final List<String> names = Arrays.asList("Аарон","Абрам","Аваз", "Аввакум", "Август", "Августин",
            "Авдей", "Аверьян", "АвраамАвтандил", "Витольд","Владимир","Владислав","Владлен","Влас","Власий",
            "Воислав","Володар","Вольга", "Вольдемар","Всеволод","Всеслав","Вышеслав", "Вячеслав");
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
        for (String departmentName : departments) {
            Department department = new Department();
            department.setName(departmentName);
            departmentService.addDepartment(department);
        }


    }

    @Override
    public void generationEmployers() {
        for (int i = 0; i < 10001; i++) {
            String name = names.get((int) (0 + Math.random() * names.size()));
            double salary = Math.random() * 9999999;
            Position position = positionService.getPosition((long) (1 + Math.random() * positions.size()));
            Department department = departmentService.getDepartment((long) (1 + Math.random() * departments.size()));

            Employee employee = new Employee();
            employee.setName(name);
            employee.setSalary(salary);
            employee.setPosition(position);
            employee.setDepartment(department);

            employeeService.addEmployee(employee);
        }

    }

    public static DataBaseGenerationByData getInstance() {
        return instance;
    }
}