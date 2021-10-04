package by.it_academy.jd2.task_database.controller.web.servlets;

import by.it_academy.jd2.task_database.model.Department;
import by.it_academy.jd2.task_database.model.Employee;
import by.it_academy.jd2.task_database.view.DepartmentService;
import by.it_academy.jd2.task_database.view.DepartmentServiceHibernate;
import by.it_academy.jd2.task_database.view.EmployeeService;
import by.it_academy.jd2.task_database.view.api.IDepartmentService;
import by.it_academy.jd2.task_database.view.api.IDepartmentServiceHibernate;
import by.it_academy.jd2.task_database.view.api.IEmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "ServletGetDepartment", urlPatterns = "/getDepartment")
public class ServletGetDepartment extends HttpServlet {
    private final IDepartmentService departmentService;
    private final IDepartmentServiceHibernate departmentServiceHibernate;
    private final IEmployeeService employeeService;

    public ServletGetDepartment() {
        this.departmentService = DepartmentService.getInstance();
        this.departmentServiceHibernate = DepartmentServiceHibernate.getInstance();
        this.employeeService = EmployeeService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

//        Department department = departmentService.getDepartment(Long.parseLong(id));
        Department department = departmentServiceHibernate.getDepartment(Long.parseLong(id));

        long countAllEntriesByDepartment = employeeService.getCountAllEntriesByDepartment(Long.parseLong(id));
        long limit = 10;
        long pageCount= (long) Math.ceil((double) countAllEntriesByDepartment/limit);
        String page = req.getParameter("page");

        Collection<Employee> employersByDepartmentLimit = employeeService.getEmployersByDepartmentLimit(Long.parseLong(id), limit, Long.parseLong(page));

        if (department != null) {
            req.setAttribute("department", department);
            req.setAttribute("employersByDepartment", employersByDepartmentLimit);
            req.setAttribute("pageCount", pageCount);
            req.setAttribute("page", page);
        } else {
            req.setAttribute("info", "Такого отдела не существует");
        }
        req.getRequestDispatcher("views/getDepartment.jsp").forward(req,resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
