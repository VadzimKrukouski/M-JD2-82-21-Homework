package by.it_academy.jd2.task_person.view.api;

import by.it_academy.jd2.task_person.view.CookieHandle;
import by.it_academy.jd2.task_person.view.SessionHandle;

public enum StorageType {
    COOKIE(CookieHandle.getInstance()),
    SESSION(SessionHandle.getInstance())
    ;

    private final HandleRequest handler;

    StorageType(HandleRequest handler) {
        this.handler = handler;
    }

    //метод исключающий неправильность ввода заголовка с точки зрения регистра
    public static StorageType valueOfIgnoreCase(String name){
        for (StorageType value : values()) {
            if (value.name().equalsIgnoreCase(name)){
                return value;
            }
        }
        throw new IllegalArgumentException("Не передан тип сохранения");
    }

    public HandleRequest getHandler() {
        return handler;
    }
}
