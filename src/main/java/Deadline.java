public class Deadline extends Task {
    private String ddl;

    public Deadline(String description, String by) {
        super(description);
        this.ddl = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + ddl + ")";
    }
}
