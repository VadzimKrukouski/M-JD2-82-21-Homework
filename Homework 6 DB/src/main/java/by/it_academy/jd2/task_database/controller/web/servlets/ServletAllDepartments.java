package by.it_academy.jd2.task_database.controller.web.servlets;

import by.it_academy.jd2.task_database.model.Department;
import by.it_academy.jd2.task_database.view.ApplicationUtil;
import by.it_academy.jd2.task_database.view.DepartmentService;
import by.it_academy.jd2.task_database.view.DepartmentServiceHibernate;
import by.it_academy.jd2.task_database.view.api.IDepartmentService;
import by.it_academy.jd2.task_database.view.api.IDepartmentServiceHibernate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "ServletAllDepartments", urlPatterns = "/allDepartments")
public class ServletAllDepartments extends HttpServlet {
//    private final IDepartmentService departmentService;
    private final IDepartmentServiceHibernate departmentServiceHibernate;

    public ServletAllDepartments() {
        this.departmentServiceHibernate = ApplicationUtil.getContext().getBean("departmentServiceHibernate", IDepartmentServiceHibernate.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long limit = 10;
//        long countAllEntries = departmentService.getCountAllEntries();
        long countAllEntries = departmentServiceHibernate.getCountAllEntries();
        long pageCount = (long) Math.ceil((double) countAllEntries / limit);
        String page = req.getParameter("page");
//        Collection<Department> allDepartments = departmentService.getAllDepartmentsLimit(limit, Long.parseLong(page));
        Collection<Department> allDepartments = departmentServiceHibernate.getAllDepartmentsLimit(limit, Long.parseLong(page));
        req.setAttribute("allDepartments", allDepartments);
        req.setAttribute("pageCount", pageCount);
        req.setAttribute("page", page);
        req.getRequestDispatcher("views/allDepartments.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
