package by.it_academy.jd2.task_database.controller.web.servlets;

import by.it_academy.jd2.task_database.model.Position;
import by.it_academy.jd2.task_database.view.PositionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (name = "ServletAddPosition", urlPatterns = "/addPosition")
public class ServletAddPosition extends HttpServlet {
    private final PositionService positionService;

    public ServletAddPosition() {
        this.positionService = PositionService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/addPosition.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");

        Position position = new Position();
        position.setName(name);

        long id = positionService.addPosition(position);

        if (id>0){
            req.setAttribute("id", id);
            req.setAttribute("info", "Должность успешно добавлена с id=");
        } else {
            req.setAttribute("infoErr", "Должность не добавлена");
        }
        req.getRequestDispatcher("views/addPosition.jsp").forward(req,resp);
    }
}
