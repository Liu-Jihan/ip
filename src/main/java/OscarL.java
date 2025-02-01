import java.util.Scanner;
import java.util.ArrayList;


public class OscarL {
    public static void main(String[] args) {
        System.out.println("-------------------------------------------------------\n" + "Hello! I'm OscarL\n" +
                "What can I do for you?\n" + "-------------------------------------------------------");

        Storage storage = new Storage("./data/duke.txt");
        ArrayList<Task> tasks = new ArrayList<>(storage.loadTasks());
        Scanner scanner = new Scanner(System.in);

        String theInput;



        while (true) {
            try {
                String programInput = scanner.nextLine().trim();
                String[] inputParts = programInput.split(" ", 2); // Split into command and details
                theInput = inputParts[0]; // Command

                switch (theInput) {
                case "bye": // Exit condition
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    return;

                case "mark": // Mark task as done
                    if (inputParts.length < 2) {
                        throw new OscarLException("Please specify the task number to mark as done.");
                    }
                    int markIndex = Integer.parseInt(inputParts[1]) - 1;
                    if (markIndex >= 0 && markIndex < tasks.size()) {
                        tasks.get(markIndex).markAsDone();
                        storage.saveTasks(tasks);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(tasks.get(markIndex));
                    } else {
                        throw new OscarLException("Task number is invalid.");
                    }
                    break;

                case "unmark": // Mark task as not done
                    if (inputParts.length < 2) {
                        throw new OscarLException("Please specify the task number to unmark.");
                    }
                    int unmarkIndex = Integer.parseInt(inputParts[1]) - 1;
                    if (unmarkIndex >= 0 && unmarkIndex < tasks.size()) {
                        tasks.get(unmarkIndex).markAsNotDone();
                        storage.saveTasks(tasks);
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(tasks.get(unmarkIndex));
                    } else {
                        throw new OscarLException("Task number is invalid.");
                    }
                    break;

                case "list": // Display all tasks
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                    break;

                case "todo": // Add a todo task
                    if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                        throw new OscarLException("The description of a todo cannot be empty.");
                    }
                    Task newTodo = new ToDo(inputParts[1].trim());
                    tasks.add(newTodo);
                    storage.saveTasks(tasks);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTodo);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    break;

                case "deadline": // Add a deadline task
                    if (inputParts.length < 2 || !inputParts[1].contains("/by")) {
                        throw new OscarLException("The deadline format is invalid. Use 'description /by dueDate'.");
                    }
                    String[] deadlineDetails = inputParts[1].split(" /by ", 2);
                    Task newDeadline = new Deadline(deadlineDetails[0].trim(), deadlineDetails[1].trim());
                    tasks.add(newDeadline);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newDeadline);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    break;

                case "event": // Add an event task
                    if (inputParts.length < 2 || !inputParts[1].contains("/from") || !inputParts[1].contains("/to")) {
                        throw new OscarLException("The event format is invalid. Use 'description /from start /to end'.");
                    }
                    String[] eventDetails = inputParts[1].split(" /from | /to ");
                    if (eventDetails.length < 3) {
                        throw new OscarLException("Event details are incomplete.");
                    }
                    Task newEvent = new Event(eventDetails[0].trim(), eventDetails[1].trim(), eventDetails[2].trim());
                    tasks.add(newEvent);
                    storage.saveTasks(tasks);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newEvent);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    break;

                case "delete": // Remove a task
                    if (inputParts.length < 2) {
                        throw new OscarLException("Please specify the task number to delete.");
                    }
                    int deleteIndex = Integer.parseInt(inputParts[1]) - 1;
                    if (deleteIndex >= 0 && deleteIndex < tasks.size()) {
                        Task removedTask = tasks.remove(deleteIndex); // Remove the task
                        storage.saveTasks(tasks);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(removedTask);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    } else {
                        throw new OscarLException("Task number is invalid.");
                    }
                    break;

                default: // Unrecognized command
                    throw new OscarLException("I'm sorry, I don't understand that command.");
                }
            } catch (OscarLException e) {
                System.out.println("OOPS!!! " + e.getMessage());
            } catch (Exception e) {
                System.out.println("OOPS!!! An unexpected error occurred: " + e.getMessage());
            }
            System.out.println("____________________________________________________________");
        }
    }
}
