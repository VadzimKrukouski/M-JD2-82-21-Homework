package by.it_academy.jd2.task_messenger_load_save.storage;

import by.it_academy.jd2.task_messenger_load_save.storage.api.IChatsStorage;

public class ChatsStorageFactory {
    private static StorageType type = null;

    private ChatsStorageFactory() {
    }

    public static synchronized void setType(StorageType type){
        if (type!=null){
            ChatsStorageFactory.type=type;
        } else {
            throw new IllegalStateException("Нельзя менять тип сохранения");
        }
    }

    public static IChatsStorage getInstance(){
        if (type==null){
            throw new IllegalStateException("Не выбран тип сохранения");
        }

        switch (type){
            case FILE:
                return FileChatsStorage.getInstance();
            case MEMORY:
                return MemoryChatsStorage.getInstance();
            default:
                throw new IllegalStateException("Такого типа сохранения не существует");
        }
    }
}
