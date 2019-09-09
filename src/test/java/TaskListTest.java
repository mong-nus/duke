import dukeproject.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {


    Storage storage = new Storage();
    TaskList tasks = new TaskList();
    Ui ui = new Ui();
    Parser parse = new Parser();
    List<Task> lists = new ArrayList<Task>();
    DeleteListItems deleteList = new DeleteListItems();
    String compare, expected;

    /**
     * Checks whether the program correctly add todo task to the list
     */
    @Test
    void testAddTaskToDo() {
        Task currTask = new ToDo("Assignment");
        tasks.addTask(lists, currTask, ui, storage);
        compare = lists.get(0).toString();
        expected = "[T][\u2718] Assignment";
        assertEquals(compare, expected);
        deleteList.deleteTaskItem(lists);
    }

    /**
     * Checks whether the program correctly make done the todo task in the list
     */
    @Test
    void testMakeDoneToDoTask() {
        Task currTask = new ToDo("Assignment");
        tasks.addTask(lists, currTask, ui, storage);
        tasks.makeDone(lists, 0, ui, storage);
        expected = "[T][\u2713] Assignment";
        compare = lists.get(0).toString();
        assertEquals(compare, expected);
        deleteList.deleteTaskItem(lists);
    }

    /**
     * Checks whether the program correctly delete the todo task in the list
     */
    @Test
    void testDeleteToDoTask() {
        Task currTask = new ToDo("Assignment");
        tasks.addTask(lists, currTask, ui, storage);
        assertEquals(lists.size(), 1);
        tasks.deleteItem(lists, 0, ui, storage);
        assertEquals(lists.size(), 0);
        deleteList.deleteTaskItem(lists);
    }

    /**
     * Checks whether the program correctly add the deadline task to the list
     */
    @Test
    void testAddTaskDeadline() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        try {
            LocalDateTime dateTime = LocalDateTime.parse("01-01-2020 23:59", formatter);
            Task currTask = new Deadline("Project 1", dateTime);
            tasks.addTask(lists, currTask, ui, storage);

        } catch (DateTimeParseException error) {
            System.out.println("Invalid date. Please enter in the format of dd-MM-yyyy HH:mm (24HR) " +
                    "E.g. 21-01-2020 03:25, tomorrow is accepted as well");
        }
        compare = lists.get(0).toString();
        expected = "[D][\u2718] Project 1 (by: 2020-01-01T23:59)";
        assertEquals(compare, expected);
        deleteList.deleteTaskItem(lists);
    }

    /**
     * Checks whether the program correctly make done the deadline task in the list
     */
    @Test
    void testMakeDoneDeadlineTask() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        try {
            LocalDateTime dateTime = LocalDateTime.parse("01-01-2020 23:59", formatter);
            Task currTask = new Deadline("Project 1", dateTime);
            tasks.addTask(lists, currTask, ui, storage);

        } catch (DateTimeParseException error) {
            System.out.println("Invalid date. Please enter in the format of dd-MM-yyyy HH:mm (24HR) " +
                    "E.g. 21-01-2020 03:25, tomorrow is accepted as well");
        }
        tasks.makeDone(lists, 0, ui, storage);
        compare = lists.get(0).toString();
        expected = "[D][\u2713] Project 1 (by: 2020-01-01T23:59)";
        assertEquals(compare, expected);
        deleteList.deleteTaskItem(lists);
    }

    /**
     * Checks whether the program correctly delete the deadline task in the list
     */
    @Test
    void testDeleteDeadlineTask() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        try {
            LocalDateTime dateTime = LocalDateTime.parse("01-01-2020 23:59", formatter);
            Task currTask = new Deadline("Project 1", dateTime);
            tasks.addTask(lists, currTask, ui, storage);

        } catch (DateTimeParseException error) {
            System.out.println("Invalid date. Please enter in the format of dd-MM-yyyy HH:mm (24HR) " +
                    "E.g. 21-01-2020 03:25, tomorrow is accepted as well");
        }
        assertEquals(lists.size(), 1);
        tasks.deleteItem(lists, 0, ui, storage);
        assertEquals(lists.size(), 0);
        deleteList.deleteTaskItem(lists);
    }

    /**
     * Checks whether the program correctly add the event task to the list
     */
    @Test
    void testAddTaskEvent() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        try {
            LocalDateTime dateTime = LocalDateTime.parse("01-01-2020 23:59", formatter);
            Task currTask = new Event("Meeting 1", dateTime);
            tasks.addTask(lists, currTask, ui, storage);

        } catch (DateTimeParseException error) {
            System.out.println("Invalid date. Please enter in the format of dd-MM-yyyy HH:mm (24HR) " +
                    "E.g. 21-01-2020 03:25, tomorrow is accepted as well");
        }
        compare = lists.get(0).toString();
        expected = "[E][\u2718] Meeting 1 (at: 2020-01-01T23:59)";
        assertEquals(compare, expected);
        deleteList.deleteTaskItem(lists);
    }


    /**
     * Checks whether the program correctly make done the event task in the list
     */
    @Test
    void testMakeDoneEventTask() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        try {
            LocalDateTime dateTime = LocalDateTime.parse("01-01-2020 23:59", formatter);
            Task currTask = new Event("Meeting 1", dateTime);
            tasks.addTask(lists, currTask, ui, storage);

        } catch (DateTimeParseException error) {
            System.out.println("Invalid date. Please enter in the format of dd-MM-yyyy HH:mm (24HR) " +
                    "E.g. 21-01-2020 03:25, tomorrow is accepted as well");
        }
        tasks.makeDone(lists, 0, ui, storage);
        compare = lists.get(0).toString();
        expected = "[E][\u2713] Meeting 1 (at: 2020-01-01T23:59)";
        assertEquals(compare, expected);
        deleteList.deleteTaskItem(lists);
    }

    /**
     * Checks whether the program correctly delete the event task in the list
     */
    @Test
    void testDeleteEventTask() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        try {
            LocalDateTime dateTime = LocalDateTime.parse("01-01-2020 23:59", formatter);
            Task currTask = new Event("Meeting 1", dateTime);
            tasks.addTask(lists, currTask, ui, storage);

        } catch (DateTimeParseException error) {
            System.out.println("Invalid date. Please enter in the format of dd-MM-yyyy HH:mm (24HR) " +
                    "E.g. 21-01-2020 03:25, tomorrow is accepted as well");
        }
        assertEquals(lists.size(), 1);
        tasks.deleteItem(lists, 0, ui, storage);
        assertEquals(lists.size(), 0);
        deleteList.deleteTaskItem(lists);
    }
}