package dukeproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Storage {

    /**
     * Reads and Stores the list of task from the duke.txt file
     * Outer if condition will check the type of command being entered e.g. todo, list
     * Inner if condition will check the status of the task whether it done or not done
     *
     * @param listOfTasks Task object that contains all the different tasks such as event, deadline and todo
     * @param display Ui object used to call the display function for user to view task information
     */
    public void readFile(List<Task> listOfTasks, Ui display) {
        display.readStart();

        File file = new File("data");

        if (!file.isDirectory()) {
            display.printError("Folder does not exist. Creating folder data now.\n");
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
                        listOfTasks.get(listOfTasks.size() - 1).markAsDone();
                    } else {
                        data = data[1].split(" \\| ", 2);
                        Task currTask = new ToDo(data[1]);
                        listOfTasks.add(currTask);
                    }

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
                            listOfTasks.get(listOfTasks.size() - 1).markAsDone();
                        } catch (DateTimeParseException error) {
                            display.printError("Reading Fail after item No. " + listOfTasks.size() + "\n");
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
                            display.printError("Reading Fail after item No. " + listOfTasks.size() + "\n");
                        }
                    }
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
                            listOfTasks.get(listOfTasks.size() - 1).markAsDone();
                        } catch (DateTimeParseException error) {
                            display.printError("Reading Fail after item No. " + listOfTasks.size() + "\n");
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
                            display.printError("Reading Fail after item No. " + listOfTasks.size() + "\n");
                        }
                    }
                }
            }
        } catch(FileNotFoundException error) {
            display.printError("There is no file to read. File will be created when there is new records.\n");
        }

        display.readEnd();
    }

    /**
     * Writes to the duke.txt file when there is changes
     *
     * @param listOfTasks Task object that contains all the different tasks such as event, deadline and todo
     */
    public void writeFile(List<Task> listOfTasks) {

        File file = new File("data\\duke.txt");

        PrintWriter output = null;
        try {
            output = new PrintWriter(file);

            for (int i = 0; i < listOfTasks.size(); i++) {
                output.println(listOfTasks.get(i).insertFile());
            }

        } catch (IOException error) {

            System.out.println("There is a output error");

        } finally {

            if (output != null) {
                output.close();
            }

        }
    }
}
