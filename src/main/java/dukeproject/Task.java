package dukeproject;

public abstract class Task {

    protected String description;
    protected boolean isDone;
    protected char typeOfTask;

    /**
     * Initializes the variables of the Task class
     *
     * @param type Contains either T, D or E depending on the type of task
     * @param userDescription Contains the description of the task provided by the user
     */
    public Task(char type, String userDescription) {
        this.typeOfTask = type;
        this.description = userDescription;
        this.isDone = false;
    }

    /**
     * Returns a tick or a cross symbol for displaying of the status
     */
    public String getStatusIcon() {

        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Returns T, D or E depending on the type of task
     */
    public char getTypeOfTask() {

        return typeOfTask;
    }

    /**
     * Returns the description of the task
     */
    public String getDescription() {

        return description;
    }

    /**
     * Sets the status to done
     */
    public void markAsDone() {

        this.isDone = true;
    }

    /**
     * Returns the status of the task
     *
     */
    public boolean getIsDone() {

        return isDone;
    }

    /**
     * Declares abstract method to be override by sub class
     */
    public abstract String insertFile();
}
