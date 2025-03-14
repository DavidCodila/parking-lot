import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReader {
    private final Path path;

    public FileReader(String path) {
        this.path = Path.of(path);
    }

    public String[] getLines(String delimiter) throws IOException {
        if (!Files.exists(this.path)) {
            throw new FileNotFoundException("File with path: " + this.path + " not found");
        }
        return Files.readString(this.path)
                .trim()
                .split(delimiter);
    }
}
