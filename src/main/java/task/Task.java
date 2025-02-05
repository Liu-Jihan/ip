package task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false; // Default is not done
    }

    // Mark as done
    public void markAsDone() {
        this.isDone = true;
    }

    // Mark as not done
    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }
    public String toFileFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }

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
    /**
     * Retrieves the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

}
