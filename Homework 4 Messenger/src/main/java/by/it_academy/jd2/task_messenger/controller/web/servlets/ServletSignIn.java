package by.it_academy.jd2.task_messenger.controller.web.servlets;

import by.it_academy.jd2.task_messenger.view.SignInHandle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletSignIn", urlPatterns = "/signIn")
public class ServletSignIn extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/signIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //проводим верификацию юзера
        SignInHandle signInHandle = SignInHandle.getInstance();
        signInHandle.userVerification(req, resp);
    }
}
