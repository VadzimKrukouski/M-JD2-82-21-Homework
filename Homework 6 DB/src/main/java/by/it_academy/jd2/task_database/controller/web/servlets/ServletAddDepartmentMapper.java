package by.it_academy.jd2.task_database.controller.web.servlets;

import by.it_academy.jd2.task_database.model.Department;
import by.it_academy.jd2.task_database.view.DepartmentService;
import by.it_academy.jd2.task_database.view.DepartmentServiceHibernate;
import by.it_academy.jd2.task_database.view.api.IDepartmentService;
import by.it_academy.jd2.task_database.view.api.IDepartmentServiceHibernate;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet (name = "ServletAddDepartmentMapper" , urlPatterns = "/addDepartmentMapper")
public class ServletAddDepartmentMapper extends HttpServlet {
    private final IDepartmentServiceHibernate departmentServiceHibernate;
    private final IDepartmentService departmentService;
    private final ObjectMapper mapper = new ObjectMapper();

    public ServletAddDepartmentMapper() {
        this.departmentService = DepartmentService.getInstance();
        this.departmentServiceHibernate = DepartmentServiceHibernate();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Collection<Department> allDepartments = departmentService.getAllDepartments();
        Collection<Department> allDepartments = departmentServiceHibernate.getAllDepartments();
        req.setAttribute("allDepartments", allDepartments);

        req.getRequestDispatcher("views/addDepartmentMapper.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Department department = mapper.readValue(req.getInputStream(), Department.class);

//        long id = departmentService.addDepartment(department);
        long id = departmentServiceHibernate.addDepartment(department);

//        Collection<Department> allDepartments = departmentService.getAllDepartments();
        Collection<Department> allDepartments = departmentServiceHibernate.getAllDepartments();
        req.setAttribute("allDepartments", allDepartments);

        if (id>0){
            req.setAttribute("id", id);
            req.setAttribute("info", "Отдел успешно добавлен с id=");

        } else {
            req.setAttribute("infoErr", "Отдел не добавлен");
        }
        req.getRequestDispatcher("views/addDepartmentMapper.jsp").forward(req,resp);
    }
}
