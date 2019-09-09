import dukeproject.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    Storage storage = new Storage();
    TaskList tasks = new TaskList();
    Ui ui = new Ui();
    Parser parse = new Parser();
    List<Task> lists = new ArrayList<Task>();
    DeleteListItems deleteList = new DeleteListItems();
    String compare, expected, input;

    /**
     * Checks whether the program parse correctly when a valid todo command is provided
     */
    @Test
    void testParseTodoValid() {
        input = "todo Homework and Project";
        parse.checkInstruction(input, storage, ui, lists, tasks);
        compare = lists.get(0).toString();
        expected = "[T][\u2718] Homework and Project";
        assertEquals(compare, expected);
        deleteList.deleteTaskItem(lists);
    }

    /**
     * Checks whether the program parse correctly when a invalid todo command is provided
     */
    @Test
    void testParseTodoInvalid() {
        input = "todo      ";
        parse.checkInstruction(input, storage, ui, lists, tasks);
        assertEquals(lists.size(), 0);
        deleteList.deleteTaskItem(lists);
    }

    /**
     * Checks whether the program parse correctly when a valid deadline command is provided
     */
    @Test
    void testParseDeadlineValid() {
        input = "deadline Project 1 /by 01-01-2020 23:59";
        parse.checkInstruction(input, storage, ui, lists, tasks);
        compare = lists.get(0).toString();
        expected = "[D][\u2718] Project 1 (by: 2020-01-01T23:59)";
        assertEquals(compare, expected);
        deleteList.deleteTaskItem(lists);
    }

    /**
     * Checks whether the program parse correctly when tomorrow is input as the date for deadline command
     */
    @Test
    void testParseDeadlineTomorrow() {
        input = "deadline Project 1 /by tomorrow";
        parse.checkInstruction(input, storage, ui, lists, tasks);
        compare = lists.get(0).toString();
        LocalDateTime tomorrow = LocalDateTime.now().withNano(0).withSecond(0).withHour(23).withMinute(59).plusDays(1);
        expected = "[D][\u2718] Project 1 (by: " + tomorrow.toString() + ")";
        assertEquals(compare, expected);
        deleteList.deleteTaskItem(lists);
    }

    /**
     * Checks whether the program parse correctly when a invalid deadline command is provided
     */
    @Test
    void testParseDeadlineInvalid() {
        input = "deadline Project 1 /at 01-01-2020 23:59";
        parse.checkInstruction(input, storage, ui, lists, tasks);
        assertEquals(lists.size(), 0);
        deleteList.deleteTaskItem(lists);
    }

    /**
     * Checks whether the program parse correctly when a valid event command is provided
     */
    @Test
    void testParseEventValid() {
        input = "event Meeting 1 /at 01-01-2020 23:59";
        parse.checkInstruction(input, storage, ui, lists, tasks);
        compare = lists.get(0).toString();
        expected = "[E][\u2718] Meeting 1 (at: 2020-01-01T23:59)";
        assertEquals(compare, expected);
        deleteList.deleteTaskItem(lists);
    }

    /**
     * Checks whether the program parse correctly when tomorrow is input as the date for event command
     */
    @Test
    void testParseEventTomorrow() {
        input = "event Meeting 1 /at tomorrow";
        parse.checkInstruction(input, storage, ui, lists, tasks);
        compare = lists.get(0).toString();
        LocalDateTime tomorrow = LocalDateTime.now().withNano(0).withSecond(0).withHour(13).withMinute(30).plusDays(1);
        expected = "[E][\u2718] Meeting 1 (at: " + tomorrow.toString() + ")";
        assertEquals(compare, expected);
        deleteList.deleteTaskItem(lists);
    }

    /**
     * Checks whether the program parse correctly when a invalid event command is provided
     */
    @Test
    void testParseEventInvalid() {
        input = "event Meeting 1 01-01-2020 23:59";
        parse.checkInstruction(input, storage, ui, lists, tasks);
        assertEquals(lists.size(), 0);
        deleteList.deleteTaskItem(lists);
    }

    @Test
    void testParseCheckEmpty() {
        input= "";
        DukeException err = assertThrows(DukeException.class, () -> parse.checkEmpty(input));
        assertEquals("⍩ OOPS!!! There is no command given", err.getMessage());
    }

}