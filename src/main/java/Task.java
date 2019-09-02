public abstract class Task {

    protected String description;
    protected boolean isDone;
    protected char typeOfTask;

    public Task(char type, String userDescription) {
        this.typeOfTask = type;
        this.description = userDescription;
        this.isDone = false;
    }

    /**
     * return a tick or a cross symbol for displaying of the status
     *
     */
    public String getStatusIcon() {

        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * return a T, D or E depending on the type of task
     *
     */
    public char getTypeOfTask() {

        return typeOfTask;
    }

    /**
     * return the description of the task
     *
     */
    public String getDescription() {

        return description;
    }

    /**
     * set the status to done
     *
     */
    public void markAsDone() {

        this.isDone = true;
    }

    /**
     * return the status of the task
     *
     */
    public boolean getIsDone() {

        return isDone;
    }

    /**
     * abstract method to be override by sub class
     *
     */
    public abstract String insertFile();
}
