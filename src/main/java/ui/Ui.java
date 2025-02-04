package ui;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("-------------------------------------------------------\n" +
                "Hello! I'm OscarL.OscarL\nWhat can I do for you?\n" +
                "-------------------------------------------------------");
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(String errorMessage) {
        System.out.println("OOPS!!! " + errorMessage);
    }
}
