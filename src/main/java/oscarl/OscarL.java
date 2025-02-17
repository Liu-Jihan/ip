package oscarl;

import command.Command;
import storage.Storage;
import task.TaskList;
import places.Places;
import ui.Ui;

import java.util.ArrayList;

/**
 * The main class for the OscarL task management application.
 * Handles initialization, user interaction, and command execution.
 */
public class OscarL {
    private final Storage storage;
    private final TaskList tasks;
    private final Places places;
    private final Ui ui;

    /**
     * Constructs an instance of OscarL with the given file path for task storage.
     *
     * @param filePath The file path where tasks are stored.
     */
    public OscarL(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(new ArrayList<>(storage.loadTasks()));
        this.places = new Places();
    }

    /**
     * Getter for UI.
     */
    public Ui getUi() {
        return ui;
    }

    /**
     * Getter for TaskList.
     */
    public TaskList getTasks() {
        return tasks;
    }

    /**
     * Getter for Storage.
     */
    public Storage getStorage() {
        return storage;
    }

    /**
     * Processes user input and returns a response string.
     *
     * @param input The user command.
     * @return The response string to be displayed in the GUI.
     */
    public String getResponse(String input) {
        try {
            Command command = Command.parse(input, tasks, places, ui, storage); // ✅ Correct order
            return command.execute(); // ✅ Returns response instead of printing
        } catch (OscarLException e) {
            return "Error: " + e.getMessage();
        }
    }

    public Places getPlaces() {
        return places;
    }

}

