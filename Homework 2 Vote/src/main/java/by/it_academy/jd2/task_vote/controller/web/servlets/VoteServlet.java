package by.it_academy.jd2.task_vote.controller.web.servlets;

import by.it_academy.jd2.task_vote.view.Printer;
import by.it_academy.jd2.task_vote.view.VoteService;
import by.it_academy.jd2.task_vote.view.api.IPrinter;
import by.it_academy.jd2.task_vote.view.api.IVoteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "VoteServlet", urlPatterns = "/")
public class VoteServlet extends HttpServlet {

    private final IVoteService service;
    private final IPrinter printer;

    public VoteServlet() {
        this.service = VoteService.getInstance();
        this.printer = Printer.getInstance();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/vote.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        String artist = req.getParameter("artist");
        String[] genres = req.getParameterValues("genre");
        String about = req.getParameter("about");

        if (genres.length<3){
            throw new IllegalArgumentException("Количество жанров меньше трёх");
        }

        this.service.addVoteArtist(artist);
        this.service.addVoteGenre(genres);
        this.service.addVoteAbout(about);

        printer.printerVoteArtist(writer);
        printer.printerVoteGenre(writer);
        printer.printerText(writer);

    }
}
