import java.util.Scanner;

public class OscarL {
    public static void main(String[] args) {
        System.out.println("-------------------------------------------------------\n" + "Hello! I'm OscarL\n" +
                "What can I do for you?\n" + "-------------------------------------------------------\n");

        Scanner scanner = new Scanner(System.in);
        String theInput;



        while (true) {
            theInput = scanner.nextLine();

            if (theInput.equals("bye")) { // Exit condition
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }

            // Echo the user's input
            System.out.println(theInput);
            System.out.println("____________________________________________________________");
        }

        scanner.close();

    }
}
