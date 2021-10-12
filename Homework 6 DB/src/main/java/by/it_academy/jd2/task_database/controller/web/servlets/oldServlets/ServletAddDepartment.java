package by.it_academy.jd2.task_database.controller.web.servlets.oldServlets;

import by.it_academy.jd2.task_database.model.Department;
import by.it_academy.jd2.task_database.view.oldServices.DepartmentService;
import by.it_academy.jd2.task_database.view.api.IDepartmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "ServletAddDepartment", urlPatterns = "/addDepartment")
public class ServletAddDepartment extends HttpServlet {
    private final IDepartmentService departmentService;

    public ServletAddDepartment() {
        this.departmentService = DepartmentService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<Department> allDepartments = departmentService.getAllDepartments();

        req.setAttribute("allDepartments", allDepartments);
        req.getRequestDispatcher("views/addDepartment.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Department department = new Department();

        String name = req.getParameter("name");
        if (req.getParameter("parentName")==null){
            department.setName(name);
        } else {
            long parentDepartmentId = Long.parseLong(req.getParameter("parentName"));
            Department parentDepartment = departmentService.getDepartment(parentDepartmentId);
            department.setName(name);
            department.setParentDepartment(parentDepartment);
        }

        long id = departmentService.addDepartment(department);

        Collection<Department> allDepartments = departmentService.getAllDepartments();
        req.setAttribute("allDepartments", allDepartments);

        if (id>0){
            req.setAttribute("id", id);
            req.setAttribute("info", "Отдел успешно добавлен с id=");

        } else {
            req.setAttribute("info", "Отдел не добавлен");
        }
        req.getRequestDispatcher("views/addDepartment.jsp").forward(req,resp);
    }
}
