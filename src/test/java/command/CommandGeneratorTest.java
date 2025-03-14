package command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandGeneratorTest {
    private CommandGenerator commandGenerator;

    @BeforeEach
    public void setUp() {
        this.commandGenerator = new CommandGenerator();
    }

    @Test
    public void testGenerateParkCommand() {
        String parkCarCommandLine = "PARK_CAR 0";
        //assertSame(new ParkCarCommand(0), this.commandGenerator.generateCommand(parkCarCommandLine));
    }
}
