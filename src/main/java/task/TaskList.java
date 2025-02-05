package task;

import java.util.ArrayList;
import oscarl.OscarLException;

/**
 * Represents a list of tasks and provides methods to manage them.
 */
public class TaskList {

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks The initial list of tasks.
     */

    private final ArrayList<Task> tasks;

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
        if (index < 0 || index >= tasks.size()) {
            throw new OscarLException("Invalid task number.");
        }
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
     * Lists all tasks in the task list.
     */
    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
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
     * Finds and displays tasks that contain the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public void findTasks(String keyword) {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the matching tasks in your list:");

        keyword = keyword.toLowerCase().trim(); // Case-insensitive search with trimming
        int count = 0;

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().toLowerCase().contains(keyword)) {
                System.out.println((count + 1) + ". " + task);
                count++;
            }
        }

        if (count == 0) {
            System.out.println(" No matching tasks found.");
        }

        System.out.println("____________________________________________________________");
    }

}
