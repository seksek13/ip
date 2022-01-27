import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {
    public static void main(String[] args) throws DukeException, IOException {
        File file;
        ArrayList<Task> tasks = new ArrayList<>(100);
        try {
            file = new File("./data/duke.txt");
            Scanner fileData = new Scanner(file);
            while(fileData.hasNextLine()){
                String data = fileData.nextLine();
                String[] dataSplited = data.split(",");
                String taskType = dataSplited[0].toUpperCase();
                boolean done = dataSplited[1].equals("1");

                switch(taskType){
                    case "T":
                        if(dataSplited.length!=3)
                            System.out.println("data: " + data + " not in correct format!");
                        else{
                            Task t = new Todo(dataSplited[2],done);
                            tasks.add(t);
                        }
                        break;
                    case "D":
                        if(dataSplited.length!=4)
                            System.out.println("data: " + data + " not in correct format!");
                        else{
                            LocalDate d1 = LocalDate.parse(dataSplited[3]);
                            Task t = new Deadline(dataSplited[2],d1,done);
                            tasks.add(t);
                        }
                        break;
                    case "E":
                        if(dataSplited.length!=4)
                            System.out.println("data: " + data + " not in correct format!");
                        else{
                            LocalDate d1 = LocalDate.parse(dataSplited[3]);
                            Task t = new Event(dataSplited[2], d1, done);
                            tasks.add(t);
                        }
                        break;
                    default:
                        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                        break;
                }
            }
            fileData.close();
        } catch (FileNotFoundException e) {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdir();
            }
            file = new File("./data/duke.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Sumikko :)");
        System.out.println("What can I do for you?");
        boolean exit = false;

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
                int indexofTask = Integer.parseInt(cmd[1]);
                if(indexofTask>tasks.size()) {
                    throw new DukeException("@ OOPS!!! number is out of list");
                }
                tasks.get(indexofTask-1).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(indexofTask-1));

                Scanner scanner = new Scanner(file);
                //store the string content of task to be updated
                String taskData = null;
                StringBuffer buffer = new StringBuffer();
                while (indexofTask > 0) {
                    taskData = scanner.nextLine();
                    indexofTask -= 1;
                }
                scanner.close();
                //store updated string
                String newData = taskData.replaceFirst("0", "1");
                scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    buffer.append(scanner.nextLine()+System.lineSeparator());
                }
                String fileContents = buffer.toString();
                fileContents = fileContents.replaceAll(taskData, newData);
                FileWriter writer = new FileWriter("./data/duke.txt");
                writer.append(fileContents);
                writer.flush();
            }
            else if(input.startsWith("unmark")){
                String[] cmd = input.split(" ");
                int indexofTask = Integer.parseInt(cmd[1]);
                if(indexofTask>tasks.size()){
                    throw new DukeException("OOPS!!! number is out of list");
                }
                tasks.get(indexofTask-1).markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet!");
                System.out.println(tasks.get(indexofTask-1));

                Scanner scanner = new Scanner(file);
                //store the string content of task to be updated
                String taskData = null;
                StringBuffer buffer = new StringBuffer();
                while (indexofTask > 0) {
                    taskData = scanner.nextLine();
                    indexofTask -= 1;
                }
                scanner.close();
                //store updated string
                String newData = taskData.replaceFirst("1", "0");
                scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    buffer.append(scanner.nextLine()+System.lineSeparator());
                }
                String fileContents = buffer.toString();
                fileContents = fileContents.replaceAll(taskData, newData);
                FileWriter writer = new FileWriter("./data/duke.txt");
                writer.append(fileContents);
                writer.flush();
            }
            else if(input.startsWith("todo")){
                if(input.equals("todo")){
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }
                String[] description = input.split("todo ");
                Task t= new Todo(description[1], false);
                tasks.add(t);
                String data = "T,0," + description[1];
                addToFile(data,file);
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
                Task t = new Deadline(description, d1,false);

                tasks.add(t);
                String data = "D,0," + description + "," + date;
                addToFile(data,file);
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
                Task t = new Event(description, d1,false);

                tasks.add(t);
                String data = "E,0," + description + "," + time;
                addToFile(data,file);
                System.out.println("Got it. I've added this task:");
                System.out.println(t);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");

            }
            else if(input.startsWith("delete")){
                String[] description = input.split("delete ");
                int index = Integer.parseInt(description[1]);
                if(index>tasks.size()){
                    throw new DukeException("OOPS!!! number is out of list!");
                }
                else if(input.equals("delete")){
                    throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                }
                Task t = tasks.get(index-1);
                tasks.remove(index-1);
                System.out.println("Noted. I've removed this task:");
                System.out.println(t);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                Scanner scanner = new Scanner(file);
                StringBuffer buffer = new StringBuffer();
                int currLine = 1;
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (currLine != index) {
                        buffer.append(line+System.lineSeparator());
                    }
                    currLine++;
                }
                String fileContents = buffer.toString();
                FileWriter writer = new FileWriter("./data/duke.txt");
                writer.append(fileContents);
                writer.flush();
            }

            else {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    public static void addToFile(String data, File file) throws IOException {

        Writer update = new BufferedWriter(new FileWriter(file, true));
        update.append(data + "\n");
        update.close();
    }
}
