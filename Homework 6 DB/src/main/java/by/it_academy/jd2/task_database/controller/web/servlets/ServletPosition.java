package by.it_academy.jd2.task_database.controller.web.servlets;

import by.it_academy.jd2.task_database.model.Employee;
import by.it_academy.jd2.task_database.model.EmployeeDTO;
import by.it_academy.jd2.task_database.model.Position;
import by.it_academy.jd2.task_database.view.api.IEmployeeServiceHibernate;
import by.it_academy.jd2.task_database.view.api.IPositionServiceHibernate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

//@WebServlet(name = "ServletPosition", urlPatterns = "/position")
@Controller
@RequestMapping("/position")
public class ServletPosition /*extends HttpServlet*/ {
    private final IPositionServiceHibernate positionServiceHibernate;
    private final IEmployeeServiceHibernate employeeServiceHibernate;
    private static final long LIMIT = 10;

    //    public ServletPosition() {
//        this.positionServiceHibernate = ApplicationUtil.getContext().getBean("positionServiceHibernate", IPositionServiceHibernate.class);
//        this.employeeServiceHibernate = ApplicationUtil.getContext().getBean("employeeServiceHibernate", IEmployeeServiceHibernate.class);
//    }
    public ServletPosition(IPositionServiceHibernate positionServiceHibernate,
                           IEmployeeServiceHibernate employeeServiceHibernate) {
        this.positionServiceHibernate = positionServiceHibernate;
        this.employeeServiceHibernate = employeeServiceHibernate;
    }

    @GetMapping
    public String getPositionPage(){
        return "addPositionMapper";
    }

    @GetMapping( value = "/all")
    public String getAllPositionPage(@RequestParam(value = "page", required = false) Long page,
                                     Model model){
        long countAllEntries = positionServiceHibernate.getCountAllEntries();
        long pageCount = getPageCount(countAllEntries);
        Collection<Position> allPositions = positionServiceHibernate.getAllPositionsLimit(LIMIT, page);
        model.addAttribute("allPositions", allPositions);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("page", page);
        return "allPositions";
    }

    @GetMapping( value = "/{id}")
    public String getPositionById(@PathVariable("id") Long id,
                                  @RequestParam(value = "page", required = false) Long page,
                                  Model model){
        Position position = positionServiceHibernate.getPosition(id);
        EmployeeDTO employeeDTO = new EmployeeDTO.Builder().position(position).build();
        long countAllEntriesByPosition = employeeServiceHibernate.getCountAllEntriesLastVersion(employeeDTO);
        long pageCount = getPageCount(countAllEntriesByPosition);
        long offset=LIMIT*(page-1);
        Collection<Employee> employersByPosition = employeeServiceHibernate.getAllEmployersLastVersion(employeeDTO, LIMIT, offset);
        if (position!=null){
            model.addAttribute("position", position);
            model.addAttribute("employersByPosition", employersByPosition);
            model.addAttribute("pageCount", pageCount);
            model.addAttribute("page", page);
        } else {
            model.addAttribute("info", "Такой должности не существует");
        }
        return "getPosition";
    }

    private long getPageCount(long countAllEntries) {
        return (long) Math.ceil((double) countAllEntries / ServletPosition.LIMIT);
    }




//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String id = req.getParameter("id");
//        String page = req.getParameter("page");
//
//        if (id == null && page == null) {
//            req.getRequestDispatcher("views/addPositionMapper.jsp").forward(req, resp);
//        }
//        if (id == null && page != null) {
//            long limit = 10;
//            long countAllEntries = positionServiceHibernate.getCountAllEntries();
//            long pageCount = (long) Math.ceil((double) countAllEntries / limit);
//            Collection<Position> allPositions = positionServiceHibernate.getAllPositionsLimit(limit, Long.parseLong(page));
//
//            req.setAttribute("allPositions", allPositions);
//            req.setAttribute("pageCount", pageCount);
//            req.setAttribute("page", page);
//            req.getRequestDispatcher("views/allPositions.jsp").forward(req, resp);
//        }
//        if (id != null && page != null) {
//            Position position = positionServiceHibernate.getPosition(Long.parseLong(id));
//            long countAllEntriesByPosition = employeeServiceHibernate.getCountAllEntriesByPosition(Long.parseLong(id));
//            long limit = 10;
//            long pageCount = (long) Math.ceil((double) countAllEntriesByPosition / limit);
//            Collection<Employee> employersByPosition = employeeServiceHibernate.getEmployersByPositionLimit(Long.parseLong(id), limit, Long.parseLong(page));
//
//            if (position != null) {
//                req.setAttribute("position", position);
//                req.setAttribute("employersByPosition", employersByPosition);
//                req.setAttribute("pageCount", pageCount);
//                req.setAttribute("page", page);
//            } else {
//                req.setAttribute("info", "Такой должности не существует");
//            }
//            req.getRequestDispatcher("views/getPosition.jsp").forward(req, resp);
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Position position = mapper.readValue(req.getInputStream(), Position.class);
//
//        long id = positionServiceHibernate.addPosition(position);
//
//        if (id > 0) {
//            req.setAttribute("id", id);
//            req.setAttribute("info", "Должность успешно добавлена с id=");
//        } else {
//            req.setAttribute("infoErr", "Должность не добавлена");
//        }
//        req.getRequestDispatcher("views/addPositionMapper.jsp").forward(req, resp);
//    }
}
