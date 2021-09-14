package by.it_academy.jd2.task_database.controller.web.servlets;

import by.it_academy.jd2.task_database.model.Employee;
import by.it_academy.jd2.task_database.view.EmployeeService;
import by.it_academy.jd2.task_database.view.api.IEmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletGetEmployeeMapper", urlPatterns = "/getEmployeeMapper")
public class ServletGetEmployeeMapper extends HttpServlet {
    private final IEmployeeService employeeService;
    private final ObjectMapper mapper = new ObjectMapper();

    public ServletGetEmployeeMapper() {
        this.employeeService = EmployeeService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/getEmployeeMapper.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = mapper.readValue(req.getInputStream(), long.class);

        Employee employee = employeeService.getEmployee(id);

        if (employee != null) {
            req.setAttribute("employee", employee.toString());
        } else {
            req.setAttribute("info", "Такого пользователя не существует");
        }
        req.getRequestDispatcher("views/getEmployee.jsp").forward(req, resp);
    }


}

