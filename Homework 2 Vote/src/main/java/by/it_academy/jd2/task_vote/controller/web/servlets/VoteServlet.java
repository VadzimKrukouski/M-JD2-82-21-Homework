package by.it_academy.jd2.task_vote.controller.web.servlets;

import by.it_academy.jd2.task_vote.view.Printer;
import by.it_academy.jd2.task_vote.view.VoteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "VoteServlet", urlPatterns = "/vote")
public class VoteServlet extends HttpServlet {

    private final VoteService service;

    public VoteServlet() {
        this.service = VoteService.getInstance();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.getRequestDispatcher("views/vote.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();


        String artist = req.getParameter("artist");
        String[] genres = req.getParameterValues("genre");
        String about = req.getParameter("about");

        this.service.addVoteArtist(artist);
        this.service.addVoteGenre(genres);
        this.service.addVoteAbout(about);

        Printer printer = new Printer();

        printer.printerVoteArtist(writer);
        printer.printerVoteGenre(writer);
        printer.printerText(writer);

    }
}
