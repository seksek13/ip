package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description,isDone);
        this.by = by;
    }

    public String parseDate() {

        return this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy 'by' hh:mm a "));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " ( " + parseDate() + ")";
    }
}