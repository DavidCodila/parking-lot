import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReader {
    private final Path path;
    private final String lineDelimiter;

    public FileReader(String path, String delimiter) {
        this.path = Path.of(path);
        this.lineDelimiter = delimiter;
    }

    public String[] getLines() throws IOException {
        if (!Files.exists(this.path)) {
            throw new FileNotFoundException("File with path: " + this.path + " not found");
        }
        return Files.readString(this.path)
                .trim()
                .split(this.lineDelimiter);
    }
}
