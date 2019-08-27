public class Deadline extends Task {

    protected String by;

    public Deadline(String inputDescription, String inputBy) {
        super('D', inputDescription);
        this.by = inputBy;
    }

    @Override
    public String toString() {
        return "[" + super.getTypeOfTask() + "][" + super.getStatusIcon() + "] "
                + super.getDescription() + " (by: " + by + ")";
    }

    @Override
    public String insertFile() {

        if (super.getIsDone() == true) {
            return super.getTypeOfTask() + " | 1 | " + super.getDescription().length() + " | " +  super.getDescription() + " | " + by;
        } else {
            return super.getTypeOfTask() + " | 0 | " + super.getDescription().length() + " | " +  super.getDescription() + " | " + by;
        }
    }
}
