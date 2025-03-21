import application.BasicSlotRecordGenerator;
import application.ParkingLot;
import application.SlotHandler;
import command.CommandInterface;
import command.CommandRemote;
import file.File;
import command.CommandGenerator;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        final String PATH_TO_COMMAND_FILE = "./src/main/resources/commands";
        final int MAX_CAPACITY = 5;
        final String DELIMITER = "\r\n";
        File file = new File(PATH_TO_COMMAND_FILE);
        CommandGenerator commandGenerator = new CommandGenerator();
        List<String> commandLines = file.getLines(DELIMITER);
        List<CommandInterface> commands = commandGenerator.generateCommands(commandLines);

        ParkingLot parkingLot = new ParkingLot(
                MAX_CAPACITY,
                new SlotHandler(new BasicSlotRecordGenerator(MAX_CAPACITY))
        );

        CommandRemote commandRemote = new CommandRemote(parkingLot);
        commandRemote.executeCommands(commands);
    }
}