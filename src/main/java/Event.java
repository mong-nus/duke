import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String inputDescription, LocalDateTime inputAt) {
        super('E', inputDescription);
        this.at = inputAt;
    }

    /**
     * return string to be display to user
     *
     */
    @Override
    public String toString() {
        return "[" + super.getTypeOfTask() + "][" + super.getStatusIcon() + "] "
                + super.getDescription() + " (at: " + at + ")";
    }

    /**
     * return the string to be inserted into the file
     *
     */
    @Override
    public String insertFile() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        if (super.getIsDone() == true) {
            //length of description to ensure if regex character is entered, code can still run
            return super.getTypeOfTask() + " | 1 | " + super.getDescription().length() + " | " +  super.getDescription() + " | " + formatter.format(at);
        } else {
            return super.getTypeOfTask() + " | 0 | " + super.getDescription().length() + " | " +  super.getDescription() + " | " + formatter.format(at);
        }
    }
}
