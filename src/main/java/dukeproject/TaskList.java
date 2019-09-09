package dukeproject;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    /**
     * Marks the task to be done
     *
     * @param listOfTasks Task object that contains all the different tasks such as event, deadline and todo
     * @param num Specifies the task within the list to be marked done
     * @param output Ui object used to call the display function for user to view task information
     * @param store storage object used to call the write to file function
     */
    public void makeDone(List<Task> listOfTasks, int num, Ui output, Storage store) {
        listOfTasks.get(num).markAsDone();
        output.taskMarked(listOfTasks, num);
        store.writeFile(listOfTasks);

    }

    /**
     * Deletes the specified item from the list of tasks
     *
     * @param listOfTasks Task object that contains all the different tasks such as event, deadline and todo
     * @param num Specifies the task within the list to be deleted
     * @param output Ui object used to call the display function for user to view task information
     * @param store storage object used to call the write to file function
     */
    public void deleteItem(List<Task> listOfTasks, int num, Ui output, Storage store) {
        Task currTask = listOfTasks.get(num);
        listOfTasks.remove(num);
        output.taskDeleted(listOfTasks, currTask);
        store.writeFile(listOfTasks);
    }

    /**
     * Adds new task to the list of tasks
     *
     * @param listOfTasks Task object that contains all the different tasks such as event, deadline and todo
     * @param toBeAdded Contains the new task to be added to the list of tasks
     * @param output Ui object used to call the display function for user to view task information
     * @param store storage object used to call the write to file function
     */
    public void addTask(List<Task> listOfTasks, Task toBeAdded, Ui output, Storage store) {
        listOfTasks.add(toBeAdded);
        output.taskAdded(listOfTasks);
        store.writeFile(listOfTasks);
    }

    /**
     * Finds task that matches user input
     *
     * @param listOfTasks Task object that contains all the different tasks such as event, deadline and todo
     * @param userInput Contains the string to be matched
     * @param output Ui object used to call the display function for user to view task information
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
