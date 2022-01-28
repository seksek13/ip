package duke;
import java.util.Scanner;

public class Ui {

    private Scanner input = new Scanner(System.in);

    public void showWelcomeMessage(){
        System.out.println("Hello! I'm Sumikko :)");
        System.out.println("What can I do for you?");
    }

    public String readCommand() {
        String userInput = input.nextLine();
        return userInput;
    }

    public void showFileError() {
        System.out.println("No task list file found! Creating a new file for you (:");
    }

    public void showOutOfArray(){
        System.out.println("Number of task is out of list!");
    }
    public void showAddTask(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showMarkTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void showUnmarkTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet!");
        System.out.println(task);
    }

    public void showDeleteTask(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showTaskList(TaskList tasks) {
        System.out.println(tasks);
    }

    public void showBye() {
        System.out.println("Bye bye. See you next time!");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showFindTask(TaskList tasks){
        System.out.println("Here are the matching tasks in your list:");
        System.out.println(tasks);
    }


}
