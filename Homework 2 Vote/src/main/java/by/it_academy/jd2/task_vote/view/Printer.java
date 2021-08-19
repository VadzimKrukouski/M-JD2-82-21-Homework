package by.it_academy.jd2.task_vote.view;

import by.it_academy.jd2.task_vote.view.api.IPrinter;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Printer implements IPrinter {
    private final VoteService service;

    public Printer (){
        this.service = VoteService.getInstance();
    }

    public void printerVoteArtist(PrintWriter writer){
        /*
        Получаем карту артист-количество голосов
        Получаем отсортированный лист артистов по количеству голосов, от большего к меньшему
         */
        Map<String, Integer> artistResult = this.service.getArtistResult();
        List<Map.Entry<String, Integer>> sortedArtist = artistResult.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).collect(Collectors.toList());

        writer.write("<h2> Результаты голосования артистов: </h2>");
        for (Map.Entry<String, Integer> stringIntegerEntry : sortedArtist) {
            switch (stringIntegerEntry.getKey()) {
                case "Linkin Park":
                    writer.write("Linkin Park ");
                    break;
                case "50 Cent":
                    writer.write("50 Cent ");
                    break;
                case "Eminem":
                    writer.write("Eminem ");
                    break;
                case "Иванушки":
                    writer.write("Иванушки ");
                    break;
            }
            writer.write(String.valueOf(stringIntegerEntry.getValue()));
            writer.write("<br/>");
        }
    }

    public void printerVoteGenre(PrintWriter writer){
        /*
        Получаем карту жанра-количество голосов
        Получаем отсортированный лист жанров по количеству голосов, от большего к меньшему
         */
        Map<String, Integer> genreResult = this.service.getGenreResult();
        List<Map.Entry<String, Integer>> sortedGenre = genreResult.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).collect(Collectors.toList());

        writer.write("<h2> Результаты голосования жанров: </h2>");
        for (Map.Entry<String, Integer> entryGenre : sortedGenre) {
            switch (entryGenre.getKey()) {
                case "Рэп":
                    writer.write("Рэп ");
                    break;
                case "Поп":
                    writer.write("Поп ");
                    break;
                case "Рок":
                    writer.write("Рок ");
                    break;
                case "Электро":
                    writer.write("Электро ");
                    break;
                case "Классика":
                    writer.write("Классика ");
                    break;
                case "Джаз":
                    writer.write("Джаз ");
                    break;
                case "Металл":
                    writer.write("Металл ");
                    break;
            }
            writer.write(String.valueOf(entryGenre.getValue()));
            writer.write("<br/>");
        }
    }

    public void printerText(PrintWriter writer){
        //получаем лист текста о себе
        List<String> aboutResult = this.service.getAboutResult();
        writer.write("<h2> Текст о себе: </h2>");
        for (String text : aboutResult) {
            writer.write(text);
            writer.write("<br/>");
        }
    }
}
