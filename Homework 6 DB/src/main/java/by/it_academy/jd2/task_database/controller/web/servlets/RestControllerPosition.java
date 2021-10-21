package by.it_academy.jd2.task_database.controller.web.servlets;

import by.it_academy.jd2.task_database.model.Position;
import by.it_academy.jd2.task_database.view.api.IPositionServiceHibernate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping ("/position")
public class RestControllerPosition {
    private final IPositionServiceHibernate positionServiceHibernate;
    private final ObjectMapper mapper = new ObjectMapper();

    public RestControllerPosition(IPositionServiceHibernate positionServiceHibernate) {
        this.positionServiceHibernate = positionServiceHibernate;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addNewPosition(HttpServletRequest req, HttpServletResponse resp, Model model) throws IOException {
        Position position = mapper.readValue(req.getInputStream(), Position.class);
        long id = positionServiceHibernate.addPosition(position);
        model.addAttribute("id", id);
        model.addAttribute("info", "Должность успешно добавлена с id=");

        resp.sendRedirect("addPositionMapper");
    }
}
