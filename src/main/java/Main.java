import application.BasicRecordGenerator;
import application.ParkingLot;
import application.SlotHandler;
import command.Command;
import command.CommandRemote;
import file.File;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        final String PATH_TO_COMMAND_FILE = "./src/main/resources/commands";
        final String LINE_DELIMITER = "\r\n";

        File file = new File(PATH_TO_COMMAND_FILE);
        String[] commandLines = file.getLines(LINE_DELIMITER);

        final int MAX_CAPACITY = 5;
        ParkingLot parkingLot = new ParkingLot(
                MAX_CAPACITY,
                new SlotHandler(new BasicRecordGenerator(MAX_CAPACITY))
        );

        CommandRemote commandRemote = new CommandRemote(parkingLot);

        for (String commandLine: commandLines) {
            Command command = new Command(commandLine);
            commandRemote.execute(command);
        }
    }
}