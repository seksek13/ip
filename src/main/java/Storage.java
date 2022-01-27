import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<Task>(100);
        File file;
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
            return tasks;
        } catch (FileNotFoundException e) {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdir();
            }
            file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            return tasks;
        }
    }

    public void addToFile(String data) throws IOException {
        File file = new File(filePath);
        Writer output = new BufferedWriter(new FileWriter(file, true));
        output.append(data + "\n");
        output.close();
    }

    public void deleteFileData(int index) throws IOException {

        File file = new File(filePath);
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
        FileWriter writer = new FileWriter(filePath);
        writer.append(fileContents);
        writer.flush();
    }

    public void updateTaskStatus(int index, boolean done) throws IOException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        //store the string content of task to be updated
        String task = null;
        StringBuffer buffer = new StringBuffer();
        while (index> 0) {
            task = scanner.nextLine();
            index -= 1;
        }
        scanner.close();
        String newData = null;
        //store updated string
        if(done){
            newData = task.replaceFirst("0", "1");
        }
        else{
            newData = task.replaceFirst("1", "0");
        }

        scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            buffer.append(scanner.nextLine()+System.lineSeparator());
        }
        String fileContents = buffer.toString();
        fileContents = fileContents.replaceAll(task, newData);
        FileWriter writer = new FileWriter(filePath);
        writer.append(fileContents);
        writer.flush();
    }
}