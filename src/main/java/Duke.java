import java.io.*;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        boolean exit = false;
        ArrayList<String> list = new ArrayList<>(100);

        while(!exit) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                exit = true;
                System.out.println("Bye. Hope to see you again soon!");
            }
            else if(input.equals("list")){
                int listSize = list.size()+1;
                for(int i=1;i<listSize;i++){
                    System.out.println(i +". " + list.get(i-1));
                }
            }
            else {
                list.add(input);
                System.out.println("added: " + input);
            }
        }
    }
}
