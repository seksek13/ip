import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {
    public static void main(String[] args) throws DukeException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Sumikko :)");
        System.out.println("What can I do for you?");
        boolean exit = false;
        ArrayList<Task> tasks = new ArrayList<Task>(100);

        while(!exit) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                exit = true;
                System.out.println("Bye bye. See you next time!");
            }
            else if(input.equals("list")){
                System.out.println("Here are your tasks:");
                int tasksSize = tasks.size()+1;
                for(int i=1;i<tasksSize;i++){
                    System.out.println(i + "." + tasks.get(i-1));
                }
            }
            else if(input.startsWith("mark")){

                String[] cmd = input.split(" ");
                int indexofTask = Integer.parseInt(cmd[1])-1;
                if(indexofTask>=tasks.size()){
                    throw new DukeException("@ OOPS!!! number is out of list");
                }
                tasks.get(indexofTask).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(indexofTask));
            }
            else if(input.startsWith("unmark")){
                String[] cmd = input.split(" ");
                int indexofTask = Integer.parseInt(cmd[1])-1;
                if(indexofTask>=tasks.size()){
                    throw new DukeException("OOPS!!! number is out of list");
                }
                tasks.get(indexofTask).markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet!");
                System.out.println(tasks.get(indexofTask));
            }
            else if(input.startsWith("todo")){
                if(input.equals("todo")){
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }
                String[] description = input.split("todo ");
                Task t= new Todo(description[1]);
                tasks.add(t);
                System.out.println("Got it. I've added this task:");
                System.out.println(t);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            }
            else if(input.startsWith("deadline")){
                if (input.equals("deadline")) {
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                }
                String[] cmd = input.split("deadline ");
                String description = cmd[1].split(" /by")[0];
                String date = cmd[1].split("/by ")[1];
                LocalDate d1 = LocalDate.parse(date);
                Task t = new Deadline(description, d1);
                tasks.add(t);
                System.out.println("Got it. I've added this task:");
                System.out.println(t);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            }
            else if(input.startsWith("event")){
                if (input.equals("event")) {
                    throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                }
                String[] cmd = input.split("event ");
                String description = cmd[1].split(" /at")[0];
                String time = cmd[1].split("/at ")[1];
                LocalDate d1 = LocalDate.parse(time);
                Task t = new Event(description, d1);
                tasks.add(t);
                System.out.println("Got it. I've added this task:");
                System.out.println(t);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");

            }
            else if(input.startsWith("delete")){
                String[] description = input.split("delete ");
                int index = Integer.parseInt(description[1])-1;
                if(index>=tasks.size()){
                    throw new DukeException("OOPS!!! number is out of list!");
                }
                else if(input.equals("delete")){
                    throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                }
                Task t = tasks.get(index);
                tasks.remove(index);
                System.out.println("Noted. I've removed this task:");
                System.out.println(t);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            }

            else {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
