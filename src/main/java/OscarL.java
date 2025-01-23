import java.util.Scanner;
import java.util.ArrayList;


public class OscarL {
    public static void main(String[] args) {
        System.out.println("-------------------------------------------------------\n" + "Hello! I'm OscarL\n" +
                "What can I do for you?\n" + "-------------------------------------------------------");

        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        String theInput;



        while (true) {
            String programInput = scanner.nextLine();
            String[] inputParts = programInput.split(" ");
            theInput = inputParts[0];

            if (theInput.equals("bye")) { // Exit condition
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (theInput.equals("mark")) { // mark whether it is completed
                int taskIndex = Integer.parseInt(inputParts[1]) - 1;
                if (taskIndex >= 0 && taskIndex < tasks.size()) {
                    tasks.get(taskIndex).markAsDone();
                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(taskIndex));
                    System.out.println("____________________________________________________________");
                }
            } else if (theInput.equals("unmark")) { // mark whether it is uncompleted
                int taskIndex = Integer.parseInt(inputParts[1]) - 1;
                if (taskIndex >= 0 && taskIndex < tasks.size()) {
                    tasks.get(taskIndex).markAsNotDone();
                    System.out.println("____________________________________________________________");
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks.get(taskIndex));
                    System.out.println("____________________________________________________________");
                }
            } else if (theInput.equals("list")) { // Display all tasks
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
                System.out.println("____________________________________________________________");
            } else if (theInput.equals("todo")) {
                String[] details = programInput.split("todo");
                Task newTask = new ToDo(details[1]);
                tasks.add(newTask);
                System.out.println("Got it. I've added this task:");
                System.out.println(newTask);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else if (theInput.equals("deadline")) {
                String[] details = programInput.split("deadline | /by ");
                Task newTask = new Deadline(details[1], details[2]);
                tasks.add(newTask);
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(newTask);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else if (theInput.equals("event")) {
                System.out.println("____________________________________________________________");
                String[] details = programInput.split("event | /from | /to ");
                Task newTask = new Event(details[1], details[2], details[3]);
                tasks.add(newTask);
                System.out.println("Got it. I've added this task:");
                System.out.println(newTask);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("I'm sorry, I don't understand that command.");
            }
        }

        scanner.close();

    }
}
