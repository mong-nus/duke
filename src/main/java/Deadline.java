public class Deadline extends Task {

    protected String by;

    public Deadline(String inputDescription, String inputBy) {
        super('D', inputDescription);
        this.by = inputBy;
    }

    public String toString() {
        return "[" + super.getTypeOfTask() + "][" + super.getStatusIcon() + "] "
                + super.getDescription() + " (by: " + by + ")";
    }
}
