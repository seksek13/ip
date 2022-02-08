package duke;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

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
    public String getResponse(String input) throws DukeException {

        try {
            String[] commands = Parser.parse(input);
            if (commands[0].equals("bye")) {
                new Timer().schedule(new TimerTask() {
                    public void run () { System.exit(0); }
                }, 2000);
                return ui.showBye();

            } else if (commands[0].equals("list")) {
                return ui.showTaskList(tasks);
            } else if (commands[0].equals("mark")) {
                int indexOfTask = Integer.parseInt(commands[1]);
                // index is not in array
                if (indexOfTask > tasks.size()) {
                    return ui.showOutOfArray();
                } else {
                    Task mark = tasks.markTask(indexOfTask - 1);
                    storage.updateTaskStatus(indexOfTask, true);
                    return ui.showMarkTask(mark);
                }
            } else if (commands[0].equals("unmark")) {
                int indexOfTask = Integer.parseInt(commands[1]);
                // index is not in array
                if (indexOfTask > tasks.size()) {
                    return ui.showOutOfArray();
                } else {
                    Task mark = tasks.unmarkTask(indexOfTask - 1);
                    storage.updateTaskStatus(indexOfTask, false);
                    return ui.showUnmarkTask(mark);
                }
            } else if (commands[0].equals("todo")) {
                Task t = new Todo(commands[1], false);
                tasks.addTask(t);
                String data = "T,0," + commands[1];
                storage.addToFile(data);
                return ui.showAddTask(t, tasks.size());
            } else if (commands[0].equals("deadline")) {
                //format date time
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime formattedDate = LocalDateTime.parse(commands[2], format);
                Task t = new Deadline(commands[1], formattedDate, false);
                tasks.addTask(t);
                String data = "D,0," + commands[1] + "," + commands[2];
                storage.addToFile(data);
                return ui.showAddTask(t, tasks.size());
            } else if (input.startsWith("event")) {
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime formattedDate = LocalDateTime.parse(commands[2], format);
                Task t = new Event(commands[1], formattedDate, false);
                tasks.addTask(t);
                String data = "E,0," + commands[1] + "," + commands[2];
                storage.addToFile(data);
                return ui.showAddTask(t, tasks.size());
            } else if (input.startsWith("delete")) {
                int index = Integer.parseInt(commands[1]);
                // index is not in array
                if (index > tasks.size()) {
                    return ui.showOutOfArray();
                } else {
                    Task t = tasks.deleteTask(index - 1);
                    storage.deleteFileData(index);
                    return ui.showDeleteTask(t, tasks.size());
                }
            } else if (input.startsWith("find")) {
                TaskList t = new TaskList();
                Task[] temp = tasks.find(commands[1]);
                int count = 0;
                while (temp[count] != null) {
                    t.addTask(temp[count]);
                    count++;
                }
                return ui.showFindTask(t);
            } else {
                return "You have entered an invalid command! :(";
            }
        } catch (DukeException | IOException | IllegalArgumentException e) {
            return ui.showError(e.getMessage());
        }

    }


}
