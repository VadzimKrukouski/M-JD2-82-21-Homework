package by.it_academy.jd2.task_person.view.api;

import by.it_academy.jd2.task_person.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HandleRequest {
    void save(HttpServletRequest req, HttpServletResponse resp, Person person);
    Person get(HttpServletRequest req);
}
