package by.it_academy.jd2.task_database.controller.web.servlets;

import by.it_academy.jd2.task_database.model.Employee;
import by.it_academy.jd2.task_database.view.api.IEmployeeServiceHibernate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("api/employee")
public class RestControllerEmployee {
    private final IEmployeeServiceHibernate employeeServiceHibernate;

    public RestControllerEmployee(IEmployeeServiceHibernate employeeServiceHibernate) {
        this.employeeServiceHibernate = employeeServiceHibernate;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addNewEmployee(@RequestBody Employee employee, HttpServletResponse resp, Model model) throws IOException {
        long id = employeeServiceHibernate.addEmployee(employee);
        model.addAttribute("id", id);
        model.addAttribute("info", "Сотрудник успешно добавлен с id=");
        resp.sendRedirect("addEmployeeMapper");
    }


}
