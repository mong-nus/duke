import java.util.List;

public class Ui {

    private String logo;
    private String line;

    public Ui() {
        this.logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        this.line = "__________________________________________________";

    }

    /**
     * starting message when duke is being run
     *
     */
    public void startDuke() {
        System.out.println("Hello from\n" + logo);
        System.out.println(line);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(line);
    }

    /**
     * ending message displayed when "bye" is entered, program will also be exited
     *
     */
    public void end() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
        System.exit(0);
    }

    /**
     * print all task in the list
     *
     */
    public void listAllItems (List<Task> listOfTasks) throws DukeException {
        if(listOfTasks.size() == 0) {
            throw new DukeException("There is currently no task in the list");
        }

        int itemNo = 1;
        System.out.println(line);

        for (int i = 0; i < listOfTasks.size(); i++) {
            System.out.println( itemNo + ". " + listOfTasks.get(i).toString());
            itemNo++;
        }

        System.out.println(line);
    }

    /**
     * print task that matches the search
     *
     */
    public void listFindItems(List<Task> listOfTasks) {

        int itemNo = 1;

        System.out.println(line);
        System.out.println("Here are the matching tasks in your list:");

        if (listOfTasks.size() == 0) {

                System.out.println("No items matched");
        } else {

            for (int i = 0; i < listOfTasks.size(); i++) {
                String toBeSearch = listOfTasks.get(i).toString();
                System.out.println(itemNo + ". " + listOfTasks.get(i).toString());
                itemNo++;
            }
        }
        System.out.println(line);

    }

    /**
     * print the tasked that was just mark done
     *
     */
    public void taskMarked(List<Task> listOfTasks, int num) {
        System.out.println(line);
        System.out.println("Nice! I've marked this task as done:\n" + listOfTasks.get(num).toString());
        System.out.println(line);

    }

    /**
     * print the task that was just deleted
     *
     */
    public void taskDeleted(List<Task> listOfTasks, Task theTask) {
        System.out.println(line);
        System.out.println("Noted. I've removed this task:\n  " + theTask.toString()
                + "\nNow you have " + listOfTasks.size() + " tasks in the list.");
        System.out.println(line);

    }

    /**
     * print the task that was just added
     *
     */
    public void taskAdded(List<Task> listOfTasks) {
        System.out.println(line);
        System.out.println("Got it. I've added this task:\n  " + listOfTasks.get(listOfTasks.size() - 1).toString()
                + "\nNow you have " + (listOfTasks.size()) + " tasks in the list.");
        System.out.println(line);

    }

    /**
     * print the error message
     *
     */
    public void printError(String error) {
        System.out.println(line);
        System.out.println(error);
        System.out.println(line);
    }

    /**
     * print the error message
     *
     */
    public void readStart() {
        System.out.println(line);
        System.out.println("Reading Task List ... \n");
    }

    /**
     * display message when reading of file is completed
     *
     */
    public void readEnd() {
        System.out.println("Read Operation completed\n");
        System.out.println(line);
    }




}
