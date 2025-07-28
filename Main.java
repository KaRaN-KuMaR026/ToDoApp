import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean appRunning = true;
        TaskManager manager= new TaskManager();

        while(appRunning) {
            System.out.println("\n========= TO-DO APPLICATION =========");
            System.out.println("1. Add Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. Update Task");
            System.out.println("4. Mark Task as Completed");
            System.out.println("5. Delete Task");
            System.out.println("6. Filter Tasks (Completed / Pending)");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = in.nextInt();

            switch(choice) {
                case 1 :
                    manager.addTask();
                    break;

                case 2 :
                    System.out.println("The Task List: ");
                    manager.ViewAllTasks();
                    break;

                case 3 :
                    manager.updateTask();
                    break;

                case 4 :
                    System.out.print("Enter the Task id: ");
                    int id = in.nextInt();
                    manager.markTask(id);
                    break;

                case 5 :
                    manager.deleteTask();
                    break;

                case 6 :
                    System.out.println("6 entered");
                    break;

                case 0 :
                    manager.saveTasksToFile();
                    System.out.println("Exiting the app....saved tasks.");
                    appRunning = false;
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
