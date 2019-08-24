public class ToDo extends Task {

    public ToDo(String inputDescription) {
        super('T', inputDescription);
    }

    public String toString() {
        return "[" + super.getTypeOfTask() + "][" + super.getStatusIcon() + "] " + super.getDescription();
    }


}
