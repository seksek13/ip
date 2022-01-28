package duke;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Duke {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showFileError();
        }
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean exit = false;

        while(!exit) {
            try{
                String input = ui.readCommand();
                String[] cmd = Parser.parse(input);
                if (cmd[0].equals("bye")) {
                    exit = true;
                    ui.showBye();

                } else if (cmd[0].equals("list")) {
                    ui.showTaskList(tasks);
                } else if (cmd[0].equals("mark")) {
                    int indexOfTask = Integer.parseInt(cmd[1]);
                    if (indexOfTask > tasks.size()) {
                       ui.showOutOfArray();
                    }
                    else{
                        Task mark = tasks.markTask(indexOfTask - 1);
                        storage.updateTaskStatus(indexOfTask,true);
                        ui.showMarkTask(mark);
                    }
                } else if (cmd[0].equals("unmark")) {
                    int indexOfTask = Integer.parseInt(cmd[1]);
                    if (indexOfTask > tasks.size()) {
                        ui.showOutOfArray();
                    }
                    else{
                        Task mark = tasks.UnmarkTask(indexOfTask - 1);
                        storage.updateTaskStatus(indexOfTask,false);
                        ui.showUnmarkTask(mark);
                    }
                } else if (cmd[0].equals("todo")) {
                    Task t = new Todo(cmd[1], false);
                    tasks.addTask(t);
                    String data = "T,0," + cmd[1];
                    storage.addToFile(data);
                    ui.showAddTask(t, tasks.size());

                } else if (cmd[0].equals("deadline")) {
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    LocalDateTime formattedDate = LocalDateTime.parse(cmd[2], format);
                    Task t = new Deadline(cmd[1], formattedDate, false);
                    tasks.addTask(t);
                    String data = "D,0," + cmd[1] + "," + cmd[2];
                    storage.addToFile(data);
                    ui.showAddTask(t, tasks.size());
                } else if (input.startsWith("event")) {
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    LocalDateTime formattedDate = LocalDateTime.parse(cmd[2], format);
                    Task t = new Event(cmd[1], formattedDate, false);
                    tasks.addTask(t);
                    String data = "E,0," + cmd[1] + "," + cmd[2];
                    storage.addToFile(data);
                    ui.showAddTask(t, tasks.size());

                } else if (input.startsWith("delete")) {
                    int index = Integer.parseInt(cmd[1]);
                    if (index > tasks.size()) {
                       ui.showOutOfArray();
                    }
                    else{
                        Task t = tasks.deleteTask(index - 1);
                        storage.deleteFileData(index);
                        ui.showDeleteTask(t, tasks.size());
                    }
                }

            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main (String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
