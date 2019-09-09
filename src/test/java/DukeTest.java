import dukeproject.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    /**
     * Calls all the test case in TaskListTest
     */
    @Test
    void testTaskListTestCase() throws DukeException {
        TaskListTest tasklistTest = new TaskListTest();
        tasklistTest.testAddTaskToDo();
        tasklistTest.testMakeDoneToDoTask();
        tasklistTest.testDeleteToDoTask();
        tasklistTest.testAddTaskDeadline();
        tasklistTest.testMakeDoneDeadlineTask();
        tasklistTest.testDeleteDeadlineTask();
        tasklistTest.testAddTaskEvent();
        tasklistTest.testMakeDoneEventTask();
        tasklistTest.testDeleteEventTask();
    }

    /**
     * Calls all the test case in ParserTest
     */
    @Test
    void testParserTestCase() {
        ParserTest parseTest = new ParserTest();
        parseTest.testParseTodoValid();
        parseTest.testParseTodoInvalid();
        parseTest.testParseDeadlineValid();
        parseTest.testParseDeadlineTomorrow();
        parseTest.testParseDeadlineInvalid();
        parseTest.testParseEventValid();
        parseTest.testParseEventTomorrow();
        parseTest.testParseEventInvalid();
        parseTest.testParseCheckEmpty();
    }
}
