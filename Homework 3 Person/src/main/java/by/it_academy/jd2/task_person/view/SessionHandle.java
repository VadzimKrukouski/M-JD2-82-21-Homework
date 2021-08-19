package by.it_academy.jd2.task_person.view;

import by.it_academy.jd2.task_person.model.Person;
import by.it_academy.jd2.task_person.view.api.HandleRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionHandle implements HandleRequest {
    private static final String ATTRIBUTE_SESSION = "person";

    private static final SessionHandle instance = new SessionHandle();

    public static SessionHandle getInstance() {
        return instance;
    }

    @Override
    public void save(HttpServletRequest req, HttpServletResponse resp, Person person) {
        HttpSession session = req.getSession();
        session.setAttribute(ATTRIBUTE_SESSION, person);
    }

    @Override
    public Person get(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Person person = (Person) session.getAttribute(ATTRIBUTE_SESSION);

        if (person == null) {
            throw new IllegalArgumentException("параметры не введены");
        }

        return person;
    }
}
