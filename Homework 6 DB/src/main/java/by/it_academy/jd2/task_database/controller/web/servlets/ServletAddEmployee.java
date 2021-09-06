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

@WebServlet (name = "ServletAddEmployee", urlPatterns = "/addEmployee")
public class ServletAddEmployee extends HttpServlet {
    private final IEmployeeService employeeService;

    public ServletAddEmployee() {
        this.employeeService = EmployeeService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/addEmployee.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        double salary = Double.parseDouble((req.getParameter("salary")));

        Employee employee = new Employee();
        employee.setName(name);
        employee.setSalary(salary);

        long id = employeeService.addEmployee(employee);
        if (id>0){
            req.setAttribute("id", id);
            req.setAttribute("info", "Сотрудник добавлен");
        } else {
            req.setAttribute("infoErr", "Сотрудник не добавлен");
        }
        req.getRequestDispatcher("views/addEmployee.jsp").forward(req,resp);
    }
}
