package file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class File {
    private final Path path;

    public File(String pathToFile) throws FileNotFoundException {
        Path path = Path.of(pathToFile);
        if (!Files.exists(path)) {
            throw new FileNotFoundException("File with path: " + pathToFile + " not found");
        }
        this.path = path;
    }

    public String[] getLines(String delimiter) throws IOException {
        return Files.readString(this.path)
                .trim()
                .split(delimiter);
    }
}
