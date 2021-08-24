package by.it_academy.jd2.task_messenger_load_save.controller.web.servlets;

import by.it_academy.jd2.task_messenger_load_save.storage.AboutStorage;
import by.it_academy.jd2.task_messenger_load_save.storage.api.IAboutStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet (name = "ServletAbout", urlPatterns = "/about")
public class ServletAbout extends HttpServlet {
    private final IAboutStorage aboutStorage;

    public ServletAbout() {
        this.aboutStorage = AboutStorage.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        Date date = aboutStorage.getAbout();
        String storageOption = aboutStorage.getStorageOption();
        writer.write("<p>" + "Дата запуска приложения: " + date.toString() + "</p>");
        writer.write("<p>" + "Вариант сохранения информации: " + storageOption + "<p>");
    }
}
