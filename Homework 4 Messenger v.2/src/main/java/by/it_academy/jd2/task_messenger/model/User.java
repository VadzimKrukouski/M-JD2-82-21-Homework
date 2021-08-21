package by.it_academy.jd2.task_messenger.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String login;
    private String password;
    private String fio;
    private String birthday;
    private List<String> messages = new ArrayList<>();

    public void addMessage(String message){
        messages.add(message);
    }

    public List<String> getMessages() {
        return messages;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
