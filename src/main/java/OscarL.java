import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class OscarL {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public OscarL(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        tasks = new TaskList(new ArrayList<>(storage.loadTasks()));  // Explicit conversion

    }

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

    public static void main(String[] args) {
        new OscarL("data/tasks.txt").run();
    }
}
