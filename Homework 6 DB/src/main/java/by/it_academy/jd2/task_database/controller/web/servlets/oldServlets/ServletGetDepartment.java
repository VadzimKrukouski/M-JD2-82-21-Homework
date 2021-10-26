package by.it_academy.jd2.task_database.controller.web.servlets.oldServlets;

import by.it_academy.jd2.task_database.model.Department;
import by.it_academy.jd2.task_database.model.Employee;
import by.it_academy.jd2.task_database.view.util.ApplicationUtil;
import by.it_academy.jd2.task_database.view.api.IDepartmentServiceHibernate;
import by.it_academy.jd2.task_database.view.api.IEmployeeServiceHibernate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "ServletGetDepartment", urlPatterns = "/getDepartment")
public class ServletGetDepartment extends HttpServlet {
    private final IDepartmentServiceHibernate departmentServiceHibernate;
    private final IEmployeeServiceHibernate employeeServiceHibernate;

    public ServletGetDepartment() {
        this.departmentServiceHibernate = ApplicationUtil.getContext().getBean("departmentServiceHibernate", IDepartmentServiceHibernate.class);
        this.employeeServiceHibernate = ApplicationUtil.getContext().getBean("employeeServiceHibernate", IEmployeeServiceHibernate.class);
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String id = req.getParameter("id");
//
//        Department department = departmentServiceHibernate.getDepartment(Long.parseLong(id));
//
//        long countAllEntriesByDepartment = employeeServiceHibernate.getCountAllEntriesByDepartment(Long.parseLong(id));
//        long limit = 10;
//        long pageCount= (long) Math.ceil((double) countAllEntriesByDepartment/limit);
//        String page = req.getParameter("page");
//
//        Collection<Employee> employersByDepartmentLimit = employeeServiceHibernate.getEmployersByDepartmentLimit(Long.parseLong(id), limit, Long.parseLong(page));
//        if (department != null) {
//            req.setAttribute("department", department);
//            req.setAttribute("employersByDepartment", employersByDepartmentLimit);
//            req.setAttribute("pageCount", pageCount);
//            req.setAttribute("page", page);
//        } else {
//            req.setAttribute("info", "Такого отдела не существует");
//        }
//        req.getRequestDispatcher("views/getDepartment.jsp").forward(req,resp);
//
//
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
