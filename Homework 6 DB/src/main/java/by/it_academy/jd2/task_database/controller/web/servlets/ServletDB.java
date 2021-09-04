package by.it_academy.jd2.task_database.controller.web.servlets;


import by.it_academy.jd2.task_database.view.ModelService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletDB", urlPatterns = "/db")
public class ServletDB extends HttpServlet {
    private final ModelService modelService;

    public ServletDB() {
        this.modelService = ModelService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
