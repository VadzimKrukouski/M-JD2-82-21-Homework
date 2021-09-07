package by.it_academy.jd2.task_database.controller.web.servlets;

import by.it_academy.jd2.task_database.model.Department;
import by.it_academy.jd2.task_database.view.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletAddDepartment", urlPatterns = "/addDepartment")
public class ServletAddDepartment extends HttpServlet {
    private final DepartmentService departmentService;

    public ServletAddDepartment() {
        this.departmentService = DepartmentService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/addDepartment.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        long parentDepartmentId = Long.parseLong(req.getParameter("parentName"));

        Department parentDepartment = departmentService.getDepartment(parentDepartmentId);

        Department department = new Department();
        department.setName(name);
        department.setParentDepartment(parentDepartment);

        long id = departmentService.addDepartment(department);
        if (id>0){
            req.setAttribute("id", id);
            req.setAttribute("info", "Отдел успешно добавлен с id=");
        } else {
            req.setAttribute("info", "Отдел не добавлен");
        }
        req.getRequestDispatcher("views/addDepartment.jsp").forward(req,resp);
    }
}
