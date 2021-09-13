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
        Collection<Employee> employersByPosition = employeeService.getEmployersByPosition(Long.parseLong(id));

        if (position!=null){
            req.setAttribute("position", position.getName());
            req.setAttribute("employersByPosition", employersByPosition);
        } else {
            req.setAttribute("info", "Такой должности не существует");
        }
        req.getRequestDispatcher("views/getPosition.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String id = req.getParameter("id");
//
//        Position position = positionService.getPosition(Long.parseLong(id));
//
//        if (position!=null){
//            req.setAttribute("position", position.toString());
//        } else {
//            req.setAttribute("info", "Такой должности не существует");
//        }
//        req.getRequestDispatcher("views/getPosition.jsp").forward(req,resp);


    }
}
