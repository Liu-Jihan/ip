package command;

import task.TaskList;
import task.ToDo;
import task.Deadline;
import task.Event;
import task.Task;  // Import Task
import storage.Storage;
import ui.Ui;
import oscarl.OscarLException;

public class Command {
    private final Runnable action;
    private final boolean isExit;

    public Command(Runnable action, boolean isExit) {
        this.action = action;
        this.isExit = isExit;
    }

    public void execute() {
        action.run();
    }

    public boolean isExit() {
        return isExit;
    }

    public static Command parse(String input, TaskList tasks, Ui ui, Storage storage) throws OscarLException {
        String[] parts = input.split(" ", 2);
        String command = parts[0];

        switch (command) {
            case "bye":
                return new Command(() -> ui.showMessage("Bye. Hope to see you again soon!"), true);

            case "list":
                return new Command(() -> {
                    ui.showMessage("Here are your tasks:");
                    tasks.listTasks();
                }, false);

            case "mark":
                return new Command(() -> {
                    try {
                        if (parts.length < 2) {
                            throw new OscarLException("Task number is required for 'mark' command.");
                        }
                        int index = Integer.parseInt(parts[1]) - 1;
                        Task task = tasks.markTask(index, true);
                        ui.showMessage("Task marked as done: " + task);
                        storage.saveTasks(tasks.getTasks());
                    } catch (NumberFormatException e) {
                        ui.showError("Invalid task number format.");
                    } catch (Exception e) {
                        ui.showError(e.getMessage());
                    }
                }, false);

            case "unmark":
                return new Command(() -> {
                    try {
                        if (parts.length < 2) {
                            throw new OscarLException("Task number is required for 'unmark' command.");
                        }
                        int index = Integer.parseInt(parts[1]) - 1;
                        Task task = tasks.markTask(index, false);
                        ui.showMessage("Task marked as not done: " + task);
                        storage.saveTasks(tasks.getTasks());
                    } catch (NumberFormatException e) {
                        ui.showError("Invalid task number format.");
                    } catch (Exception e) {
                        ui.showError(e.getMessage());
                    }
                }, false);

            case "delete":
                return new Command(() -> {
                    try {
                        if (parts.length < 2) {
                            throw new OscarLException("Task number is required for 'delete' command.");
                        }
                        int index = Integer.parseInt(parts[1]) - 1;
                        Task removed = tasks.removeTask(index);
                        ui.showMessage("Removed: " + removed);
                        storage.saveTasks(tasks.getTasks());
                    } catch (NumberFormatException e) {
                        ui.showError("Invalid task number format.");
                    } catch (Exception e) {
                        ui.showError(e.getMessage());
                    }
                }, false);

            case "todo":
                return new Command(() -> {
                    try {
                        if (parts.length < 2) {
                            throw new OscarLException("Description is required for 'todo' command.");
                        }
                        Task task = new ToDo(parts[1]);
                        tasks.addTask(task);
                        ui.showMessage("Added: " + task);
                        storage.saveTasks(tasks.getTasks());
                    } catch (Exception e) {
                        ui.showError("Invalid ToDo format.");
                    }
                }, false);

            case "deadline":
                return new Command(() -> {
                    try {
                        if (parts.length < 2 || !parts[1].contains("/by")) {
                            throw new OscarLException("Use 'deadline description /by dueDate'.");
                        }
                        String[] deadlineParts = parts[1].split(" /by ", 2);
                        Task task = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
                        tasks.addTask(task);
                        ui.showMessage("Added: " + task);
                        storage.saveTasks(tasks.getTasks());
                    } catch (Exception e) {
                        ui.showError("Invalid Deadline format.");
                    }
                }, false);

            case "event":
                return new Command(() -> {
                    try {
                        if (parts.length < 2 || !parts[1].contains("/from") || !parts[1].contains("/to")) {
                            throw new OscarLException("Use 'event description /from start /to end'.");
                        }
                        String[] eventParts = parts[1].split(" /from | /to ", 3);
                        Task task = new Event(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim());
                        tasks.addTask(task);
                        ui.showMessage("Added: " + task);
                        storage.saveTasks(tasks.getTasks());
                    } catch (Exception e) {
                        ui.showError("Invalid Event format.");
                    }
                }, false);

            default:
                throw new OscarLException("Unknown command!");
        }
    }
}
