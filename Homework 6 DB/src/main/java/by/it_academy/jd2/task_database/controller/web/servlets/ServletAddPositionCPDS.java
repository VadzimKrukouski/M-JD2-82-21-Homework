package by.it_academy.jd2.task_database.controller.web.servlets;

import by.it_academy.jd2.task_database.model.Position;
import by.it_academy.jd2.task_database.view.PositionService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletAddPositionCPDS extends HttpServlet {
    private final PositionService positionService;
    private final ObjectMapper mapper = new ObjectMapper();

    public ServletAddPositionCPDS() {
        this.positionService = PositionService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/addPosition.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Position position = mapper.readValue(req.getInputStream(), Position.class);

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
