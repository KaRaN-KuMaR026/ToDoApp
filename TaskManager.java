import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private List<Task> taskList;
    private final String FILE_NAME = "tasks.txt";
    Scanner in = new Scanner(System.in);

    public TaskManager() {
        taskList = new ArrayList<>();
        loadTasksFromFile();
    }

    private void loadTasksFromFile() {
        File file = new File(FILE_NAME);
        if(!file.exists()) {
            return;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while((line  = reader.readLine()) != null) {
                Task task = Task.fromFileString(line);
                taskList.add(task);
            }
            reader.close();
        }
        catch (IOException e) {
            System.out.println("Could not load tasks from file: " + e.getMessage());
        }
    }

    public void addTask() {
        try {
            System.out.print("Enter Task name: ");
            String name = in.nextLine();

            System.out.print("Enter Task description: ");
            String desc = in.nextLine();

            System.out.print("Enter Due Date (YYYY-MM-DD): ");
            String dueDate = in.nextLine();

            if(!dueDate.matches("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$")) {
                System.out.println("Invalid Date format. Use YYYY-MM-DD.");
                return;
            }

            Task task = new Task(name,desc,dueDate);
            taskList.add(task);
            System.out.println("Task added with ID: " + task.getTaskId());
        }
        catch(Exception e) {
            System.out.println("Error adding task: " + e.getMessage());
        }
    }


    public void ViewAllTasks() {
        if(taskList.isEmpty()) {
            System.out.println("No Tasks found.");
            return;
        }
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.printf("%-6s %-20s %-25s %-12s %-10s\n", "ID", "Name", "Description", "Due Date", "Status");
        System.out.println("--------------------------------------------------------------------------------");

        for(Task task : taskList) {
            System.out.printf("%-6d %-20s %-25s %-12s %-10s\n", task.getTaskId(), task.getName(), task.getDescription(), task.getDueDate(), task.getCompleted() ? "Completed" : "Pending");
        }
    }

    public void saveTasksToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
            for(Task task: taskList) {
                writer.write(task.toFileString());
                writer.newLine();
            }
            writer.close();
        }
        catch (IOException e) {
            System.out.println("Could not save tasks. " + e.getMessage());
        }
    }

    public void updateTask() {
        System.out.print("Enter the taskId of the task you want to Update: ");
        int id = in.nextInt();
        in.nextLine();
        Task task = findTaskByID(id);
        if(task == null) {
            System.out.println("could not find the task.");
            return;
        }

        System.out.print("Enter new task name: ");
        String newName = in.nextLine();
        if(!newName.isEmpty()) {
            task.setName(newName);
        }

        System.out.print("Enter new task description: ");
        String newDesc = in.nextLine();
        if(!newName.isEmpty()) {
            task.setDescription(newDesc);
        }

        System.out.print("Enter new due-date: ");
        String newDueDate = in.nextLine();
        if(!newDueDate.isEmpty()) {
            task.setDueDate(newDueDate);
        }
    }

    public void markTask(int id) {
        Task task = findTaskByID(id);
        if(task == null) {
            System.out.println("Could not find the task.");
            return;
        }
        task.markAsCompleted();
    }

    public void deleteTask() {
        System.out.print("Enter the Task Id: ");
        int id = in.nextInt();
        Task task = findTaskByID(id);
        if(task == null) {
            System.out.println("could not find task.");
            return;
        }
        taskList.remove(task);
        System.out.println("Task with id " + id + " deleted.");
    }

    public void filterTasks() {
        System.out.print("Enter filter type (completed/pending): ");
        String type = in.nextLine().toLowerCase();
        boolean status;
        if (type.equals("completed")) {
            status = true;
        } else if (type.equals("pending")) {
            status = false;
        } else {
            System.out.println("Invalid filter type.");
            return;
        }

        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.printf("%-6s %-20s %-45s %-12s %-10s\n", "ID", "Name", "Description", "Due Date","Status");
        System.out.println("--------------------------------------------------------------------------------");

        for (Task task : taskList) {
            if (task.getCompleted() == status) {
                System.out.printf("%-6d %-20s %-25s %-12s %-10s\n", task.getTaskId(), task.getName(), task.getDescription(), task.getDueDate(),task.getCompleted() ? "Completed" : "Pending");
            }
        }
    }

    private Task findTaskByID(int id) {
        for(Task task: taskList) {
            if(task.getTaskId() == id) {
                return task;
            }
        }
        return null;
    }

//    public void validateDate(String dueDate) {
//        String[] parts = dueDate.split("-");
//        System.out.println(Arrays.toString(parts));
//    }
}
