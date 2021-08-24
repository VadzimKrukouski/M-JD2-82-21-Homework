package by.it_academy.jd2.task_messenger_load_save.view;

import by.it_academy.jd2.task_messenger_load_save.model.Message;
import by.it_academy.jd2.task_messenger_load_save.model.User;
import by.it_academy.jd2.task_messenger_load_save.storage.AboutStorage;
import by.it_academy.jd2.task_messenger_load_save.storage.ChatsStorage;
import by.it_academy.jd2.task_messenger_load_save.storage.UsersStorage;
import by.it_academy.jd2.task_messenger_load_save.storage.api.IAboutStorage;
import by.it_academy.jd2.task_messenger_load_save.storage.api.IChatsStorage;
import by.it_academy.jd2.task_messenger_load_save.storage.api.IUsersStorage;
import by.it_academy.jd2.task_messenger_load_save.view.api.ISaveLoadFileService;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class SaveLoadFileService implements ISaveLoadFileService {
    private static final SaveLoadFileService instance = new SaveLoadFileService();

    private final IUsersStorage usersStorage;
    private final IChatsStorage chatsStorage;
    private final File file;

    private SaveLoadFileService() {
        this.usersStorage = UsersStorage.getInstance();
        this.chatsStorage = ChatsStorage.getInstance();
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
            Collection<User> allUsers = usersStorage.getAll();
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
                writer.append(user.getRegistration().toString());
                writer.append(newLine);
                List<Message> messages = chatsStorage.get(user.getLogin());
                for (Message message : messages) {
                    writer.append("M");
                    writer.append(",");
                    writer.append(user.getLogin());
                    writer.append(",");
                    writer.append(message.getFrom());
                    writer.append(",");
                    writer.append(message.getDate().toString());
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
                    DateFormat format = new SimpleDateFormat("d MMMM yyyy");
                    Date date = format.parse(partsString[5]);
                    user.setRegistration(date);
                    this.usersStorage.addUser(user);

                }
                if (partsString[0].equals("M")) {
                    Message message = new Message();
                    message.setFrom(partsString[2]);
                    DateFormat format = new SimpleDateFormat("d MMMM yyyy");
                    Date date = format.parse(partsString[3]);
                    message.setDate(date);
                    message.setText(partsString[4]);
                    this.chatsStorage.addMessage(partsString[1], message);
                }
                line=reader.readLine();
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }
}
