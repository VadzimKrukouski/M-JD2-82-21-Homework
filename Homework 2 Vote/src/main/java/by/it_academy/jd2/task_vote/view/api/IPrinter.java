package by.it_academy.jd2.task_vote.view.api;

import java.io.PrintWriter;

public interface IPrinter {
    void printerVoteArtist(PrintWriter writer);
    void printerVoteGenre(PrintWriter writer);
    void printerText(PrintWriter writer);
}
