import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        Path path = Path.of("./src/main/resources/commands");
        if (Files.exists(path)) {
            String commands = Files.readString(path).trim();
            String[] listOfCommands = commands.split("\r\n");
            System.out.println(Arrays.toString(listOfCommands));
        } else throw new FileNotFoundException("File with path: " + path + " not found");
    }
}
