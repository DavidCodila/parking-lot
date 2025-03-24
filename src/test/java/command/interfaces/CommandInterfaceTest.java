package command.interfaces;

import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandInterfaceTest {
    private final CommandInterface commandInterface = parkingLot -> {};

    @Test
    public void testExtractValidParameterListReturnsValidList() {
        final int PARAMETER_0 = 0;
        final int PARAMETER_1 = 1;
        final List<String> VALID_COMMAND_STRING = List.of(String.valueOf(PARAMETER_0), String.valueOf(PARAMETER_1));
        List<Integer> result = this.commandInterface.extractValidParameterList(VALID_COMMAND_STRING);
        assertEquals(List.of(PARAMETER_0, PARAMETER_1), result);
    }

    @Test
    public void testExtractValidParameterListThrowsExceptionForInvalidParameter() {
        final List<String> INVALID_COMMAND_STRING = List.of("Invalid");
        var exception = assertThrows(InvalidParameterException.class, () ->
                this.commandInterface.extractValidParameterList(INVALID_COMMAND_STRING)
        );
        assertEquals( "Parameter " + INVALID_COMMAND_STRING + " is not valid", exception.getMessage());
    }
}
