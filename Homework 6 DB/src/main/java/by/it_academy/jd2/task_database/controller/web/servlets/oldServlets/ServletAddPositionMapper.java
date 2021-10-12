package by.it_academy.jd2.task_database.controller.web.servlets.oldServlets;

import by.it_academy.jd2.task_database.model.Position;
import by.it_academy.jd2.task_database.view.util.ApplicationUtil;
import by.it_academy.jd2.task_database.view.api.IPositionServiceHibernate;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletAddPositionMapper", urlPatterns = "/addPositionMapper")
public class ServletAddPositionMapper extends HttpServlet {
    private final IPositionServiceHibernate positionServiceHibernate;
    private final ObjectMapper mapper = new ObjectMapper();

    public ServletAddPositionMapper() {
       this.positionServiceHibernate = ApplicationUtil.getContext().getBean("positionServiceHibernate", IPositionServiceHibernate.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/addPositionMapper.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Position position = mapper.readValue(req.getInputStream(), Position.class);

        long id = positionServiceHibernate.addPosition(position);

        if (id>0){
            req.setAttribute("id", id);
            req.setAttribute("info", "Должность успешно добавлена с id=");
        } else {
            req.setAttribute("infoErr", "Должность не добавлена");
        }
        req.getRequestDispatcher("views/addPositionMapper.jsp").forward(req,resp);
    }
}
