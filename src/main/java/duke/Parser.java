package duke;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    /**
     * Getting required info from user input
     *
     * @param command User input
     * @return String list to store required info
     * @throws DukeException
     */
    public static String[] parse(String command) throws DukeException {

        if (command.equals("bye")) {
            String[] description = new String[] { "bye" };
            return description;
        } else if (command.equals("list")) {
            String[] description = new String[] { "list" };
            return description;
        } else if (command.startsWith("mark")) {
            String[] cmd = command.split(" ");
            String[] description = new String[] { "mark", cmd[1]};
            return description;
        } else if(command.startsWith("unmark")){
            String[] cmd = command.split(" ");
            String[] description = new String[] { "unmark", cmd[1] };
            return description;
        } else if (command.startsWith("delete")) {
            String[] cmd = command.split(" ");
            String[] description = new String[] { "delete", cmd[1] };
            return description;
        } else if (command.startsWith("todo")) {
            String[] cmd = command.split("todo ");
            if (cmd.length!=2) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            String[] description = new String[] { "todo", cmd[1] };
            return description;
        } else if (command.startsWith("deadline")) {
            if (command.equals("deadline")) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }
            String[] cmd = command.split("deadline ");
            String[] task = cmd[1].split("/by ");

            if(task.length!=2) {
                throw new DukeException("OOPS!!! The description of the deadline is not complete!");
            }
            try {
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime formattedDate = LocalDateTime.parse(task[1], format);

            } catch (DateTimeException e) {
                throw new DukeException("'" + task[1] + "' is in wrong format! Please enter date and time as dd/MM/yyyy HH:mm");
            }
            String[] description = new String[] { "deadline", task[0], task[1] };
            return description;
        } else if (command.startsWith("event")) {
            if (command.equals("event")) {
                throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
            }
            String[] cmd = command.split("event ");
            String[] task = cmd[1].split("/at ");

            if(task.length!=2){
                throw new DukeException("☹ OOPS!!! The description of the event is not complete!");
            }
            try {
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime formattedDate = LocalDateTime.parse(task[1], format);

            } catch (DateTimeException e) {
                throw new DukeException("'" + task[1] + "' is in wrong format! Please enter date and time as dd/MM/yyyy HH:mm");
            }
            String[] description = new String[] { "event", task[0], task[1] };
            return description;
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}