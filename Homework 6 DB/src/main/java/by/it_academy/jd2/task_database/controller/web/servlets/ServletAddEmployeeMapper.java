package by.it_academy.jd2.task_database.controller.web.servlets;

import by.it_academy.jd2.task_database.model.Department;
import by.it_academy.jd2.task_database.model.Employee;
import by.it_academy.jd2.task_database.model.Position;
import by.it_academy.jd2.task_database.view.DepartmentService;
import by.it_academy.jd2.task_database.view.EmployeeService;
import by.it_academy.jd2.task_database.view.PositionService;
import by.it_academy.jd2.task_database.view.api.IDepartmentService;
import by.it_academy.jd2.task_database.view.api.IEmployeeService;
import by.it_academy.jd2.task_database.view.api.IPositionService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "ServletAddEmployeeMapper" , urlPatterns = "/addEmployeeMapper")
public class ServletAddEmployeeMapper extends HttpServlet {
    private final IEmployeeService employeeService;
    private final IDepartmentService departmentService;
    private final IPositionService positionService;
    private final ObjectMapper mapper = new ObjectMapper();

    public ServletAddEmployeeMapper() {
        this.employeeService = EmployeeService.getInstance();
        this.departmentService = DepartmentService.getInstance();
        this.positionService = PositionService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<Department> allDepartments = departmentService.getAllDepartments();
        Collection<Position> allPositions = positionService.getAllPositions();

        req.setAttribute("allDepartments", allDepartments);
        req.setAttribute("allPositions", allPositions);
        req.getRequestDispatcher("views/addEmployee.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = mapper.readValue(req.getInputStream(), Employee.class);

        long id = employeeService.addEmployee(employee);
        if (id>0){
            req.setAttribute("id", id);
            req.setAttribute("info", "Сотрудник успешно добавлен с id=");
        } else {
            req.setAttribute("infoErr", "Сотрудник не добавлен");
        }
        req.getRequestDispatcher("views/addEmployee.jsp").forward(req,resp);
    }
}
