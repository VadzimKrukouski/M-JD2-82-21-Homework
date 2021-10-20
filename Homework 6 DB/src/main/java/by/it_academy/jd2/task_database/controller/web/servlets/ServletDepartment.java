package by.it_academy.jd2.task_database.controller.web.servlets;

import by.it_academy.jd2.task_database.model.Department;
import by.it_academy.jd2.task_database.model.Employee;
import by.it_academy.jd2.task_database.view.util.ApplicationUtil;
import by.it_academy.jd2.task_database.view.api.IDepartmentServiceHibernate;
import by.it_academy.jd2.task_database.view.api.IEmployeeServiceHibernate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

//@WebServlet(name = "ServletDepartment", urlPatterns = "/department")
@Controller
@RequestMapping ("/department")
public class ServletDepartment /*extends HttpServlet*/ {
    private final IDepartmentServiceHibernate departmentServiceHibernate;
    private final IEmployeeServiceHibernate employeeServiceHibernate;
    private final ObjectMapper mapper = new ObjectMapper();

//    public ServletDepartment() {
//        this.departmentServiceHibernate = ApplicationUtil.getContext().getBean("departmentServiceHibernate", IDepartmentServiceHibernate.class);
//        this.employeeServiceHibernate = ApplicationUtil.getContext().getBean("employeeServiceHibernate", IEmployeeServiceHibernate.class);
//    }


    public ServletDepartment(IDepartmentServiceHibernate departmentServiceHibernate,
                             IEmployeeServiceHibernate employeeServiceHibernate) {
        this.departmentServiceHibernate = departmentServiceHibernate;
        this.employeeServiceHibernate = employeeServiceHibernate;
    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String page = req.getParameter("page");
//        String id = req.getParameter("id");
//
//        if (page == null && id == null) {
//            Collection<Department> allDepartments = departmentServiceHibernate.getAllDepartments();
//            req.setAttribute("allDepartments", allDepartments);
//            req.getRequestDispatcher("views/addDepartmentMapper.jsp").forward(req, resp);
//        }
//        if (page != null && id == null) {
//            long limit = 10;
//            long countAllEntries = departmentServiceHibernate.getCountAllEntries();
//            long pageCount = (long) Math.ceil((double) countAllEntries / limit);
//            Collection<Department> allDepartments = departmentServiceHibernate.getAllDepartmentsLimit(limit, Long.parseLong(page));
//            req.setAttribute("allDepartments", allDepartments);
//            req.setAttribute("pageCount", pageCount);
//            req.setAttribute("page", page);
//            req.getRequestDispatcher("views/allDepartments.jsp").forward(req, resp);
//        }
//        if (page != null && id != null) {
//            Department department = departmentServiceHibernate.getDepartment(Long.parseLong(id));
//            long countAllEntriesByDepartment = employeeServiceHibernate.getCountAllEntriesByDepartment(Long.parseLong(id));
//            long limit = 10;
//            long pageCount = (long) Math.ceil((double) countAllEntriesByDepartment / limit);
//            Collection<Employee> employersByDepartmentLimit = employeeServiceHibernate.getEmployersByDepartmentLimit(Long.parseLong(id), limit, Long.parseLong(page));
//            if (department != null) {
//                req.setAttribute("department", department);
//                req.setAttribute("employersByDepartment", employersByDepartmentLimit);
//                req.setAttribute("pageCount", pageCount);
//                req.setAttribute("page", page);
//            } else {
//                req.setAttribute("info", "Такого отдела не существует");
//            }
//            req.getRequestDispatcher("views/getDepartment.jsp").forward(req, resp);
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Department department = mapper.readValue(req.getInputStream(), Department.class);
//
//        long id = departmentServiceHibernate.addDepartment(department);
//
//        Collection<Department> allDepartments = departmentServiceHibernate.getAllDepartments();
//        req.setAttribute("allDepartments", allDepartments);
//
//        if (id > 0) {
//            req.setAttribute("id", id);
//            req.setAttribute("info", "Отдел успешно добавлен с id=");
//
//        } else {
//            req.setAttribute("infoErr", "Отдел не добавлен");
//        }
//        req.getRequestDispatcher("views/addDepartmentMapper.jsp").forward(req, resp);
//    }
}
