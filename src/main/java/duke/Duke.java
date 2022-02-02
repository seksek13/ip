package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Duke {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Constructor for Duke class
     *
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showFileError();
        }
    }

    /**
     * Method to run entire programme
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;


        while(!isExit) {
            try{
                String input = ui.readCommand();
                String[] cmds = Parser.parse(input);
                if (cmds[0].equals("bye")) {
                    isExit = true;
                    ui.showBye();

                } else if (cmds[0].equals("list")) {
                    ui.showTaskList(tasks);
                } else if (cmds[0].equals("mark")) {
                    int indexOfTask = Integer.parseInt(cmds[1]);
                    // index is not in array
                    if (indexOfTask > tasks.size()) {
                        ui.showOutOfArray();
                    } else {
                        Task mark = tasks.markTask(indexOfTask - 1);
                        storage.updateTaskStatus(indexOfTask, true);
                        ui.showMarkTask(mark);
                    }
                } else if (cmds[0].equals("unmark")) {
                    int indexOfTask = Integer.parseInt(cmds[1]);
                    // index is not in array
                    if (indexOfTask > tasks.size()) {
                        ui.showOutOfArray();
                    } else{
                        Task mark = tasks.UnmarkTask(indexOfTask - 1);
                        storage.updateTaskStatus(indexOfTask, false);
                        ui.showUnmarkTask(mark);
                    }
                } else if (cmds[0].equals("todo")) {
                    Task t = new Todo(cmds[1], false);
                    tasks.addTask(t);
                    String data = "T,0," + cmds[1];
                    storage.addToFile(data);
                    ui.showAddTask(t, tasks.size());
                } else if (cmds[0].equals("deadline")) {
                    //format date time
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    LocalDateTime formattedDate = LocalDateTime.parse(cmds[2], format);
                    Task t = new Deadline(cmds[1], formattedDate, false);
                    tasks.addTask(t);
                    String data = "D,0," + cmds[1] + "," + cmds[2];
                    storage.addToFile(data);
                    ui.showAddTask(t, tasks.size());
                } else if (input.startsWith("event")) {
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    LocalDateTime formattedDate = LocalDateTime.parse(cmds[2], format);
                    Task t = new Event(cmds[1], formattedDate, false);
                    tasks.addTask(t);
                    String data = "E,0," + cmds[1] + "," + cmds[2];
                    storage.addToFile(data);
                    ui.showAddTask(t, tasks.size());

                } else if (input.startsWith("delete")) {
                    int index = Integer.parseInt(cmds[1]);
                    // index is not in array
                    if (index > tasks.size()) {
                        ui.showOutOfArray();
                    } else {
                        Task t = tasks.deleteTask(index - 1);
                        storage.deleteFileData(index);
                        ui.showDeleteTask(t, tasks.size());
                    }
                } else if (input.startsWith("find")) {
                    TaskList t = new TaskList();
                    Task[] temp = tasks.find(cmds[1]);
                    int count = 0;
                    while (temp[count] != null) {
                        t.addTask(temp[count]);
                        count++;
                    }
                    ui.showFindTask(t);
                }
            } catch (DukeException | IOException  | IllegalArgumentException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Duke class main method
     *
     * @param args
     */
    public static void main (String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
