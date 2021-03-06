package by.it_academy.jd2.task_database.controller.web.servlets.oldServlets;

import by.it_academy.jd2.task_database.model.Employee;
import by.it_academy.jd2.task_database.view.util.ApplicationUtil;
import by.it_academy.jd2.task_database.view.api.IEmployeeServiceHibernate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

//@WebServlet(name = "ServletSearch", urlPatterns = "/search")
public class ServletSearch extends HttpServlet {
    private final IEmployeeServiceHibernate employeeServiceHibernate;

    public ServletSearch() {
        this.employeeServiceHibernate = ApplicationUtil.getContext().getBean("employeeServiceHibernate", IEmployeeServiceHibernate.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String name = req.getParameter("name");
//        long salary1 = Long.parseLong(req.getParameter("salary1"));
//        long salary2 = Long.parseLong(req.getParameter("salary2"));
//        String page = "1";
//        long limit = 10;
//        long countAllEntriesForSearch = employeeServiceHibernate.getCountAllEntriesForSearch(name, salary1, salary2);
//        long pageCount = (long) Math.ceil((double) countAllEntriesForSearch / limit);
//
//        Collection<Employee> employeesForSearch = employeeServiceHibernate.getEmployeesForSearch(name, salary1, salary2, limit, Long.parseLong(page));
//
//        req.setAttribute("pageCount", pageCount);
//        req.setAttribute("page", page);
//        req.setAttribute("allEmployers", employeesForSearch);
//        req.getRequestDispatcher("views/allEmployeeLimit.jsp").forward(req, resp);


    }
}
