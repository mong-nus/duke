import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String inputDescription, LocalDateTime inputBy) {
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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        if (super.getIsDone() == true) {
            return super.getTypeOfTask() + " | 1 | " + super.getDescription().length() + " | " +  super.getDescription() + " | " + formatter.format(by);
        } else {
            return super.getTypeOfTask() + " | 0 | " + super.getDescription().length() + " | " +  super.getDescription() + " | " + formatter.format(by);
        }
    }
}
