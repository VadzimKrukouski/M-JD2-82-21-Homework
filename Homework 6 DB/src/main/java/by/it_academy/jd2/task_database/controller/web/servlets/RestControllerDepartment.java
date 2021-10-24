package by.it_academy.jd2.task_database.controller.web.servlets;

import by.it_academy.jd2.task_database.model.Department;
import by.it_academy.jd2.task_database.view.api.IDepartmentServiceHibernate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("api/department")
public class RestControllerDepartment {
    private final IDepartmentServiceHibernate departmentServiceHibernate;

    public RestControllerDepartment(IDepartmentServiceHibernate departmentServiceHibernate) {
        this.departmentServiceHibernate = departmentServiceHibernate;
    }

    @PostMapping
    public void addNewDepartment(@RequestBody Department department, HttpServletResponse resp, Model model) throws IOException {
        long id = departmentServiceHibernate.addDepartment(department);

        Collection<Department> allDepartments = departmentServiceHibernate.getAllDepartments();
        model.addAttribute("allDepartments", allDepartments);
        model.addAttribute("id", id);
        model.addAttribute("info", "Отдел успешно добавлен с id=");

        resp.sendRedirect("addDepartmentMapper");
    }
}
