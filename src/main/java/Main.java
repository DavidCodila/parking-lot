import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        final int MAX_CAPACITY = 5;
        final String PATH_TO_COMMAND_FILE = "./src/main/resources/commands";
        final String LINE_DELIMITER = "\r\n";

        FileReader fileReader = new FileReader(PATH_TO_COMMAND_FILE, LINE_DELIMITER);
        String[] commandLines = fileReader.getLines();
        CommandConstants.validateCommandLines(commandLines);

        ParkingLot parkingLot = new ParkingLot(
                MAX_CAPACITY,
                new SlotRecord(new BasicSlotListGenerator(MAX_CAPACITY))
        );
        CommandRemote commandRemote = new CommandRemote(parkingLot);

        for (String commandLine: commandLines) {
            String command = commandLine.split(" ")[0];
            int parameter = Integer.parseInt(commandLine.split(" ")[1]);
            Command commandObject = CommandConstants.createCommand(command, parameter);
            commandRemote.executeCommand(commandObject);
        }
    }
}