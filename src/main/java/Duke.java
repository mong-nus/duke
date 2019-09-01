import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String line = "__________________________________________________";
        int counter = 0;
        List<Task> lists = new ArrayList<Task>();
        System.out.println(line);
        counter = readFile(lists);
        System.out.println(line);

        System.out.println(line);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(line);

        Scanner reader = new Scanner(System.in);
        String inp = reader.nextLine();

        while (!inp.equals("bye")) {
            try {

                checkEmpty(inp);

                String[] check = inp.split(" ", 2);

                if (inp.equals("list")) {
                    listAllItems(lists, counter, line);
                    inp = reader.nextLine();

                } else if (check[0].equals("done")) {
                    makeDone(lists, counter, check, line);
                    writeFile(lists, counter);
                    inp = reader.nextLine();
                } else if (check[0].equals("delete")) {
                    deleteItem(lists, counter, check, line);
                    counter--;
                    writeFile(lists, counter);
                    inp = reader.nextLine();
                } else if (check[0].equals("find")) {
                    listFindItems(lists, counter, check, line);
                    inp = reader.nextLine();

                } else {
                    addTask(lists, counter, check, line);
                    counter++;
                    writeFile(lists, counter);
                    inp = reader.nextLine();

                }

            } catch (DukeException errorMessage) {

                System.out.println(line);
                System.out.println(errorMessage.getMessage());
                System.out.println(line);
                inp = reader.nextLine();

            } catch (NumberFormatException errorMessage) {

                System.out.println(line);
                System.out.println("\u2369 OOPS!!! only number are accepted after done or delete");
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


    public static void checkEmpty(String userInput) throws DukeException {
        if (userInput.isEmpty()) {
            throw new DukeException("There is no command given");
        }
    }

    public static void listAllItems(List<Task> listOfTasks, int arrCounter, String lineToPrint) throws DukeException {

        if(arrCounter == 0) {
            throw new DukeException("There is currently no task in the list");
        }

        int itemNo = 1;
        System.out.println(lineToPrint);

        for (int i = 0; i < arrCounter; i++) {
            System.out.println( itemNo + ". " + listOfTasks.get(i).toString());
            itemNo++;
        }

        System.out.println(lineToPrint);
    }



    public static void makeDone(List<Task> listOfTasks, int arrCounter, String[] userInput, String lineToPrint) throws DukeException {

        int num = Integer.parseInt(userInput[1]) - 1;

        if ((num < 0) || (num > (arrCounter - 1))) {
            throw new DukeException("Digit provided is out of range");
        }

        listOfTasks.get(num).markAsDone();
        System.out.println(lineToPrint);
        System.out.println("Nice! I've marked this task as done:\n" + listOfTasks.get(num).toString());
        System.out.println(lineToPrint);
    }
    public static void deleteItem(List<Task> listOfTasks, int arrCounter, String[] userInput, String lineToPrint) throws DukeException {

        int num = Integer.parseInt(userInput[1]) - 1;

        if ((num < 0) || (num > (arrCounter - 1))) {
            throw new DukeException("Digit provided is out of range");
        }

        Task currTask = listOfTasks.get(num);
        listOfTasks.remove(num);

        System.out.println(lineToPrint);
        System.out.println("Noted. I've removed this task:\n  " + currTask.toString()
                + "\nNow you have " + (arrCounter - 1) + " tasks in the list.");
        System.out.println(lineToPrint);

    }


    public static void addTask(List<Task> listOfTasks, int arrCounter, String[] userInput, String lineToPrint) throws DukeException {

        if (userInput[0].equals("todo")) {

            if (userInput[1].isBlank()) {
                throw new DukeException("Description cannot be blank or space only");
            }

            Task currTask = new ToDo(userInput[1]);

            listOfTasks.add(currTask);
            System.out.println(lineToPrint);
            System.out.println("Got it. I've added this task:\n  " + listOfTasks.get(arrCounter).toString()
                    + "\nNow you have " + (arrCounter + 1) + " tasks in the list.");
            System.out.println(lineToPrint);


        } else if (userInput[0].equals("deadline")) {

            userInput = userInput[1].split(" /by ", 2);

            if (userInput[0].isBlank() || userInput[1].isBlank()) {
                throw new DukeException("Description or date cannot be blank or space only");
            }

            if (userInput[1].equals("tomorrow")) {

                LocalDateTime today = LocalDateTime.now().withNano(0).withSecond(0).withHour(23).withMinute(59);
                LocalDateTime tomorrow = today.plusDays(1);

                Task currTask = new Deadline(userInput[0], tomorrow);

                listOfTasks.add(currTask);
                System.out.println(lineToPrint);
                System.out.println("Got it. I've added this task:\n  " + listOfTasks.get(arrCounter).toString()
                        + "\nNow you have " + (arrCounter + 1) + " tasks in the list.");
                System.out.println(lineToPrint);


            } else {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                try {
                    LocalDateTime dateTime = LocalDateTime.parse(userInput[1], formatter);
                    Task currTask = new Deadline(userInput[0], dateTime);
                    listOfTasks.add(currTask);
                    System.out.println(lineToPrint);
                    System.out.println("Got it. I've added this task:\n  " + listOfTasks.get(arrCounter).toString()
                            + "\nNow you have " + (arrCounter + 1) + " tasks in the list.");
                    System.out.println(lineToPrint);
                } catch (DateTimeParseException error) {
                    throw new DukeException("Invalid date. Please enter in the format of dd-MM-yyyy HH:mm (24HR) E.g. 21-01-2020 03:25, tomorrow is accepted as well");
                }

            }

        } else if (userInput[0].equals("event")) {

            userInput = userInput[1].split(" /at ", 2);

            if (userInput[0].isBlank() || userInput[1].isBlank()) {
                throw new DukeException("Description or date cannot be blank or space only");
            }

            if (userInput[1].equals("tomorrow")) {

                LocalDateTime today = LocalDateTime.now().withNano(0).withSecond(0).withHour(13).withMinute(30);
                LocalDateTime tomorrow = today.plusDays(1);

                Task currTask = new Event(userInput[0], tomorrow);

                listOfTasks.add(currTask);
                System.out.println(lineToPrint);
                System.out.println("Got it. I've added this task:\n  " + listOfTasks.get(arrCounter).toString()
                        + "\nNow you have " + (arrCounter + 1) + " tasks in the list.");
                System.out.println(lineToPrint);


            } else {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                try {
                    LocalDateTime dateTime = LocalDateTime.parse(userInput[1], formatter);

                    Task currTask = new Event(userInput[0], dateTime);
                    listOfTasks.add(currTask);
                    System.out.println(lineToPrint);
                    System.out.println("Got it. I've added this task:\n  " + listOfTasks.get(arrCounter).toString()
                            + "\nNow you have " + (arrCounter + 1) + " tasks in the list.");
                    System.out.println(lineToPrint);
                } catch (DateTimeParseException error) {
                    throw new DukeException("Invalid date. Please enter in the format of dd-MM-yyyy HH:mm (24HR) E.g. 21-01-2020 03:25, tomorrow is accepted as well");
                }

            }

        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    public static int readFile(List<Task> listOfTasks) {
        System.out.println("Reading Task List ... \n");
        int count = 0;
        File file = new File("data");

        if (!file.isDirectory()) {
            System.out.println("Folder does not exist. Creating folder data now.\n");
            file.mkdir();
        }

        file = new File("data\\duke.txt");

        try {

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {

                String[] data = scanner.nextLine().split(" \\| ", 2);

                if (data[0].equals("T")) {
                    data=data[1].split(" \\| ", 2);

                    if (data[0].equals("1")) {
                        data = data[1].split(" \\| ", 2);
                        Task currTask = new ToDo(data[1]);
                        listOfTasks.add(currTask);
                        listOfTasks.get(count).setDone();
                    } else {
                        data = data[1].split(" \\| ", 2);
                        Task currTask = new ToDo(data[1]);
                        listOfTasks.add(currTask);
                    }
                    count++;

                } else if (data[0].equals("D")) {
                    data=data[1].split(" \\| ", 2);

                    if (data[0].equals("1")) {
                        data=data[1].split(" \\| ", 2);
                        int lengthOfDescription = Integer.parseInt(data[0]);
                        String describe = data[1].substring(0, lengthOfDescription);

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                        try {
                            LocalDateTime dateTime = LocalDateTime.parse(data[1].substring(lengthOfDescription+3), formatter);
                            Task currTask = new Deadline(describe, dateTime);
                            listOfTasks.add(currTask);
                            listOfTasks.get(count).setDone();
                        } catch (DateTimeParseException error) {
                            System.out.println("Reading Fail at item No. " + count + "\n");
                            return count;
                        }

                    } else {
                        data=data[1].split(" \\| ", 2);
                        int lengthOfDescription = Integer.parseInt(data[0]);
                        String describe = data[1].substring(0, lengthOfDescription);

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                        try {
                            LocalDateTime dateTime = LocalDateTime.parse(data[1].substring(lengthOfDescription+3), formatter);
                            Task currTask = new Deadline(describe, dateTime);
                            listOfTasks.add(currTask);
                        } catch (DateTimeParseException error) {
                            System.out.println("Reading Fail at item No. " + count + "\n");
                            return count;
                        }
                    }
                    count++;
                } else if (data[0].equals("E")) {
                    data=data[1].split(" \\| ", 2);

                    if (data[0].equals("1")) {
                        data=data[1].split(" \\| ", 2);
                        int lengthOfDescription = Integer.parseInt(data[0]);
                        String describe = data[1].substring(0, lengthOfDescription);

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                        try {
                            LocalDateTime dateTime = LocalDateTime.parse(data[1].substring(lengthOfDescription+3), formatter);
                            Task currTask = new Event(describe, dateTime);
                            listOfTasks.add(currTask);
                            listOfTasks.get(count).setDone();
                        } catch (DateTimeParseException error) {
                            System.out.println("Reading Fail at item No. " + count + "\n");
                            return count;
                        }
                    } else {
                        data=data[1].split(" \\| ", 2);
                        int lengthOfDescription = Integer.parseInt(data[0]);
                        String describe = data[1].substring(0, lengthOfDescription);

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                        try {
                            LocalDateTime dateTime = LocalDateTime.parse(data[1].substring(lengthOfDescription+3), formatter);
                            Task currTask = new Event(describe, dateTime);
                            listOfTasks.add(currTask);
                        } catch (DateTimeParseException error) {
                            System.out.println("Reading Fail at item No. " + count + "\n");
                            return count;
                        }
                    }
                    count++;
                }
            }
        } catch(FileNotFoundException error) {
            System.out.println("There is no file to read. File will be created when there is new records.\n");
        }

        System.out.println("Read from file completed\n");
        return count;
    }

    public static void writeFile(List<Task> listOfTasks, int count) {

        File file = new File ("data\\duke.txt");

        try{

            PrintWriter output = new PrintWriter(file);

            for (int i = 0; i < count; i++) {
                output.println(listOfTasks.get(i).insertFile());
            }
            output.close();
        } catch(IOException error) {
            System.out.println("There is a output error");
        }


    }

    public static void listFindItems(List<Task> listOfTasks, int arrCounter, String[] userInput, String lineToPrint) throws DukeException {
        if (userInput[1].isBlank()) {
            throw new DukeException("Description cannot be blank or space only");
        }

        CharSequence seq = userInput[1];
        int itemNo = 1;

        System.out.println(lineToPrint);
        System.out.println("Here are the matching tasks in your list:");

        for (int i = 0; i < arrCounter; i++) {
            String toBeSearch = listOfTasks.get(i).toString();
            boolean bool = toBeSearch.contains(seq);
            if (bool == true) {
                System.out.println(itemNo + ". " + listOfTasks.get(i).toString());
                itemNo++;
            }
        }
        if(itemNo==1)
        {
            System.out.println("No items matched");
        }
        System.out.println(lineToPrint);
    }

}
