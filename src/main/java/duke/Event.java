package duke;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime at;


    public Event(String description, LocalDateTime at, boolean done) {
        super(description,done);
        this.at = at;
    }

    public String parseDate() {
        return this.at.format(DateTimeFormatter.ofPattern("MMM dd yyyy 'at' hh:mm a"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " ( " + parseDate() + ")";
    }

}
