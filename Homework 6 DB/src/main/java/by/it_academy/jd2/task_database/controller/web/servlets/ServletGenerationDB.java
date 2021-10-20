package by.it_academy.jd2.task_database.controller.web.servlets;

import by.it_academy.jd2.task_database.view.util.ApplicationUtil;
import by.it_academy.jd2.task_database.view.util.DataBaseGenerationByData;
import by.it_academy.jd2.task_database.view.api.IDataBaseGenerationByData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "ServletGenerationDB", urlPatterns = "/generationDB")
@Controller
@RequestMapping("/generationDB")
public class ServletGenerationDB /*extends HttpServlet*/ {
    private final IDataBaseGenerationByData dataBaseGenerationByData;

//    public ServletGenerationDB() {
//        this.dataBaseGenerationByData = ApplicationUtil.getContext().getBean("dataBaseGenerationByData", DataBaseGenerationByData.class);
//    }

    public ServletGenerationDB(IDataBaseGenerationByData dataBaseGenerationByData) {
        this.dataBaseGenerationByData = dataBaseGenerationByData;
    }


//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        dataBaseGenerationByData.generationPosition();
//        dataBaseGenerationByData.generationDepartment();
//        dataBaseGenerationByData.generationEmployers();
//
//        req.setAttribute("info", "База успешно сгенерирована");
//        req.getRequestDispatcher("views/generationDB.jsp");
//    }
}
