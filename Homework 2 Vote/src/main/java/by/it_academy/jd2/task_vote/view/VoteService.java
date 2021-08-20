package by.it_academy.jd2.task_vote.view;

import by.it_academy.jd2.task_vote.model.VoteStorage;
import by.it_academy.jd2.task_vote.view.api.IVoteService;

import java.util.List;
import java.util.Map;

public class VoteService implements IVoteService {

    private final VoteStorage storage;
    private final static VoteService instance = new VoteService();

    private VoteService() {
        this.storage = VoteStorage.getInstance();
    }

    @Override
    public void addVoteArtist(String artist) {
    /*
        Добавляем голос к выбранному артисту
        Если такого артиста нет, количество голосов устанавливаем равное 0
         */
        Integer artistValue = this.storage.getArtist().getOrDefault(artist, 0);
        this.storage.getArtist().put(artist, ++artistValue);
    }

    @Override
    public void addVoteGenre(String[] genres) {
  /*
        Перебираем массив выбранных жанров
        Добавляем количество голосов к каждому жанру из массива
        Если такого жанра нет, количество голосов устанавливаем равное 0
         */
        if (genres != null) {
            for (String genre : genres) {
                Integer genreValue = this.storage.getGenre().getOrDefault(genre, 0);
                this.storage.getGenre().put(genre, ++genreValue);
            }
        }
    }

    @Override
    public void addVoteAbout(String about) {
        //Добавляем наш текст
        this.storage.getAbout().add(about);
    }

    public Map<String, Integer> getArtistResult() {
        return this.storage.getArtist();
    }

    public Map<String, Integer> getGenreResult() {
        return this.storage.getGenre();
    }

    public List<String> getAboutResult() {
        return this.storage.getAbout();
    }

    public static VoteService getInstance() {
        return instance;
    }
}
