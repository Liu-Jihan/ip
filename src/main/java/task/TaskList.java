package task;

import java.util.ArrayList;
import oscarl.OscarLException;

/**
 * Represents a list of tasks and provides methods to manage them.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list at the specified index.
     *
     * @param index The index of the task to remove.
     * @return The removed task.
     * @throws OscarLException If the index is invalid.
     */
    public Task removeTask(int index) throws OscarLException {
        assert index >= 0 && index < tasks.size() : "Index out of bounds";
        return tasks.remove(index);
    }

    /**
     * Marks a task as done or not done at the specified index.
     *
     * @param index The index of the task to mark.
     * @param done True to mark as done, false to mark as not done.
     * @return The updated task.
     * @throws OscarLException If the index is invalid.
     */
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

    /**
     * Returns all tasks as a formatted string instead of printing.
     *
     * @return A formatted string listing all tasks.
     */
    public String listTasks() {
        if (tasks.isEmpty()) {
            return "No tasks available.";
        } else {
            StringBuilder result = new StringBuilder("\n");
            for (int i = 0; i < tasks.size(); i++) {
                result.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
            }
            return result.toString().trim(); // ✅ Return instead of printing
        }
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Searches for tasks containing the keyword and returns them as a formatted string.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A formatted string with matching tasks.
     */
    public String findTasks(String keyword) {
        StringBuilder result = new StringBuilder("Here are the matching tasks in your list:\n");

        keyword = keyword.toLowerCase().trim();
        int count = 0;

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().toLowerCase().contains(keyword)) {
                result.append((count + 1)).append(". ").append(task).append("\n");
                count++;
            }
        }

        if (count == 0) {
            return "No matching tasks found.";
        }

        return result.toString().trim();
    }
}
