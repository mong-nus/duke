import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String line = "__________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(line);

        Scanner reader = new Scanner(System.in);
        String inp = reader.nextLine();

        int counter=0;
        String[] lists = new String[100];

        while (!inp.equals("bye")) {

            if (inp.equals("list")) {
                int itemNo = 1;
                System.out.println(line);

                for (int i = 0; i < counter; i++) {
                    System.out.println(itemNo + ". " + lists[i]);
                    itemNo++;
                }

                System.out.println(line);
                inp = reader.nextLine();
            } else {
                lists[counter] = inp;
                System.out.println(line);
                System.out.println("added: " + inp);
                System.out.println(line);
                counter++;
                inp = reader.nextLine();
            }
        }

        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);

    }
}
