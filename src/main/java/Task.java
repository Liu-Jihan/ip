public class Task {
    private String description;
    private boolean isDone;

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
}
