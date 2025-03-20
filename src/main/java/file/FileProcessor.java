package file;

import command.CommandGenerator;
import command.CommandInterface;

import java.io.IOException;
import java.util.List;

public class FileProcessor {
    private final File file;
    private final CommandGenerator commandGenerator;

    public FileProcessor(File file, CommandGenerator commandGenerator) {
        this.file = file;
        this.commandGenerator = commandGenerator;
    }

    public List<CommandInterface> getCommands() throws IOException {
        String LINE_DELIMITER = "\r\n";
        List<String> commandLines = this.file.getLines(LINE_DELIMITER);
        return commandLines.stream()
                .map(this.commandGenerator::generateCommand)
                .toList();
    }
}
