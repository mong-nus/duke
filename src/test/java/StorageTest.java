import dukeproject.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    Storage storage = new Storage();
    List<Task> lists = new ArrayList<Task>();
    DeleteListItems deleteList = new DeleteListItems();
    Ui ui = new Ui();

    /**
     * Checks whether the program run correctly when there are no file to read
     */
    @Test
    void readFromEmptyFile() {
        storage.readFile(lists, ui);
        assertEquals(lists.size(), 0);
        deleteList.deleteTaskItem(lists);
    }

    /**
     * Checks whether the program run correctly when there are file to read
     */
    @Test
    void readFromFileWithTasks() {
        storage.readFile(lists, ui);
        assertEquals(lists.size(), 5);
        deleteList.deleteTaskItem(lists);
    }
}