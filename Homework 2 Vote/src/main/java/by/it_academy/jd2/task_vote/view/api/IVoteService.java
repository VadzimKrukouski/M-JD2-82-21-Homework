package by.it_academy.jd2.task_vote.view.api;

public interface IVoteService {
    void addVoteArtist(String artist);
    void addVoteGenre(String [] genres);
    void addVoteAbout(String about);
}
