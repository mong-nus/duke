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

        int counter = 0;
        Task[] lists = new Task[100];

        while (!inp.equals("bye")) {

            String[] check = inp.split(" ", 2);

            if (inp.equals("list")) {

                int itemNo = 1;
                System.out.println(line);

                for (int i = 0; i < counter; i++) {
                    System.out.println( itemNo + ". " + lists[i].toString());
                    itemNo++;
                }

                System.out.println(line);
                inp = reader.nextLine();

            } else if (check[0].equals("done")) {

                int num = Integer.parseInt(check[1]) - 1;
                lists[num].markAsDone();
                System.out.println(line);
                System.out.println("Nice! I've marked this task as done:\n" + lists[num].toString());
                System.out.println(line);
                inp = reader.nextLine();

            } else if (check[0].equals("todo")) {

                lists[counter] = new ToDo(check[1]);
                System.out.println(line);
                System.out.println("Got it. I've added this task:\n  " + lists[counter].toString()
                        + "\nNow you have " + (counter + 1) + " tasks in the list.");
                System.out.println(line);
                counter++;
                inp = reader.nextLine();

            } else if (check[0].equals("deadline")) {

                check = check[1].split(" /by ", 2);
                lists[counter] = new Deadline(check[0], check[1]);
                System.out.println(line);
                System.out.println("Got it. I've added this task:\n  " + lists[counter].toString()
                        + "\nNow you have " + (counter+1) + " tasks in the list.");
                System.out.println(line);
                counter++;
                inp = reader.nextLine();

            } else if (check[0].equals("event")) {

                check = check[1].split(" /at ", 2);
                lists[counter] = new Event(check[0], check[1]);
                System.out.println(line);
                System.out.println("Got it. I've added this task:\n  " + lists[counter].toString()
                        + "\nNow you have " + (counter+1) + " tasks in the list.");
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
