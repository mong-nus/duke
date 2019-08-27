import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String line = "__________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(line);

        Scanner reader = new Scanner(System.in);
        String inp = reader.nextLine();

        int counter = 0;
        Task[] lists = new Task[100];

        while (!inp.equals("bye")) {
            try {

                checkEmpty(inp);

                String[] check = inp.split(" ", 2);

                if (inp.equals("list")) {
                    listAllItems(lists, counter, line);
                    inp = reader.nextLine();

                } else if (check[0].equals("done")) {
                    makeDone(lists, counter, check, line);
                    inp = reader.nextLine();

                } else {

                    addTask(lists, counter, check, line);
                    counter++;
                    inp = reader.nextLine();

                }

            } catch (DukeException errorMessage) {

                System.out.println(line);
                System.out.println(errorMessage.getMessage());
                System.out.println(line);
                inp = reader.nextLine();

            } catch (NumberFormatException errorMessage) {

                System.out.println(line);
                System.out.println("\u2369 OOPS!!! only number are accepted after done");
                System.out.println(line);
                inp = reader.nextLine();

            } catch(ArrayIndexOutOfBoundsException errorMessage) {

                System.out.println(line);
                System.out.println("\u2369 OOPS!!! Input format Invalid");
                System.out.println(line);
                inp = reader.nextLine();

            }
        }

        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);

    }


    private static void checkEmpty(String userInput) throws DukeException {
        if (userInput.isEmpty()) {
            throw new DukeException("There is no command given");
        }
    }

    private static void listAllItems(Task[] listOfTasks, int arrCounter, String lineToPrint) throws DukeException {

        if(arrCounter == 0) {
            throw new DukeException("There is currently no task in the list");
        }

        int itemNo = 1;
        System.out.println(lineToPrint);

        for (int i = 0; i < arrCounter; i++) {
            System.out.println( itemNo + ". " + listOfTasks[i].toString());
            itemNo++;
        }

        System.out.println(lineToPrint);
    }



    private static void makeDone(Task[] listOfTasks, int arrCounter, String[] userInput, String lineToPrint) throws DukeException {

        int num = Integer.parseInt(userInput[1]) - 1;

        if ((num < 0) || (num > (arrCounter - 1))) {
            throw new DukeException("Digit provided is out of range");
        }

        listOfTasks[num].markAsDone();
        System.out.println(lineToPrint);
        System.out.println("Nice! I've marked this task as done:\n" + listOfTasks[num].toString());
        System.out.println(lineToPrint);
    }

    private static void addTask(Task[] listOfTasks, int arrCounter, String[] userInput, String lineToPrint) throws DukeException {

        if (userInput[0].equals("todo")) {

            if (userInput[1].isBlank()) {
                throw new DukeException("Description cannot be blank or space only");
            }

            listOfTasks[arrCounter] = new ToDo(userInput[1]);
            System.out.println(lineToPrint);
            System.out.println("Got it. I've added this task:\n  " + listOfTasks[arrCounter].toString()
                    + "\nNow you have " + (arrCounter + 1) + " tasks in the list.");
            System.out.println(lineToPrint);


        } else if (userInput[0].equals("deadline")) {

            userInput = userInput[1].split(" /by ", 2);

            if (userInput[0].isBlank() || userInput[1].isBlank()) {
                throw new DukeException("Description or date cannot be blank or space only");
            }

            listOfTasks[arrCounter] = new Deadline(userInput[0], userInput[1]);
            System.out.println(lineToPrint);
            System.out.println("Got it. I've added this task:\n  " + listOfTasks[arrCounter].toString()
                    + "\nNow you have " + (arrCounter + 1) + " tasks in the list.");
            System.out.println(lineToPrint);

        } else if (userInput[0].equals("event")) {

            userInput = userInput[1].split(" /at ", 2);

            if (userInput[0].isBlank() || userInput[1].isBlank()) {
                throw new DukeException("Description or date cannot be blank or space only");
            }

            listOfTasks[arrCounter] = new Event(userInput[0], userInput[1]);
            System.out.println(lineToPrint);
            System.out.println("Got it. I've added this task:\n  " + listOfTasks[arrCounter].toString()
                    + "\nNow you have " + (arrCounter + 1) + " tasks in the list.");
            System.out.println(lineToPrint);

        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }



}
