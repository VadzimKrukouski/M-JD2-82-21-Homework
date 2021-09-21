package by.it_academy.jd2.task_database.controller.web.servlets;

import by.it_academy.jd2.task_database.model.Employee;
import by.it_academy.jd2.task_database.model.Position;
import by.it_academy.jd2.task_database.view.EmployeeService;
import by.it_academy.jd2.task_database.view.PositionService;
import by.it_academy.jd2.task_database.view.api.IEmployeeService;
import by.it_academy.jd2.task_database.view.api.IPositionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "ServletGetPosition", urlPatterns = "/getPosition")
public class ServletGetPosition extends HttpServlet {
    private final IPositionService positionService;
    private final IEmployeeService employeeService;

    public ServletGetPosition() {
        this.positionService = PositionService.getInstance();
        this.employeeService = EmployeeService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        Position position = positionService.getPosition(Long.parseLong(id));

        long countAllEntriesByPosition = employeeService.getCountAllEntriesByPosition(Long.parseLong(id));

        long limit = 10;
        long pageCount = (long) Math.ceil((double) countAllEntriesByPosition/limit);
        String page = req.getParameter("page");

        Collection<Employee> employersByPosition = employeeService.getEmployersByPositionLimit(Long.parseLong(id), limit, Long.parseLong(page));

        if (position!=null){
            req.setAttribute("position", position);
            req.setAttribute("employersByPosition", employersByPosition);
            req.setAttribute("pageCount", pageCount);
            req.setAttribute("page", page);
        } else {
            req.setAttribute("info", "Такой должности не существует");
        }
        req.getRequestDispatcher("views/getPosition.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
