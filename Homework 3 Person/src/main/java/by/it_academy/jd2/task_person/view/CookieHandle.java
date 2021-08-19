package by.it_academy.jd2.task_person.view;

import by.it_academy.jd2.task_person.model.Person;
import by.it_academy.jd2.task_person.view.api.HandleRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class CookieHandle implements HandleRequest {
    private static final String FIRST_NAME_COOKIE="firstName";
    private static final String LAST_NAME_COOKIE="lastName";
    private static final String AGE_COOKIE="age";

    private static final CookieHandle instance = new CookieHandle();

    private CookieHandle() {
    }

    @Override
    public void save(HttpServletRequest req, HttpServletResponse resp, Person person) {
        saveCookies(resp,FIRST_NAME_COOKIE, person.getFirstName());
        saveCookies(resp,LAST_NAME_COOKIE, person.getLastName());
        saveCookies(resp,AGE_COOKIE, person.getAge());
    }

    @Override
    public Person get(HttpServletRequest req) {
        Person person=new Person();
        String firstNameValue = getValueCookie(req, FIRST_NAME_COOKIE);
        String lastNameValue = getValueCookie(req, LAST_NAME_COOKIE);
        String ageValue = getValueCookie(req, AGE_COOKIE);
        person.setFirstName(firstNameValue);
        person.setLastName(lastNameValue);
        person.setAge(ageValue);
        return person;
    }



    public String getValueCookie(HttpServletRequest req, String paramName) {
        String value = req.getParameter(paramName);

        if (value != null) {
            return value;
        }
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            value = Arrays.stream(cookies)
                    .filter(c -> paramName.equalsIgnoreCase(c.getName()))
                    .map(Cookie::getValue)
                    .findFirst()
                    .orElse(null);
        }

        if (value != null) {
            return value;
        }

        throw new IllegalArgumentException("не введены параметры");
    }

    public void saveCookies(HttpServletResponse resp, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(1000);
        resp.addCookie(cookie);
    }

    public static CookieHandle getInstance() {
        return instance;
    }
}
