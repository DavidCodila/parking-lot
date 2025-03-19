import application.BasicSlotRecordGenerator;
import application.ParkingLot;
import application.SlotHandler;
import command.CommandGenerator;
import command.CommandInterface;
import command.CommandRemote;
import file.File;
import file.FileProcessor;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        final String PATH_TO_COMMAND_FILE = "./src/main/resources/commands";

        FileProcessor fileProcessor = new FileProcessor(
                new File(PATH_TO_COMMAND_FILE),
                new CommandGenerator()
        );

        List<CommandInterface> commands = fileProcessor.getCommands();

        final int MAX_CAPACITY = 5;
        ParkingLot parkingLot = new ParkingLot(
                MAX_CAPACITY,
                new SlotHandler(new BasicSlotRecordGenerator(MAX_CAPACITY))
        );

        CommandRemote commandRemote = new CommandRemote(parkingLot);
        commandRemote.executeCommands(commands);
    }
}