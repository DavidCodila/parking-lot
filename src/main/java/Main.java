import application.BasicSlotRecordGenerator;
import application.ParkingLot;
import application.SlotHandler;
import command.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static final String PARK_CAR = "PARK_CAR";
    private static final String FIND = "FIND";
    private static final String UN_PARK_CAR = "UN_PARK_CAR";
    private static final String LIST = "LIST";


    public static void main(String[] args) throws IOException {
        final int MAX_CAPACITY = 5;
        List<String> commandLines;
        final String PATH_TO_COMMAND_FILE = "./src/main/resources/commands";
        try {
            commandLines = Files.readAllLines(Path.of(PATH_TO_COMMAND_FILE));
        } catch (IOException e) {
            throw new FileNotFoundException("File with path: " + PATH_TO_COMMAND_FILE + " not found");
        }

        List<CommandInterface> commands = commandLines.stream()
                .map(Main::generateCommand)
                .toList();

        ParkingLot parkingLot = new ParkingLot(
                MAX_CAPACITY,
                new SlotHandler(new BasicSlotRecordGenerator(MAX_CAPACITY))
        );

        commands.forEach(command -> command.execute(parkingLot));
    }

    private static CommandInterface generateCommand(String commandLine) {
        final String DELIMITER = " ";
        List<String> split = Arrays.stream(commandLine.split(DELIMITER)).toList();
        String command = split.getFirst();
        final int PARAMETER_INDEX = 1;
        List<String> parameterLineSplit = split.subList(PARAMETER_INDEX, split.size());

        return switch (command) {
            case PARK_CAR -> new ParkCarCommand(parameterLineSplit);
            case FIND -> new FindCarCommand(parameterLineSplit);
            case UN_PARK_CAR -> new UnParkCarCommand(parameterLineSplit);
            case LIST -> new ListCarCommand(parameterLineSplit);
            default -> throw new InvalidParameterException("Command " + command + " is not valid");
        };
    }
}