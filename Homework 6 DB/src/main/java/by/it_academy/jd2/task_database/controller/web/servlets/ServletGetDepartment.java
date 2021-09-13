package by.it_academy.jd2.task_database.controller.web.servlets;

import by.it_academy.jd2.task_database.model.Department;
import by.it_academy.jd2.task_database.model.Employee;
import by.it_academy.jd2.task_database.view.DepartmentService;
import by.it_academy.jd2.task_database.view.EmployeeService;
import by.it_academy.jd2.task_database.view.api.IDepartmentService;
import by.it_academy.jd2.task_database.view.api.IEmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "ServletGetDepartment", urlPatterns = "/getDepartment")
public class ServletGetDepartment extends HttpServlet {
    private final IDepartmentService departmentService;
    private final IEmployeeService employeeService;

    public ServletGetDepartment() {
        this.departmentService = DepartmentService.getInstance();
        this.employeeService = EmployeeService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        Department department = departmentService.getDepartment(Long.parseLong(id));
        Collection<Employee> employersByDepartment = employeeService.getEmployersByDepartment(Long.parseLong(id));

        if (department != null) {
            req.setAttribute("department", department.getName());
            req.setAttribute("employersByDepartment", employersByDepartment);
        } else {
            req.setAttribute("info", "Такого отдела не существует");
        }
        req.getRequestDispatcher("views/getDepartment.jsp").forward(req,resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
