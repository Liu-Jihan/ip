package task;

/**
 * Represents a generic task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

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
        return (isDone ? "[X] " : "[ ] ") + description;
    }

    /**
     * Converts the task to a file-friendly format.
     *
     * @return A string representation formatted for file storage.
     */
    public String toFileFormat() {
        return (isDone ? "1" : "0") + " | " + description;
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

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
            case "T":
                ToDo todo = new ToDo(description);
                if (isDone) todo.markAsDone();
                return todo;

            case "D":
                if (parts.length < 4) {
                    throw new IllegalArgumentException("Invalid deadline format in file.");
                }
                Deadline deadline = new Deadline(description, parts[3]);
                if (isDone) deadline.markAsDone();
                return deadline;

            case "E":
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

}
