package by.it_academy.jd2.task_messenger_load_save.storage;

import by.it_academy.jd2.task_messenger_load_save.model.Message;
import by.it_academy.jd2.task_messenger_load_save.storage.api.IChatsStorage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileChatsStorage implements IChatsStorage {
    private static final FileChatsStorage instance = new FileChatsStorage();
    private final String path = AboutStorage.getInstance().getPathFile();
    private final File file = new File(path);

    private FileChatsStorage() {
    }

    public static FileChatsStorage getInstance() {
        return instance;
    }

    @Override
    public List<Message> get(String login) {
        List<Message> messages = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                String[] partsString = line.split(",");
                if (login.equals(partsString[1])){
                    Message message = new Message();
                    message.setFrom(partsString[2]);
                    message.setDate(partsString[3]);
                    message.setText(partsString[4]);
                    messages.add(message);
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return messages;
    }

    @Override
    public void addMessage(String login, Message message) {
        try (FileWriter writer = new FileWriter(file)) {
            String newLine = System.getProperty("line.separator");
            writer.append("Message");
            writer.append(",");
            writer.append(login);
            writer.append(",");
            writer.append(message.getFrom());
            writer.append(",");
            writer.append(message.getDate());
            writer.append(",");
            writer.append(message.getText());
            writer.append(newLine);

        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }
}
