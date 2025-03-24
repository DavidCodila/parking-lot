package command.interfaces;

import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SingleParameterCommandInterfaceTest {
    private final SingleParameterCommandInterface commandInterface = parkingLot -> {};

    @Test
    public void testExtractValidSingleParameter() {
        final int PARAMETER_0 = 0;
        final List<String> VALID_COMMAND_STRING = List.of(String.valueOf(PARAMETER_0));
        Integer result = this.commandInterface.extractValidSingleParameter(VALID_COMMAND_STRING);
        assertEquals(PARAMETER_0, result);
    }

    @Test
    public void testExtractValidSingleParameterThrowsExceptionFor2Parameters() {
        final List<String> INVALID_COMMAND_STRING = List.of("0", "1");
        var exception = assertThrows(InvalidParameterException.class,
                () -> this.commandInterface.extractValidSingleParameter(INVALID_COMMAND_STRING)
        );
        assertEquals("Can not make Command from: " + INVALID_COMMAND_STRING, exception.getMessage());
    }
}
