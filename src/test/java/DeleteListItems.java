import dukeproject.Task;

import java.util.List;

public class DeleteListItems {

    /**
     * Deletes all tasks within the list after each test case is being run
     */
    public void deleteTaskItem (List<Task> listsOfTasks) {

        while (listsOfTasks.size()!=0) {
            listsOfTasks.remove(0);
        }
    }
}
