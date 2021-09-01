package by.it_academy.jd2.task_messenger_load_save.view;

import by.it_academy.jd2.task_messenger_load_save.model.Message;
import by.it_academy.jd2.task_messenger_load_save.model.User;
import by.it_academy.jd2.task_messenger_load_save.storage.AboutStorage;
import by.it_academy.jd2.task_messenger_load_save.storage.MemoryChatsStorage;
import by.it_academy.jd2.task_messenger_load_save.storage.MemoryUsersStorage;
import by.it_academy.jd2.task_messenger_load_save.storage.api.IAboutStorage;
import by.it_academy.jd2.task_messenger_load_save.storage.api.IChatsStorage;
import by.it_academy.jd2.task_messenger_load_save.storage.api.IUsersStorage;
import by.it_academy.jd2.task_messenger_load_save.view.api.ISaveLoadFileService;

import java.io.*;
import java.util.Collection;
import java.util.List;

public class SaveLoadFileService implements ISaveLoadFileService {
    private static final SaveLoadFileService instance = new SaveLoadFileService();

    private final IUsersStorage FileUsersStorage;
    private final IChatsStorage FileChatsStorage;
    private final File file;

    private SaveLoadFileService() {
        this.FileUsersStorage = MemoryUsersStorage.getInstance();
        this.FileChatsStorage = MemoryChatsStorage.getInstance();
        IAboutStorage aboutStorage = AboutStorage.getInstance();
        String path = aboutStorage.getPathFile();
        this.file = new File(path);

    }

    public static SaveLoadFileService getInstance() {
        return instance;
    }

    @Override
    public void saveToFile() {
        try (FileWriter writer = new FileWriter(file)) {
            String newLine = System.getProperty("line.separator");
            Collection<User> allUsers = FileUsersStorage.getAll();
            for (User user : allUsers) {
                writer.append("U");
                writer.append(",");
                writer.append(user.getLogin());
                writer.append(",");
                writer.append(user.getPassword());
                writer.append(",");
                writer.append(user.getFio());
                writer.append(",");
                writer.append(user.getBirthday());
                writer.append(",");
                writer.append(user.getRegistration());
                writer.append(newLine);
                List<Message> messages = FileChatsStorage.get(user.getLogin());
                for (Message message : messages) {
                    writer.append("M");
                    writer.append(",");
                    writer.append(user.getLogin());
                    writer.append(",");
                    writer.append(message.getFrom());
                    writer.append(",");
                    writer.append(message.getDate());
                    writer.append(",");
                    writer.append(message.getText());
                    writer.append(newLine);
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                String[] partsString = line.split(",");
                if (partsString[0].equals("U")) {
                    User user = new User();
                    user.setLogin(partsString[1]);
                    user.setPassword(partsString[2]);
                    user.setFio(partsString[3]);
                    user.setBirthday(partsString[4]);
                    user.setRegistration(partsString[5]);
                    this.FileUsersStorage.addUser(user);

                }
                if (partsString[0].equals("M")) {
                    Message message = new Message();
                    message.setFrom(partsString[2]);
                    message.setDate(partsString[3]);
                    message.setText(partsString[4]);
                    this.FileChatsStorage.addMessage(partsString[1], message);
                }
                line=reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}