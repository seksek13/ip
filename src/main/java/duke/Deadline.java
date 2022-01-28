package duke;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by; 

    /**
     * Deadline task constructor
     *
     * @param description description of command
     * @param by date and time deadline is due
     * @param done status of task
     */
    public Deadline(String description, LocalDateTime by, boolean done) {
        super(description,done);
        this.by = by;
    }

    /**
     * Parse date into MMM dd yyyy format
     *
     * @return date in MMM dd yyyy hh:mm a format
     */
    public String parseDate() {

        return this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy 'by' hh:mm a "));
    }

    /**
     * Overrides toString method
     *
     * @return description of task and date
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " ( " + parseDate() + ")";
    }
}