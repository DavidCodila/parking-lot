package file;

import command.CommandGenerator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class FileProcessorTest {
    @Test
    public void testGetCommands() throws IOException {
        File file = mock(File.class);
        CommandGenerator commandGenerator = mock(CommandGenerator.class);
        when(file.getLines(any(String.class))).thenReturn(List.of("PARK_CAR 0", "FIND 0"));
        FileProcessor fileProcessor = new FileProcessor(file, commandGenerator);
        fileProcessor.getCommands();
        verify(file, times(1)).getLines(any(String.class));
        verify(commandGenerator, times(2)).generateCommand(any(String.class));
    }
}
