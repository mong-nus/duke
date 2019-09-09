import dukeproject.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parse;
    private List<Task> lists;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList();
        this.parse = new Parser();
        this.lists = new ArrayList<Task>();

    }

    /**
     * run the duke program
     *
     */
    public void run() {

        ui.startDuke();
        storage.readFile(lists, ui);

        Scanner reader = new Scanner(System.in);

        while (true) {

            String inp = reader.nextLine();
            parse.checkInstruction(inp, storage, ui, lists, tasks);
        }
    }

    public static void main(String[] args) {

        new Duke().run();
    }
}
