package task;

/**
 * Represents a generic task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    private static final String TODO_TYPE = "T";
    private static final String DEADLINE_TYPE = "D";
    private static final String EVENT_TYPE = "E";

    /**
     * Constructs a new Task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false; // Default is not done
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A formatted string indicating the completion status and description.
     */
    @Override
    public String toString() {
        return "[" + getTypeSymbol() + "]" + (isDone ? "[X] " : "[ ] ") + description;
    }

    /**
     * Converts the task to a file-friendly format, ensuring type information is included.
     *
     * @return A string representation formatted for file storage.
     */
    public String toFileFormat() {
        return getTypeSymbol() + " | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Determines the type symbol for the task.
     *
     * @return A string representing the task type ("T", "D", or "E").
     */
    protected String getTypeSymbol() {
        return "T"; // Default for generic tasks (should be overridden)
    }

    /**
     * Parses a task from its file format representation.
     *
     * @param line The line from the file representing a task.
     * @return The parsed Task object.
     * @throws IllegalArgumentException If the format is invalid.
     */
    public static Task fromFileFormat(String line) {
        String[] parts = line.split(" \\| ");

        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid task format in file.");
        }

        String type = parts[0]; // Fix: Correctly extracting the type symbol
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
            case TODO_TYPE:
                ToDo todo = new ToDo(description);
                if (isDone) todo.markAsDone();
                return todo;

            case DEADLINE_TYPE:
                if (parts.length < 4) {
                    throw new IllegalArgumentException("Invalid deadline format in file.");
                }
                Deadline deadline = new Deadline(description, parts[3]);
                if (isDone) deadline.markAsDone();
                return deadline;

            case EVENT_TYPE:
                if (parts.length < 5) {
                    throw new IllegalArgumentException("Invalid event format in file.");
                }
                Event event = new Event(description, parts[3], parts[4]);
                if (isDone) event.markAsDone();
                return event;

            default:
                throw new IllegalArgumentException("Unknown task type in file.");
        }
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }
}
