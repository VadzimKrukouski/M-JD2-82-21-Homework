package by.it_academy.jd2.task_messenger_load_save.storage;

import by.it_academy.jd2.task_messenger_load_save.model.User;
import by.it_academy.jd2.task_messenger_load_save.storage.api.IUsersStorage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUsersStorage implements IUsersStorage {
    private static final FileUsersStorage instance = new FileUsersStorage();
    private final String path = AboutStorage.getInstance().getPathFile();
    private final File file = new File(path);

    private FileUsersStorage() {
    }

    public static FileUsersStorage getInstance() {
        return instance;
    }

    @Override
    public boolean addUser(User user) {
        String login = user.getLogin();
        try (FileWriter writer = new FileWriter(file)) {
            String newLine = System.getProperty("line.separator");
            if (getUser(login) == null) {
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
                return true;
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public User getUser(String login) {
        User user = new User();
        if (Files.exists(Path.of(path))) {
//            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//                String line = reader.readLine();
//                Stream<String> lines = line.lines();
//                List<String> strings = lines.collect(Collectors.toList());
            try {
                Stream<String> lines = Files.lines(Path.of(path));
                List<String> strings = lines.collect(Collectors.toList());
                for (String string : strings) {

//            while (line != null) {
                    String[] partsString = string.split(",");
                    if (login.equals(partsString[0])) {
                        user.setLogin(partsString[0]);
                        user.setPassword(partsString[1]);
                        user.setFio(partsString[2]);
                        user.setBirthday(partsString[3]);
                        user.setRegistration(partsString[4]);
                        return user;
                    }
                }

            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Collection<User> getAll() {
        Collection<User> users = new ArrayList<>();
//        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//            String line = reader.readLine();
//            Stream<String> lines = line.lines();
//            List<String> strings = lines.collect(Collectors.toList());
        if (Files.exists(Path.of(path))) {

            try {
                Stream<String> lines = Files.lines(Path.of(path));
                List<String> strings = lines.collect(Collectors.toList());
                for (String string : strings) {
//                while (line != null) {
                    String[] partsString = string.split(",");
                    User user = new User();
                    user.setLogin(partsString[0]);
                    user.setFio(partsString[2]);
                    user.setBirthday(partsString[3]);
                    user.setRegistration(partsString[4]);
                    users.add(user);
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        return users;
    }
}
