import java.util.ArrayList;
import java.util.List;

public class TaskList {

    /**
     * mark the task to be done
     *
     */
    public void makeDone(List<Task> listOfTasks, int num, Ui output, Storage store) {
        listOfTasks.get(num).markAsDone();
        output.taskMarked(listOfTasks, num);
        store.writeFile(listOfTasks);

    }

    /**
     * delete the specified item from the list of tasks
     *
     */
    public void deleteItem(List<Task> listOfTasks, int num, Ui output, Storage store) {
        Task currTask = listOfTasks.get(num);
        listOfTasks.remove(num);
        output.taskDeleted(listOfTasks, currTask);
        store.writeFile(listOfTasks);
    }

    /**
     * add new task to the list of tasks
     *
     */
    public void addTask(List<Task> listOfTasks, Task toBeAdded, Ui output, Storage store) {
        listOfTasks.add(toBeAdded);
        output.taskAdded(listOfTasks);
        store.writeFile(listOfTasks);
    }

    /**
     * find task that matches user input
     *
     */
    public void findItems(List<Task> listOfTasks, String[] userInput, Ui output) {

        CharSequence seq = userInput[1];
        List<Task> foundItems = new ArrayList<Task>();

        for (int i = 0; i < listOfTasks.size(); i++) {
            String toBeSearch = listOfTasks.get(i).toString();
            boolean bool = toBeSearch.contains(seq);
            if (bool == true) {
                foundItems.add(listOfTasks.get(i));
            }
        }

        output.listFindItems(foundItems);
    }

}
