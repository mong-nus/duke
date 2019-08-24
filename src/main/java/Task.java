public class Task {

    protected String description;
    protected boolean isDone;
    protected char typeOfTask;

    public Task(char type, String userDescription) {
        this.typeOfTask = type;
        this.description = userDescription;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return a tick or a cross symbol
    }

    public char getTypeOfTask() {
        return typeOfTask;
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        this.isDone = true;
    }
}
