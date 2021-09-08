package by.it_academy.jd2.task_database.controller.web.servlets;

import by.it_academy.jd2.task_database.model.Employee;
import by.it_academy.jd2.task_database.view.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletGetEmployee", urlPatterns = "/getEmployee")
public class ServletGetEmployee extends HttpServlet {
    private final EmployeeService employeeService;

    public ServletGetEmployee() {
        this.employeeService = EmployeeService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/getEmployee.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Employee employee = employeeService.getEmployee(Long.parseLong(id));

        if (employee != null) {
            req.setAttribute("employee", employee.toString());
        } else {
            req.setAttribute("info", "Такого пользователя не существует");
        }
        req.getRequestDispatcher("views/getEmployee.jsp").forward(req, resp);
    }
}
