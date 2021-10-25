package by.it_academy.jd2.task_database.controller.web.servlets;

import by.it_academy.jd2.task_database.model.Department;
import by.it_academy.jd2.task_database.model.Employee;
import by.it_academy.jd2.task_database.model.EmployeeDTO;
import by.it_academy.jd2.task_database.model.Position;
import by.it_academy.jd2.task_database.view.util.ApplicationUtil;
import by.it_academy.jd2.task_database.view.api.IDepartmentServiceHibernate;
import by.it_academy.jd2.task_database.view.api.IEmployeeServiceHibernate;
import by.it_academy.jd2.task_database.view.api.IPositionServiceHibernate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

//@WebServlet (name = "ServletEmployee", urlPatterns = "/employee")
@Controller
@RequestMapping("/employee")
public class ServletEmployee /*extends HttpServlet*/ {
    private final IEmployeeServiceHibernate employeeServiceHibernate;
    private final IDepartmentServiceHibernate departmentServiceHibernate;
    private final IPositionServiceHibernate positionServiceHibernate;
    private static final long LIMIT = 10;

    //    public ServletEmployee() {
//        this.employeeServiceHibernate = ApplicationUtil.getContext().getBean("employeeServiceHibernate", IEmployeeServiceHibernate.class);
//        this.departmentServiceHibernate = ApplicationUtil.getContext().getBean("departmentServiceHibernate", IDepartmentServiceHibernate.class);
//        this.positionServiceHibernate = ApplicationUtil.getContext().getBean("positionServiceHibernate", IPositionServiceHibernate.class);
//    }
    public ServletEmployee(IEmployeeServiceHibernate employeeServiceHibernate,
                           IDepartmentServiceHibernate departmentServiceHibernate,
                           IPositionServiceHibernate positionServiceHibernate) {
        this.employeeServiceHibernate = employeeServiceHibernate;
        this.departmentServiceHibernate = departmentServiceHibernate;
        this.positionServiceHibernate = positionServiceHibernate;
    }

    @GetMapping
    public String getEmployeePage(Model model) {
        Collection<Department> allDepartments = departmentServiceHibernate.getAllDepartments();
        Collection<Position> allPositions = positionServiceHibernate.getAllPositions();
        model.addAttribute("allDepartments", allDepartments);
        model.addAttribute("allPositions", allPositions);
        return "addEmployeeMapper";
    }

    @GetMapping(value = "/all")
    public String getAllEmployeePage(@RequestParam(value = "page", required = false) Long page,
                                     Model model) {
        long limit = LIMIT;
        long countAllEntries = employeeServiceHibernate.getCountAllEntries();
        long pageCount = getPageCount(limit, countAllEntries);
        Collection<Employee> allEmployers = employeeServiceHibernate.getALLEmployersLimit(limit, page);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("page", page);
        model.addAttribute("allEmployers", allEmployers);
        return "allEmployeeLimit";
    }

    @GetMapping(value = "/{id}")
    public String getEmployeeById(@PathVariable("id") Long id, Model model) {
        if (id != 0) {
            Employee employee = employeeServiceHibernate.getEmployee(id);
            if (employee != null) {
                model.addAttribute("employee", employee);
            } else {
                model.addAttribute("info", "Такого пользователя не существует");
            }
            return "aboutEmployee";
        } else {
            return "getEmployee";
        }
    }

    @GetMapping(value = "/about")
    public String getInfoEmployee(@RequestParam(value = "id") Long id,
                                  Model model) {
        Employee employee = employeeServiceHibernate.getEmployee(id);
        model.addAttribute("employee", employee);
        return "aboutEmployee";
    }

    private long getPageCount(long limit, long countAllEntries) {
        return (long) Math.ceil((double) countAllEntries / limit);
    }

    @GetMapping(value = "/search")
    public String getSearchEmployee(Model model, @RequestParam(value = "name") String name,
                                    @RequestParam(value = "salary1") Long salaryFrom,
                                    @RequestParam(value = "salary2") Long salaryTo) {
        EmployeeDTO employeeDTO = new EmployeeDTO.Builder()
                .name(name)
                .salaryFrom(salaryFrom)
                .salaryTo(salaryTo)
                .build();
        int page = 1;
        long countAllEntriesForSearch = employeeServiceHibernate.getCountAllEntriesLastVersion(employeeDTO);
//        long countAllEntriesForSearch = employeeServiceHibernate.getCountAllEntriesForSearch(employeeDTO);
        long pageCount = getPageCount(LIMIT, countAllEntriesForSearch);
        long offset=LIMIT*(page-1);
        Collection<Employee> employeesForSearch = employeeServiceHibernate.getAllEmployersLastVersion(employeeDTO, LIMIT, offset);
//        Collection<Employee> employeesForSearch = employeeServiceHibernate.getEmployeesForSearch(name, salaryFrom, salaryTo, LIMIT, page);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("page", page);
        model.addAttribute("allEmployers", employeesForSearch);
        return "allEmployeeLimit";
    }


//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String id = req.getParameter("id");
//        String page = req.getParameter("page");
//        if (id==null && page==null){
//            Collection<Department> allDepartments = departmentServiceHibernate.getAllDepartments();
//            Collection<Position> allPositions = positionServiceHibernate.getAllPositions();
//            req.setAttribute("allDepartments", allDepartments);
//            req.setAttribute("allPositions", allPositions);
//            req.getRequestDispatcher("views/addEmployeeMapper.jsp").forward(req, resp);
//        }
//        if (id==null && page!=null){
//            long limit = 10;
//            long countAllEntries = employeeServiceHibernate.getCountAllEntries();
//            long pageCount = (long) Math.ceil((double) countAllEntries / limit);
//            Collection<Employee> allEmployers = employeeServiceHibernate.getALLEmployersLimit(limit, Long.parseLong(page));
//            req.setAttribute("pageCount", pageCount);
//            req.setAttribute("page", page);
//            req.setAttribute("allEmployers", allEmployers);
//            req.getRequestDispatcher("views/allEmployeeLimit.jsp").forward(req,resp);
//        }
//        if (id!=null && page==null){
//            if (!id.equals("start")) {
//                Employee employee = employeeServiceHibernate.getEmployee(Long.parseLong(id));
//                if (employee != null) {
//                    req.setAttribute("employee", employee.toString());
//                } else {
//                    req.setAttribute("info", "Такого пользователя не существует");
//                }
//            }
//            req.getRequestDispatcher("views/getEmployee.jsp").forward(req, resp);
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String id = req.getParameter("id");
//
//        if (id!=null){
//            Employee employee = employeeServiceHibernate.getEmployee(Long.parseLong(id));
//            if (employee!=null){
//                req.setAttribute("employee", employee.toString());
//            } else {
//                req.setAttribute("info", "Такого пользователя не существует");
//            }
//            req.getRequestDispatcher("views/getEmployee.jsp").forward(req, resp);
//        } else {
//            Employee employee = mapper.readValue(req.getInputStream(), Employee.class);
//            long idEmployee = employeeServiceHibernate.addEmployee(employee);
//            if (idEmployee > 0) {
//                req.setAttribute("id", idEmployee);
//                req.setAttribute("info", "Сотрудник успешно добавлен с id=");
//            } else {
//                req.setAttribute("infoErr", "Сотрудник не добавлен");
//            }
////        req.getRequestDispatcher("views/addEmployeeMapper.jsp").forward(req,resp);
//            resp.sendRedirect("addEmployeeMapper.jsp");
//        }
//    }
}
