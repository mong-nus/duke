package dukeproject;

public class ToDo extends Task {

    /**
     * Initializes the variables of the ToDo class
     *
     * @param inputDescription The description of the event
     */
    public ToDo(String inputDescription) {

        super('T', inputDescription);
    }

    /**
     * Returns the content of the task to be displayed to user
     */
    @Override
    public String toString() {
        return "[" + super.getTypeOfTask() + "][" + super.getStatusIcon() + "] " + super.getDescription();
    }

    /**
     * Returns the string to be inserted into the file
     */
    @Override
    public String insertFile() {

        if (super.getIsDone() == true) {
            //length of description to ensure if regex character is entered, code can still run
            return super.getTypeOfTask() + " | 1 | " + super.getDescription().length() + " | " + super.getDescription();
        } else {
            return super.getTypeOfTask() + " | 0 | " + super.getDescription().length() + " | " +  super.getDescription();
        }
    }

}
