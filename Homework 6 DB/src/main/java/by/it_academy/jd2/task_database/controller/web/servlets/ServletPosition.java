package by.it_academy.jd2.task_database.controller.web.servlets;

import by.it_academy.jd2.task_database.model.Employee;
import by.it_academy.jd2.task_database.model.Position;
import by.it_academy.jd2.task_database.view.ApplicationUtil;
import by.it_academy.jd2.task_database.view.api.IEmployeeServiceHibernate;
import by.it_academy.jd2.task_database.view.api.IPositionServiceHibernate;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "ServletPosition", urlPatterns = "/position")
public class ServletPosition extends HttpServlet {
    private final IPositionServiceHibernate positionServiceHibernate;
    private final IEmployeeServiceHibernate employeeServiceHibernate;
    private final ObjectMapper mapper = new ObjectMapper();

    public ServletPosition() {
        this.positionServiceHibernate = ApplicationUtil.getContext().getBean("positionServiceHibernate", IPositionServiceHibernate.class);
        this.employeeServiceHibernate = ApplicationUtil.getContext().getBean("employeeServiceHibernate", IEmployeeServiceHibernate.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String page = req.getParameter("page");

        if (id == null && page == null) {
            req.getRequestDispatcher("views/addPositionMapper.jsp").forward(req, resp);
        }
        if (id == null && page != null) {
            long limit = 10;
            long countAllEntries = positionServiceHibernate.getCountAllEntries();
            long pageCount = (long) Math.ceil((double) countAllEntries / limit);
            Collection<Position> allPositions = positionServiceHibernate.getAllPositionsLimit(limit, Long.parseLong(page));

            req.setAttribute("allPositions", allPositions);
            req.setAttribute("pageCount", pageCount);
            req.setAttribute("page", page);
            req.getRequestDispatcher("views/allPositions.jsp").forward(req, resp);
        }
        if (id != null && page != null) {
            Position position = positionServiceHibernate.getPosition(Long.parseLong(id));
            long countAllEntriesByPosition = employeeServiceHibernate.getCountAllEntriesByPosition(Long.parseLong(id));
            long limit = 10;
            long pageCount = (long) Math.ceil((double) countAllEntriesByPosition / limit);
            Collection<Employee> employersByPosition = employeeServiceHibernate.getEmployersByPositionLimit(Long.parseLong(id), limit, Long.parseLong(page));

            if (position != null) {
                req.setAttribute("position", position);
                req.setAttribute("employersByPosition", employersByPosition);
                req.setAttribute("pageCount", pageCount);
                req.setAttribute("page", page);
            } else {
                req.setAttribute("info", "Такой должности не существует");
            }
            req.getRequestDispatcher("views/getPosition.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Position position = mapper.readValue(req.getInputStream(), Position.class);

        long id = positionServiceHibernate.addPosition(position);

        if (id > 0) {
            req.setAttribute("id", id);
            req.setAttribute("info", "Должность успешно добавлена с id=");
        } else {
            req.setAttribute("infoErr", "Должность не добавлена");
        }
        req.getRequestDispatcher("views/addPositionMapper.jsp").forward(req, resp);
    }
}
