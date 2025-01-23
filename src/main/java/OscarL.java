import java.util.Scanner;
import java.util.ArrayList;

public class OscarL {
    public static void main(String[] args) {
        System.out.println("-------------------------------------------------------\n" + "Hello! I'm OscarL\n" +
                "What can I do for you?\n" + "-------------------------------------------------------\n");

        ArrayList<String> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        String theInput;



        while (true) {
            theInput = scanner.nextLine();

            if (theInput.equals("bye")) { // Exit condition
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }
            else if (theInput.equals("list")) { // Display all tasks
                System.out.println("____________________________________________________________");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
                System.out.println("____________________________________________________________");
            } else {
                tasks.add(theInput);
                System.out.println("added: " + theInput);
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();

    }
}
