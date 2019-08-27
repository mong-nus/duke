import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String inputDescription, LocalDateTime inputAt) {
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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        if (super.getIsDone() == true) {
            return super.getTypeOfTask() + " | 1 | " + super.getDescription().length() + " | " +  super.getDescription() + " | " + formatter.format(at);
        } else {
            return super.getTypeOfTask() + " | 0 | " + super.getDescription().length() + " | " +  super.getDescription() + " | " + formatter.format(at);
        }
    }
}
