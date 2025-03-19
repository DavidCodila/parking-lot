package file;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileTest {
    private static final String PATH_TO_TEST_COMMAND_FILE = "./src/test/resources/commands";
    private static final String INCORRECT_PATH_TO_FILE = "./src/test/resources/noFile";
    private static final String LINE_DELIMITER_FOR_TEST = "\r\n";
    private File file;


    @Test
    public void testGetLines() throws IOException {
        this.file = new File(PATH_TO_TEST_COMMAND_FILE);
        List<String> expectedOutput = List.of("PARK_CAR 0", "FIND 0", "PARK_CAR 1", "LIST 0 1", "UN_PARK_CAR 0");
        assertLinesMatch(expectedOutput, file.getLines(LINE_DELIMITER_FOR_TEST));
    }

    @Test
    public void testIncorrectPathPassed() {
        var exception = assertThrows(FileNotFoundException.class,
                () -> this.file = new File(INCORRECT_PATH_TO_FILE));
        assertEquals("File with path: " + INCORRECT_PATH_TO_FILE + " not found", exception.getMessage());
    }
}
