package dukeproject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Parser {

    /**
     * Checks whether the users input is empty
     *
     * @param userInput contains the input entered by the user
     * @throws DukeException if user input is empty.
     */
    public void checkEmpty(String userInput) throws DukeException {
        if (userInput.isEmpty()) {
            throw new DukeException("There is no command given");
        }
    }

    /**
     * Parses the input entered by the user accordingly to execute the correct command
     *
     * @param instruction inputs provided by the user
     * @param store storage object used to call the write to file function
     * @param display Ui object used to call the display function for user to view task information
     * @param allTask Contains all the task that is currently in the list
     * @param tasking TaskList object used to execute different function such as add task and delete task
     */
    public void checkInstruction(String instruction, Storage store, Ui display, List<Task> allTask, TaskList tasking) {

        try {
            checkEmpty(instruction);
            String[] check = instruction.split(" ", 2);

            if (instruction.equals("list")) {
                display.listAllItems(allTask);

            } else if (check[0].equals("done")) {
                int num = Integer.parseInt(check[1]) - 1;

                if ((num < 0) || (num > (allTask.size() - 1))) {
                    throw new DukeException("Digit provided is out of range");
                }

                tasking.makeDone(allTask, num, display, store);

            } else if (check[0].equals("delete")) {

                int num = Integer.parseInt(check[1]) - 1;

                if ((num < 0) || (num > (allTask.size() - 1))) {
                    throw new DukeException("Digit provided is out of range");
                }

                tasking.deleteItem(allTask, num, display, store);

            } else if (check[0].equals("find")) {

                if (check[1].isBlank()) {
                    throw new DukeException("Description cannot be blank or space only");
                }

                tasking.findItems(allTask, check, display);

            } else if (instruction.equals("bye")) {

                display.end();

            } else if (check[0].equals("todo")) {

                if (check[1].isBlank()) {
                    throw new DukeException("Description cannot be blank or space only");
                }

                Task currTask = new ToDo(check[1]);
                tasking.addTask(allTask, currTask, display, store);

            } else if (check[0].equals("deadline")){

                check = check[1].split(" /by ", 2);

                if (check[0].isBlank() || check[1].isBlank()) {
                    throw new DukeException("Description or date cannot be blank or space only");
                }

                if (check[1].equals("tomorrow")) {

                    LocalDateTime today = LocalDateTime.now().withNano(0).withSecond(0).withHour(23).withMinute(59);
                    LocalDateTime tomorrow = today.plusDays(1);

                    Task currTask = new Deadline(check[0], tomorrow);
                    tasking.addTask(allTask, currTask, display, store);


                } else {

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                    try {
                        LocalDateTime dateTime = LocalDateTime.parse(check[1], formatter);
                        Task currTask = new Deadline(check[0], dateTime);
                        tasking.addTask(allTask, currTask, display, store);

                    } catch (DateTimeParseException error) {
                        throw new DukeException("Invalid date. Please enter in the format of dd-MM-yyyy HH:mm (24HR) " +
                                "E.g. 21-01-2020 03:25, tomorrow is accepted as well");
                    }
                }
            } else if (check[0].equals("event")) {

                check = check[1].split(" /at ", 2);

                if (check[0].isBlank() || check[1].isBlank()) {
                    throw new DukeException("Description or date cannot be blank or space only");
                }

                if (check[1].equals("tomorrow")) {

                    LocalDateTime today = LocalDateTime.now().withNano(0).withSecond(0).withHour(13).withMinute(30);
                    LocalDateTime tomorrow = today.plusDays(1);

                    Task currTask = new Event(check[0], tomorrow);

                    tasking.addTask(allTask, currTask, display, store);


                } else {

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                    try {
                        LocalDateTime dateTime = LocalDateTime.parse(check[1], formatter);

                        Task currTask = new Event(check[0], dateTime);
                        tasking.addTask(allTask, currTask, display, store);

                    } catch (DateTimeParseException error) {
                        throw new DukeException("Invalid date. Please enter in the format of dd-MM-yyyy HH:mm (24HR) " +
                                "E.g. 21-01-2020 03:25, tomorrow is accepted as well");
                    }

                }

            } else {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }

        } catch (DukeException errorMessage) {

            display.printError(errorMessage.getMessage());

        } catch (NumberFormatException errorMessage) {

            display.printError("\u2369 OOPS!!! only number are accepted after done or delete");

        } catch(ArrayIndexOutOfBoundsException errorMessage) {

            display.printError("\u2369 OOPS!!! Input format Invalid");

        }
    }


}
