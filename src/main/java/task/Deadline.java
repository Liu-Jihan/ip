package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline, storing the due date and time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

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
     * @return A LocalDateTime object.
     * @throws IllegalArgumentException if the date format is invalid.
     */
    private static LocalDateTime parseDateTime(String by) {
        if (!by.matches("\\d{1,2}/\\d{1,2}/\\d{4} \\d{4}")) {
            throw new IllegalArgumentException("Invalid date format: \"" + by +
                    "\". Expected format: d/M/yyyy HHmm (e.g., 5/2/2025 1830)");
        }

        try {
            return LocalDateTime.parse(by, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date-time value: \"" + by +
                    "\". Please enter a valid date in the format d/M/yyyy HHmm.", e);
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
