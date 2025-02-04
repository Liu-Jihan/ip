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
                        int index = Integer.parseInt(parts[1]) - 1;
                        Task task = tasks.markTask(index, true);
                        ui.showMessage("Task marked as done: " + task);
                        storage.saveTasks(tasks.getTasks());
                    } catch (Exception e) {
                        ui.showError("Invalid task number.");
                    }
                }, false);

            case "unmark":
                return new Command(() -> {
                    try {
                        int index = Integer.parseInt(parts[1]) - 1;
                        Task task = tasks.markTask(index, false);
                        ui.showMessage("Task marked as not done: " + task);
                        storage.saveTasks(tasks.getTasks());
                    } catch (Exception e) {
                        ui.showError("Invalid task number.");
                    }
                }, false);

            case "delete":
                return new Command(() -> {
                    try {
                        int index = Integer.parseInt(parts[1]) - 1;
                        Task removed = tasks.removeTask(index);
                        ui.showMessage("Removed: " + removed);
                        storage.saveTasks(tasks.getTasks());
                    } catch (Exception e) {
                        ui.showError("Invalid task number.");
                    }
                }, false);

            case "todo":
                return new Command(() -> {
                    try {
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
                        String[] deadlineParts = parts[1].split(" /by ", 2);
                        Task task = new Deadline(deadlineParts[0], deadlineParts[1]);
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
                        String[] eventParts = parts[1].split(" /from | /to ", 3);
                        Task task = new Event(eventParts[0], eventParts[1], eventParts[2]);
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
