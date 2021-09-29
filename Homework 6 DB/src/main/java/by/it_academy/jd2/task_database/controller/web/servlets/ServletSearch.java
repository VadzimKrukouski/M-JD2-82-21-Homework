package by.it_academy.jd2.task_database.controller.web.servlets;

import by.it_academy.jd2.task_database.model.Employee;
import by.it_academy.jd2.task_database.view.EmployeeService;
import by.it_academy.jd2.task_database.view.api.IEmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet (name = "ServletSearch", urlPatterns = "/search")
public class ServletSearch extends HttpServlet {
    private final IEmployeeService employeeService;

    public ServletSearch() {
        this.employeeService = EmployeeService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        long salary1 = Long.parseLong(req.getParameter("salary1"));
        long salary2 = Long.parseLong(req.getParameter("salary2"));

        Collection<Employee> employeesForSearch = employeeService.getEmployeesForSearch(name, salary1, salary2);

        req.setAttribute("allEmployers", employeesForSearch);
        req.getRequestDispatcher("views/allEmployeeLimit.jsp").forward(req,resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
