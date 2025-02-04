package scratch;
import task.Deadline;

import task.Task;

import java.util.Scanner;

public class Scratch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String programInput = scanner.nextLine().trim();
        String[] inputParts = programInput.split(" ", 2); // Split into command and details
        String theInput;
        theInput = inputParts[0];
        System.out.println(theInput);

        String[] deadlineDetails = inputParts[1].split(" /by ", 2);
        System.out.println(deadlineDetails[0]);
        System.out.println(deadlineDetails[1]);
        Task newDeadline = new Deadline(deadlineDetails[0].trim(), deadlineDetails[1].trim());

        System.out.println(newDeadline);
    }
}
