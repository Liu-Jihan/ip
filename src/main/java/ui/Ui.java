package ui;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns the welcome message instead of printing it.
     *
     * @return The welcome message string.
     */
    public String getWelcomeMessage() {
        return "Hello! I'm OscarL.\n" +
                "What can I do for you?\n\n" +
                "Available commands:\n" +
                "  bye - Exit the program\n" +
                "  list - Show all tasks\n" +
                "  mark <index> - Mark a task as done\n" +
                "  unmark <index> - Unmark a completed task\n" +
                "  delete <index> - Delete a task\n" +
                "  todo <description> - Add a to-do task\n" +
                "  deadline <description> /by <date> - Add a deadline task\n" +
                "  event <description> /from <start> /to <end> - Add an event task\n" +
                "  addplace <place name> - Add a place\n" +
                "  listplaces - List all saved places\n" +
                "  removeplace <index> - Remove a saved place by index\n";
    }

    /**
     * Returns a separator line instead of printing it.
     *
     * @return The separator line string.
     */
    public String getLine() {
        return "____________________________________________________________";
    }

    /**
     * Reads user input from the console.
     *
     * @return The user input command.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Returns a message instead of printing it.
     *
     * @param message The message to return.
     * @return The formatted message string.
     */
    public String showMessage(String message) {
        return message;
    }

    /**
     * Returns an error message instead of printing it.
     *
     * @param errorMessage The error message to return.
     * @return The formatted error string.
     */
    public String showError(String errorMessage) {
        return "OOPS!!! " + errorMessage;
    }
}


