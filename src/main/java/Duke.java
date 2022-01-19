import java.io.*;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        boolean exit = false;
        ArrayList<Task> tasks = new ArrayList<Task>(100);

        while(!exit) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                exit = true;
                System.out.println("Bye. Hope to see you again soon!");
            }
            else if(input.equals("list")){
                System.out.println(" Here are the tasks in your list:");
                int tasksSize = tasks.size()+1;
                for(int i=1;i<tasksSize;i++){
                    System.out.println(i +".[" +tasks.get(i-1).getStatusIcon()+ "] "+ tasks.get(i-1).description);
                }
            }
            else if(input.startsWith("mark")){
                String[] cmd = input.split(" ");
                int indexofTask = Integer.parseInt(cmd[1])-1;
                tasks.get(indexofTask).markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("[" + tasks.get(indexofTask).getStatusIcon()+"] " + tasks.get(indexofTask).description );
            }
            else if(input.startsWith("unmark")){
                String[] cmd = input.split(" ");
                int indexofTask = Integer.parseInt(cmd[1])-1;
                tasks.get(indexofTask).markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet: ");
                System.out.println("[" + tasks.get(indexofTask).getStatusIcon()+"] " + tasks.get(indexofTask).description );
            }
            else {
                Task t= new Task(input);
                tasks.add(t);
                System.out.println("added: " + input);
            }
        }
    }
}
