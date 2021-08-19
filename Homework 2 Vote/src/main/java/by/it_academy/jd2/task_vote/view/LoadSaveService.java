package by.it_academy.jd2.task_vote.view;

import by.it_academy.jd2.task_vote.model.VoteStorage;
import by.it_academy.jd2.task_vote.view.api.ILoadSaveService;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class LoadSaveService implements ILoadSaveService {

    private final VoteStorage storage;
    private static final LoadSaveService instance = new LoadSaveService();
    private static final String FILE_NAME = "vote.txt";
    private static final String FILE = PathCreator.getFileName(FILE_NAME);

    public LoadSaveService() {
        this.storage = VoteStorage.getInstance();
    }

    public static LoadSaveService getInstance() {
        return instance;
    }

    public void save() {
        try (PrintWriter writer = new PrintWriter(FILE)) {
            for (String text : storage.getAbout()) {
                writer.write(text);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
