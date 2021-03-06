package by.it_academy.jd2.task_database.controller.web.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "ServletMainPage", urlPatterns = "/")
@Controller
@RequestMapping("/")
public class ServletMainPage /*extends HttpServlet*/ {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("views/mainPage.jsp").forward(req,resp);
//    }
    @GetMapping
    public String getMainPage() {
        return "mainPage";
    }
}
