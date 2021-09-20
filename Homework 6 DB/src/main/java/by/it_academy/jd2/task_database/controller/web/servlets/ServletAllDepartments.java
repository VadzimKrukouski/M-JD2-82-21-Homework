package by.it_academy.jd2.task_database.controller.web.servlets;

import by.it_academy.jd2.task_database.model.Department;
import by.it_academy.jd2.task_database.view.DepartmentService;
import by.it_academy.jd2.task_database.view.api.IDepartmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet (name = "ServletAllDepartments", urlPatterns = "/allDepartments")
public class ServletAllDepartments extends HttpServlet {
    private final IDepartmentService departmentService;

    public ServletAllDepartments() {
        this.departmentService = DepartmentService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long limit = 20;
        long countAllEntries = departmentService.getCountAllEntries();
        long pageCount = (long) Math.ceil((double) countAllEntries/limit);
        String page = req.getParameter("page");
        Collection<Department> allDepartments = departmentService.getAllDepartmentsLimit(limit, Long.parseLong(page));

        req.setAttribute("allDepartments", allDepartments);
        req.setAttribute("pageCount", pageCount);
        req.setAttribute("page", page);
        req.getRequestDispatcher("views/allDepartments.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
