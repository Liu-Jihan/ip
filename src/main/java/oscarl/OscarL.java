package oscarl;

import command.Command;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.util.ArrayList;

/**
 * The main class for the OscarL task management application.
 * Handles initialization, user interaction, and command execution.
 */
public class OscarL {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructs an instance of OscarL with the given file path for task storage.
     *
     * @param filePath The file path where tasks are stored.
     */
    public OscarL(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(new ArrayList<>(storage.loadTasks())); // Explicit conversion
    }

    /**
     * Runs the main loop of the application, processing user commands until the user exits.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                ui.showLine();
                String fullCommand = ui.readCommand();
                ui.showLine();

                Command command = Command.parse(fullCommand, tasks, ui, storage);
                command.execute();
                isExit = command.isExit();
            } catch (OscarLException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * The main entry point of the application.
     * Initializes the program and starts execution.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new OscarL("data/tasks.txt").run();
    }
}
