package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline, storing the due date and time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline task with a description and due date.
     *
     * @param description The task description.
     * @param by The due date in "d/M/yyyy HHmm" format.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = parseDateTime(by);
    }

    /**
     * Parses a date-time string in "d/M/yyyy HHmm" format.
     *
     * @param by The string representing the date and time.
     * @return A LocalDateTime object or null if parsing fails.
     */
    private LocalDateTime parseDateTime(String by) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

        try {
            return LocalDateTime.parse(by, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected: d/M/yyyy HHmm");
        }
    }

    /**
     * Converts the Deadline task to a file-friendly format.
     *
     * @return A string representation of the task formatted for storage.
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " +
                (by != null ? by.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")) : "Invalid DateTime");
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A formatted string showing the task details and due date.
     */
    @Override
    public String toString() {
        DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "[D]" + super.toString() + " (by: " +
                (by != null ? by.format(displayFormat) : "Invalid DateTime") + ")";
    }
}
