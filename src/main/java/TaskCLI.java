import java.util.Scanner;

public class TaskCLI {
    private final TaskService taskService;
    private final Scanner scanner;

    public TaskCLI(TaskService taskService) {
        this.taskService = taskService;
        this.scanner = new Scanner(System.in);
    }

    public void cliMenu() {
        while (true) {
            System.out.println("\n===== Task Tracker =====");
            System.out.println("1. Add Task");
            System.out.println("2. Update Task");
            System.out.println("3. Delete Task");
            System.out.println("4. Mark Task as In Progress");
            System.out.println("5. Mark Task as Done");
            System.out.println("6. List All Tasks");
            System.out.println("7. List Tasks by Status");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1 -> addTask();
                case 2 -> updateTask();
                case 3 -> deleteTask();
                case 4 -> changeStatus(TaskStatus.IN_PROGRESS);
                case 5 -> changeStatus(TaskStatus.DONE);
                case 6 -> taskService.listTasks();
                case 7 -> listTasksByStatus();
                case 8 -> exitCLI();
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void addTask() {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        taskService.addTask(description, TaskStatus.TODO);
        System.out.println("Task added successfully.");
    }

    private void updateTask() {
        System.out.print("Enter task position to update: ");
        int pos = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new description: ");
        String description = scanner.nextLine();
        taskService.updateTask(pos, description, TaskStatus.TODO);
        System.out.println("Task updated successfully.");
    }

    private void deleteTask() {
        System.out.print("Enter task position to delete: ");
        int pos = scanner.nextInt();
        scanner.nextLine();
        taskService.deleteTask(pos);
        System.out.println("Task deleted successfully.");
    }

    private void changeStatus(TaskStatus status) {
        System.out.print("Enter task position: ");
        int pos = scanner.nextInt();
        scanner.nextLine();
        taskService.changeStatusTask(pos, status);
        System.out.println("Task status updated to " + status);
    }

    private void listTasksByStatus() {
        System.out.println("Choose status to filter by: (todo, in-progress, done)");
        String status = scanner.nextLine().toUpperCase();
        switch (status) {
            case "TODO" -> taskService.listNotDoneTasks();
            case "IN-PROGRESS" -> taskService.listInProgressTasks();
            case "DONE" -> taskService.listDoneTasks();
            default -> System.out.println("Invalid status.");
        }
    }

    private void exitCLI() {
        System.out.println("Saving tasks...");
        taskService.saveTasks();
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
