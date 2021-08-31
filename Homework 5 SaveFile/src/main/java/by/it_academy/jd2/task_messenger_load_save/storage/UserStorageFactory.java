package by.it_academy.jd2.task_messenger_load_save.storage;

import by.it_academy.jd2.task_messenger_load_save.storage.api.IUsersStorage;

public class UserStorageFactory {
    private static StorageType type = null;

    private UserStorageFactory() {
    }

    public static synchronized void setType(StorageType type){
        if (type!=null){
            UserStorageFactory.type=type;
        } else {
            throw new IllegalStateException("Нельзя менять тип сохранения");
        }
    }

    public static IUsersStorage getInstance(){
        if (type==null){
            throw new IllegalStateException("Не выбран тип сохранения");
        }

        switch (type){
            case FILE:
                return FileUsersStorage.getInstance();
            case MEMORY:
                return MemoryUsersStorage.getInstance();
            default:
                throw new IllegalStateException("Такого типа сохранения не существует");
        }
    }


}
