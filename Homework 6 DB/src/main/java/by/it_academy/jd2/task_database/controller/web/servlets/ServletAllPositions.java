package by.it_academy.jd2.task_database.controller.web.servlets;

import by.it_academy.jd2.task_database.model.Position;
import by.it_academy.jd2.task_database.view.PositionService;
import by.it_academy.jd2.task_database.view.api.IPositionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "ServletAllPositions", urlPatterns = "/allPositions")
public class ServletAllPositions extends HttpServlet {
    private final IPositionService positionService;

    public ServletAllPositions() {
        this.positionService = PositionService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long limit = 20;
        long countAllEntries = positionService.getCountAllEntries();
        long pageCount = (long) Math.ceil((double) countAllEntries/limit);
        String page = req.getParameter("page");
        Collection<Position> allPositions = positionService.getAllPositionsLimit(limit, Long.parseLong(page));

        req.setAttribute("allPositions", allPositions);
        req.setAttribute("pageCount", pageCount);
        req.setAttribute("page", page);
        req.getRequestDispatcher("views/allPositions.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}