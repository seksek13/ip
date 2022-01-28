package duke;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void byeTest() throws DukeException {
        String[] command = Parser.parse("bye");
        String[] expected = new String[]{"bye"};
        assertEquals(expected[0], command[0]);
    }
}
