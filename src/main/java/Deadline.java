import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime by; // Store deadline with time

    public Deadline(String description, String by) {
        super(description);
        this.by = parseDateTime(by); // Convert input into LocalDateTime
    }

    /**
     * Parses a date-time string in "d/M/yyyy HHmm" format.
     */
    private LocalDateTime parseDateTime(String by) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

        try {
            return LocalDateTime.parse(by, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date-time format! Use: d/M/yyyy HHmm (e.g., 2/12/2019 1800)");
            return null; // Return null if parsing fails
        }
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " +
                (by != null ? by.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")) : "Invalid DateTime");
    }

    @Override
    public String toString() {
        DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "[D]" + super.toString() + " (by: " +
                (by != null ? by.format(displayFormat) : "Invalid DateTime") + ")";
    }
}
