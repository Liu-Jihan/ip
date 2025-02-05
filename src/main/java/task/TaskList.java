package task;

import java.util.ArrayList;
import oscarl.OscarLException;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task removeTask(int index) throws OscarLException {
        if (index < 0 || index >= tasks.size()) {
            throw new OscarLException("Invalid task number.");
        }
        return tasks.remove(index);
    }

    public Task markTask(int index, boolean done) throws OscarLException {
        if (index < 0 || index >= tasks.size()) {
            throw new OscarLException("Invalid task number.");
        }
        Task task = tasks.get(index);
        if (done) {
            task.markAsDone();
        } else {
            task.markAsNotDone();
        }
        return task;
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
