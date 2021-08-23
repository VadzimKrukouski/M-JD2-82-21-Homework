package by.it_academy.jd2.task_messenger_load_save.model;

import java.util.Date;

public class User {
    private String login;
    private String password;
    private String fio;
    private String birthday;
    private Date registration;

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

    public Date getRegistration() {
        return registration;
    }

    public void setRegistration(Date registration) {
        this.registration = registration;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", fio='" + fio + '\'' +
                ", birthday='" + birthday + '\'' +
                ", registration=" + registration +
                '}';
    }
}
