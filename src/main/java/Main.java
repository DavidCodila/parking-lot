import application.BasicSlotListGenerator;
import application.ParkingLot;
import application.SlotRecord;
import command.Command;
import command.CommandGenerator;
import command.CommandRemote;
import file.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        final String PATH_TO_COMMAND_FILE = "./src/main/resources/commands";
        final String LINE_DELIMITER = "\r\n";

        File file = new File(PATH_TO_COMMAND_FILE);
        String[] commandLines = file.getLines(LINE_DELIMITER);

        final int MAX_CAPACITY = 5;
        ParkingLot parkingLot = new ParkingLot(
                MAX_CAPACITY,
                new SlotRecord(new BasicSlotListGenerator(MAX_CAPACITY))
        );

        CommandRemote commandRemote = new CommandRemote(parkingLot);
        CommandGenerator commandGenerator = new CommandGenerator();

        List<Command> commands = new ArrayList<>();
        for (String commandLine: commandLines) {
            commands.add(commandGenerator.generateCommand(commandLine));
        }

        for (Command command: commands) {
            commandRemote.executeCommand(command);
        }
    }
}