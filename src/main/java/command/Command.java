package command;

import oscarl.OscarLException;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.ToDo;
import ui.Ui;

import java.util.function.Supplier;

/**
 * Represents a command that can be executed within the application.
 */
public class Command {
    private final Supplier<String> action;
    private final boolean isExit;

    /**
     * Constructs a Command with a specific action and exit status.
     *
     * @param action The function that returns a response when the command runs.
     * @param isExit Whether this command causes the program to exit.
     */
    public Command(Supplier<String> action, boolean isExit) {
        this.action = action;
        this.isExit = isExit;
    }

    /**
     * Executes the command and returns the response string.
     */
    public String execute() {
        return action.get(); // ✅ Returns a response instead of printing to terminal
    }

    /**
     * Checks if this command should terminate the program.
     *
     * @return true if this command exits the program, false otherwise.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Parses a user input string and returns the corresponding Command.
     */
    public static Command parse(String input, TaskList tasks, Ui ui, Storage storage) throws OscarLException {
        String[] parts = input.split(" ", 2);
        String command = parts[0];

        switch (command) {
            case "bye":
                return new Command(() -> "Bye. Hope to see you again soon!", true);

            case "list":
                return new Command(() -> "Here are your tasks:\n" + tasks.listTasks(), false);

            case "mark":
                return new Command(() -> {
                    if (parts.length < 2) return "Task number is required for 'mark' command.";
                    try {
                        int index = Integer.parseInt(parts[1]) - 1;
                        Task task = tasks.markTask(index, true);
                        storage.saveTasks(tasks.getTasks()); // ✅ Ensures tasks save properly
                        return "Task marked as done: " + task;
                    } catch (Exception e) {
                        return "Error: " + e.getMessage();
                    }
                }, false);

            case "unmark":
                return new Command(() -> {
                    if (parts.length < 2) return "Task number is required for 'unmark' command.";
                    try {
                        int index = Integer.parseInt(parts[1]) - 1;
                        Task task = tasks.markTask(index, false);
                        storage.saveTasks(tasks.getTasks());
                        return "Task marked as not done: " + task;
                    } catch (Exception e) {
                        return "Error: " + e.getMessage();
                    }
                }, false);

            case "delete":
                return new Command(() -> {
                    if (parts.length < 2) return "Task number is required for 'delete' command.";
                    try {
                        int index = Integer.parseInt(parts[1]) - 1;
                        Task removed = tasks.removeTask(index);
                        storage.saveTasks(tasks.getTasks());
                        return "Removed: " + removed;
                    } catch (Exception e) {
                        return "Error: " + e.getMessage();
                    }
                }, false);

            case "todo":
                return new Command(() -> {
                    if (parts.length < 2) return "Description is required for 'todo' command.";
                    Task task = new ToDo(parts[1]);
                    tasks.addTask(task);
                    storage.saveTasks(tasks.getTasks());
                    return "Added: " + task;
                }, false);

            case "deadline":
                return new Command(() -> {
                    if (parts.length < 2 || !parts[1].contains("/by")) return "Use 'deadline description /by dueDate'.";
                    String[] deadlineParts = parts[1].split(" /by ", 2);
                    Task task = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
                    tasks.addTask(task);
                    storage.saveTasks(tasks.getTasks());
                    return "Added: " + task;
                }, false);

            case "event":
                return new Command(() -> {
                    if (parts.length < 2 || !parts[1].contains("/from") || !parts[1].contains("/to"))
                        return "Use 'event description /from start /to end'.";
                    String[] eventParts = parts[1].split(" /from | /to ", 3);
                    Task task = new Event(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim());
                    tasks.addTask(task);
                    storage.saveTasks(tasks.getTasks());
                    return "Added: " + task;
                }, false);

            default:
                throw new OscarLException("Unknown command!");
        }
    }
}
