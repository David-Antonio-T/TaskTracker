import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonTaskRepository {
    final private static String FILE_PATH = "tasks.json";
    final private ObjectMapper objectMapper;

    public JsonTaskRepository() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }


    //cargar tasks
    public List<Task> loadTasks() {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return new ArrayList<>();
        } //vacio si no hay archivo json

        try {
            CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, Task.class);
            return objectMapper.readValue(file, listType);

        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void saveTasks(List<Task> tasks) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}//cargamos y guardamos solo al entrar y salir del cli
