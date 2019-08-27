public class Event extends Task {

    protected String at;

    public Event(String inputDescription, String inputAt) {
        super('E', inputDescription);
        this.at = inputAt;
    }

    @Override
    public String toString() {
        return "[" + super.getTypeOfTask() + "][" + super.getStatusIcon() + "] "
                + super.getDescription() + " (at: " + at + ")";
    }

    @Override
    public String insertFile() {

        if (super.getIsDone() == true) {
            return super.getTypeOfTask() + " | 1 | " + super.getDescription().length() + " | " +  super.getDescription() + " | " + at;
        } else {
            return super.getTypeOfTask() + " | 0 | " + super.getDescription().length() + " | " +  super.getDescription() + " | " + at;
        }
    }
}
