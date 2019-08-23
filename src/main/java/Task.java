public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String userDescription) {
        this.description = userDescription;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return a tick or a cross symbol
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        this.isDone = true;
    }
}
