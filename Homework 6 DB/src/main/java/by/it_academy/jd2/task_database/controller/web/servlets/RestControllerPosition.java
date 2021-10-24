package by.it_academy.jd2.task_database.controller.web.servlets;

import by.it_academy.jd2.task_database.model.Position;
import by.it_academy.jd2.task_database.view.api.IPositionServiceHibernate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping ("api/position")
public class RestControllerPosition {
    private final IPositionServiceHibernate positionServiceHibernate;

    public RestControllerPosition(IPositionServiceHibernate positionServiceHibernate) {
        this.positionServiceHibernate = positionServiceHibernate;
    }

    @PostMapping
    public void addNewPosition(@RequestBody Position position, HttpServletResponse resp, Model model) throws IOException {
        long id = positionServiceHibernate.addPosition(position);
        model.addAttribute("id", id);
        model.addAttribute("info", "Должность успешно добавлена с id=");

        resp.sendRedirect("addPositionMapper");
    }
}
