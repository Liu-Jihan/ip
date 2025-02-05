package oscarl;

import oscarl.OscarLException;

import command.Command;
import storage.Storage;
import ui.Ui;
import task.TaskList;
import java.util.ArrayList;  // Missing import for ArrayList
/**
 * The main class for the OscarL task management application.
 * Handles initialization, user interaction, and command execution.
 */
public class OscarL {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    /**
     * Constructs an instance of OscarL with the given file path for task storage.
     *
     * @param filePath The file path where tasks are stored.
     */
    public OscarL(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(new ArrayList<>(storage.loadTasks()));  // Explicit conversion
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
