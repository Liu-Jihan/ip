package command;

import oscarl.OscarLException;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.ToDo;
import places.Places;
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

    public static Command parse(String input, TaskList tasks,Places places, Ui ui, Storage storage) throws OscarLException {
        assert input != null : "Input cannot be null";
        assert !input.trim().isEmpty() : "Input cannot be empty";

        String[] parts = input.split(" ", 2);
        String command = parts[0];

        switch (command) {
            case "bye":
                return createByeCommand();
            case "list":
                return createListCommand(tasks);
            case "mark":
                return createMarkCommand(parts, tasks, storage);
            case "unmark":
                return createUnmarkCommand(parts, tasks, storage);
            case "delete":
                return createDeleteCommand(parts, tasks, storage);
            case "todo":
                return createTodoCommand(parts, tasks, storage);
            case "deadline":
                return createDeadlineCommand(parts, tasks, storage);
            case "addplace":
                return createAddPlaceCommand(parts, places);
            case "event":
                return createEventCommand(parts, tasks, storage);
            case "listplaces":
                return createListPlacesCommand(places);
            case "removeplace":
                return createRemovePlaceCommand(parts, places);
            default:
                throw new OscarLException("Unknown command!");
        }
    }

    private static Command createRemovePlaceCommand(String[] parts, Places places) {
        return new Command(() -> {
            try {
                if (parts.length < 2) return "Please provide a place index.";
                int index = Integer.parseInt(parts[1]) - 1;
                String removed = places.removePlace(index);
                return "Removed place: " + removed;
            } catch (NumberFormatException e) {
                return "Invalid place index!";
            } catch (IndexOutOfBoundsException e) {
                return "Place index out of range!";
            }
        }, false);
    }


    private static Command createAddPlaceCommand(String[] parts, Places places) {
        return new Command(() -> {
            if (parts.length < 2) return "Please provide a place name.";
            places.addPlace(parts[1]);
            return "Added place: " + parts[1];
        }, false);
    }
    private static Command createByeCommand() {
        return new Command(() -> "Bye. Hope to see you again soon!", true);
    }

    private static Command createListCommand(TaskList tasks) {
        return new Command(() -> "Here are your tasks:\n" + tasks.listTasks(), false);
    }
    private static Command createListPlacesCommand(Places places) {
        return new Command(places::listPlaces, false);
    }


    private static Command createMarkCommand(String[] parts, TaskList tasks, Storage storage) {
        return new Command(() -> {
            if (parts.length < 2) return "Task number is required for 'mark' command.";
            try {
                int index = Integer.parseInt(parts[1]) - 1;
                assert index >= 0 && index < tasks.size() : "Invalid task index";
                Task task = tasks.markTask(index, true);
                storage.saveTasks(tasks.getTasks());
                return "Task marked as done: " + task;
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        }, false);
    }

    private static Command createUnmarkCommand(String[] parts, TaskList tasks, Storage storage) {
        return new Command(() -> {
            if (parts.length < 2) return "Task number is required for 'unmark' command.";
            try {
                int index = Integer.parseInt(parts[1]) - 1;
                assert index >= 0 && index < tasks.size() : "Invalid task index";
                Task task = tasks.markTask(index, false);
                storage.saveTasks(tasks.getTasks());
                return "Task marked as not done: " + task;
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        }, false);
    }

    private static Command createDeleteCommand(String[] parts, TaskList tasks, Storage storage) {
        return new Command(() -> {
            if (parts.length < 2) return "Task number is required for 'delete' command.";
            try {
                int index = Integer.parseInt(parts[1]) - 1;
                assert index >= 0 && index < tasks.size() : "Invalid task index";
                Task removed = tasks.removeTask(index);
                storage.saveTasks(tasks.getTasks());
                return "Removed: " + removed;
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        }, false);
    }

    private static Command createTodoCommand(String[] parts, TaskList tasks, Storage storage) {
        return new Command(() -> {
            if (parts.length < 2) return "Description is required for 'todo' command.";
            Task task = new ToDo(parts[1]);
            tasks.addTask(task);
            storage.saveTasks(tasks.getTasks());
            return "Added: " + task;
        }, false);
    }

    private static Command createDeadlineCommand(String[] parts, TaskList tasks, Storage storage) {
        return new Command(() -> {
            if (parts.length < 2 || !parts[1].contains("/by")) return "Use 'deadline description /by dd/mm/yyyy 00:00'.";
            String[] deadlineParts = parts[1].split(" /by ", 2);
            assert deadlineParts.length == 2 : "Invalid deadline format";
            Task task = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
            tasks.addTask(task);
            storage.saveTasks(tasks.getTasks());
            return "Added: " + task;
        }, false);
    }

    private static Command createEventCommand(String[] parts, TaskList tasks, Storage storage) {
        return new Command(() -> {
            if (parts.length < 2 || !parts[1].contains("/from") || !parts[1].contains("/to"))
                return "Use 'event description /from start /to end'.";
            String[] eventParts = parts[1].split(" /from | /to ", 3);
            assert eventParts.length == 3 : "Invalid event format";
            Task task = new Event(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim());
            tasks.addTask(task);
            storage.saveTasks(tasks.getTasks());
            return "Added: " + task;
        }, false);
    }


}
