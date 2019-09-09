package dukeproject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Initializes the variables of the Event class
     *
     * @param inputDescription the description of the event
     * @param inputBy date and time of the event
     */
    public Deadline(String inputDescription, LocalDateTime inputBy) {
        super('D', inputDescription);
        this.by = inputBy;
    }

    /**
     * Returns the content of the task to be displayed to user
     */
    @Override
    public String toString() {
        return "[" + super.getTypeOfTask() + "][" + super.getStatusIcon() + "] "
                + super.getDescription() + " (by: " + by + ")";
    }

    /**
     * Returns the string to be inserted into the file
     */
    @Override
    public String insertFile() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        if (super.getIsDone() == true) {
            //length of description to ensure if regex character is entered, code can still run
            return super.getTypeOfTask() + " | 1 | " + super.getDescription().length() + " | " +  super.getDescription() + " | " + formatter.format(by);
        } else {
            return super.getTypeOfTask() + " | 0 | " + super.getDescription().length() + " | " +  super.getDescription() + " | " + formatter.format(by);
        }
    }
}
