public class Task {
    private static int idCounter = 1000;
    private String name;
    private String description;
    private String dueDate;
    private final int taskId;
    private boolean isCompleted;

    public Task(String name,String description,String dueDate) {
        this.taskId = idCounter++;
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.isCompleted = false;
    }

    public Task(int taskId, String name, String description, String dueDate, boolean isCompleted) {
        this.taskId = taskId;
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.isCompleted = isCompleted;

        if (taskId >= idCounter) {
            idCounter = taskId + 1;
        }
    }

    //getter methods
    public String getName () {
        return name;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public boolean getCompleted () {
        return isCompleted;
    }

    //setter methods


    public void setName(String name) {
        this.name = name;
    }

    public void markAsCompleted() {
        isCompleted = true;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String toString() {
        return String.format("%d\t %s\t %s\t %s", taskId, name,description, isCompleted ? "Completed" : "Pending", dueDate);
    }

    public String toFileString() {
        return taskId + "|" + name + "|" + description + "|" + dueDate + "|" + isCompleted;
    }

    public static Task fromFileString(String line) {
        String[] parts = line.split("\\|");
        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        String desc = parts[2];
        String date = parts[3];
        boolean status = Boolean.parseBoolean(parts[4]);
        return new Task(id, name, desc, date, status);
    }


}
