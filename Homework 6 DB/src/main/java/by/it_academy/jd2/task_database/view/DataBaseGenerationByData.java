package by.it_academy.jd2.task_database.view;

import by.it_academy.jd2.task_database.model.Department;
import by.it_academy.jd2.task_database.model.Employee;
import by.it_academy.jd2.task_database.model.Position;
import by.it_academy.jd2.task_database.view.api.IDataBaseGenerationByData;
import by.it_academy.jd2.task_database.view.api.IDepartmentServiceHibernate;
import by.it_academy.jd2.task_database.view.api.IEmployeeServiceHibernate;
import by.it_academy.jd2.task_database.view.api.IPositionServiceHibernate;

import java.util.Arrays;
import java.util.List;

public class DataBaseGenerationByData implements IDataBaseGenerationByData {
    private final IDepartmentServiceHibernate departmentServiceHibernate;
    private final IPositionServiceHibernate positionServiceHibernate;
    private final IEmployeeServiceHibernate employeeServiceHibernate;

    private final List<String> positions = Arrays.asList("Директор", "Гл.бухгалтер", "Заместитель директора",
            "Бухгалтер", "Менеджер", "Начальник отдела продаж", "Слесарь", "Начальник отдела сервиса",
            "Уборщица", "Экономист");
    private final List<String> departments = Arrays.asList("Управление", "Отдел продаж",
            "Сервис", "Вспомогательный персонал", "Финансовый отдел");
    private final List<String> names = Arrays.asList("Аарон", "Абрам", "Аваз", "Аввакум", "Август", "Августин",
            "Авдей", "Аверьян", "АвраамАвтандил", "Витольд", "Владимир", "Владислав", "Владлен", "Влас", "Власий",
            "Воислав", "Володар", "Вольга", "Вольдемар", "Всеволод", "Всеслав", "Вышеслав", "Вячеслав");

    public DataBaseGenerationByData(DepartmentServiceHibernate departmentServiceHibernate, PositionServiceHibernate positionServiceHibernate, EmployeeServiceHibernate employeeServiceHibernate) {
        this.departmentServiceHibernate = departmentServiceHibernate;
        this.positionServiceHibernate = positionServiceHibernate;
        this.employeeServiceHibernate = employeeServiceHibernate;
    }

    @Override
    public void generationPosition() {
        for (String positionName : positions) {
            Position position = new Position();
            position.setName(positionName);
            positionServiceHibernate.addPosition(position);
        }
    }

    @Override
    public void generationDepartment() {
        for (String departmentName : departments) {
            Department department = new Department();
            if (departmentName.equals("Отдел продаж")
                    || departmentName.equals("Сервис")
                    || departmentName.equals("Вспомогательный персонал")
                    || departmentName.equals("Финансовый отдел")) {
                department.setName(departmentName);
                Department parentDepartment = departmentServiceHibernate.getDepartment(1);
                department.setParentDepartment(parentDepartment);
            } else {
                department.setName(departmentName);
            }
            departmentServiceHibernate.addDepartment(department);
        }
    }

    @Override
    public void generationEmployers() {
        for (int i = 0; i < 10000; i++) {
            String name = names.get((int) (0 + Math.random() * names.size()));
            double salary = Math.random() * 9999999;
            Position position = positionServiceHibernate.getPosition((long) (1 + Math.random() * positions.size()));

            Department department = departmentServiceHibernate.getDepartment((long) (1 + Math.random() * departments.size()));
            Employee employee = new Employee();
            employee.setName(name);
            employee.setSalary(salary);
            employee.setPosition(position);
            employee.setDepartment(department);

            employeeServiceHibernate.addEmployee(employee);
        }

    }
}
