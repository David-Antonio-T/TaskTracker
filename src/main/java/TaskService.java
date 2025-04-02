import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private final List<Task> tasks;
    private final JsonTaskRepository repo;
    //private final JsonTaskRepository repo = new JsonTaskRepository(); //mal, D en SOLID, no acoplar directamente

    public TaskService(JsonTaskRepository repo) {//inyección de dependencias
        this.repo = repo;
        List<Task> loadedTasks = this.loadTasks();
        this.tasks = (loadedTasks != null) ? loadedTasks : new ArrayList<>();
    }

    public void renumberTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).setPosition(i + 1);
        }
    }

    public void addTask(String description, TaskStatus status) {
        Task newTask = new Task(description, status);
        tasks.add(newTask);
        newTask.setPosition(tasks.size());
    }

    public void updateTask(int pos, String description, TaskStatus status) {
        Task updatedTask = tasks.get(pos - 1); //referencia
        updatedTask.setUpdatedAt(LocalDateTime.now());
        updatedTask.setDescription(description);
        updatedTask.setStatus(status);
    }

    public void changeStatusTask(int pos, TaskStatus status) {
        tasks.get(pos - 1).setStatus(status);
    }

    public void deleteTask(int pos) {
        tasks.remove(pos - 1);
        this.renumberTasks();
    }

    public void listTasks() {
        tasks.forEach(System.out::println);
    }

    public void listDoneTasks() {
        tasks.stream().filter(task -> task.getStatus() == TaskStatus.DONE).forEach(System.out::println);
    }

    public void listNotDoneTasks() {
        tasks.stream().filter(task -> task.getStatus() == TaskStatus.TODO).forEach(System.out::println);
    }

    public void listInProgressTasks() {
        tasks.stream().filter(task -> task.getStatus() == TaskStatus.IN_PROGRESS).forEach(System.out::println);
    }

    public void saveTasks() {
        repo.saveTasks(tasks);
    }

    private List<Task> loadTasks() {
        return repo.loadTasks();
    }

}
