import application.BasicSlotListGenerator;
import application.ParkingLot;
import application.SlotRecord;
import command.Command;
import command.CommandGenerator;
import command.CommandRemote;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        final int MAX_CAPACITY = 5;
        final String PATH_TO_COMMAND_FILE = "./src/main/resources/commands";
        final String LINE_DELIMITER = "\r\n";

        FileReader fileReader = new FileReader(PATH_TO_COMMAND_FILE);
        String[] commandLines = fileReader.getLines(LINE_DELIMITER);

        ParkingLot parkingLot = new ParkingLot(
                MAX_CAPACITY,
                new SlotRecord(new BasicSlotListGenerator(MAX_CAPACITY))
        );

        CommandGenerator commandGenerator = new CommandGenerator();
        CommandRemote commandRemote = new CommandRemote(parkingLot);

        List<Command> commands = commandGenerator.generateCommands(commandLines);
        for (Command command: commands) {
            commandRemote.executeCommand(command);
        }
    }
}