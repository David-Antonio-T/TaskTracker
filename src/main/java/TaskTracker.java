public class TaskTracker {
    public static void main(String[] args) {
        JsonTaskRepository repo = new JsonTaskRepository();
        TaskService taskService = new TaskService(repo);
        TaskCLI taskCLI = new TaskCLI(taskService);

        taskCLI.cliMenu();
    }
}
