public class ToDo extends Task {

    public ToDo(String inputDescription) {
        super('T', inputDescription);
    }


    @Override
    public String toString() {
        return "[" + super.getTypeOfTask() + "][" + super.getStatusIcon() + "] " + super.getDescription();
    }

    @Override
    public String insertFile() {

        if (super.getIsDone() == true) { //length of description to ensure if regex character is entered, code can still run
            return super.getTypeOfTask() + " | 1 | " + super.getDescription().length() + " | " + super.getDescription();
        } else {
            return super.getTypeOfTask() + " | 0 | " + super.getDescription().length() + " | " +  super.getDescription();
        }
    }




}
