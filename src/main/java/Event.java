public class Event extends Task {

    protected String at;

    public Event(String inputDescription, String inputAt) {
        super('E', inputDescription);
        this.at = inputAt;
    }

    public String toString() {
        return "[" + super.getTypeOfTask() + "][" + super.getStatusIcon() + "] "
                + super.getDescription() + " (at: " + at + ")";
    }
}
