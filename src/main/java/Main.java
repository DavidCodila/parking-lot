import application.ParkingLot;
import command.FindCarCommand;
import command.ListCarCommand;
import command.ParkCarCommand;
import command.UnParkCarCommand;
import command.interfaces.CommandInterface;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        final int MAX_CAPACITY = 5;
        ParkingLot parkingLot = new ParkingLot(MAX_CAPACITY);
        final String PATH_TO_COMMAND_FILE = "./src/main/resources/commands";

        try {
            Files.readAllLines(Path.of(PATH_TO_COMMAND_FILE))
                    .stream()
                    .map(Main::generateCommand)
                    .toList()
                    .forEach(command -> command.execute(parkingLot));
        } catch (IOException e) {
            throw new FileNotFoundException("File with path: " + PATH_TO_COMMAND_FILE + " not found");
        }
    }

    private static CommandInterface generateCommand(String commandLine) {
        final int PARAMETER_INDEX = 1;
        List<String> split = Arrays.asList(commandLine.split(" "));
        String command = split.getFirst();
        List<String> parameterLineSplit = split.subList(PARAMETER_INDEX, split.size());

        return switch (command) {
            case "PARK_CAR" -> new ParkCarCommand(parameterLineSplit);
            case "FIND" -> new FindCarCommand(parameterLineSplit);
            case "UN_PARK_CAR" -> new UnParkCarCommand(parameterLineSplit);
            case "LIST" -> new ListCarCommand(parameterLineSplit);
            default -> throw new InvalidParameterException("Command " + command + " is not valid");
        };
    }
}
