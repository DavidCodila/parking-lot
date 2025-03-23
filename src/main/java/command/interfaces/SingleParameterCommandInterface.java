package command.interfaces;

import java.security.InvalidParameterException;
import java.util.List;

public interface SingleParameterCommandInterface extends CommandInterface {
    default void validateIsSingleParameterCommand(List<String> parameterLineSplit) throws RuntimeException {
        if (parameterLineSplit.size() > 1) {
            throw new RuntimeException("Can not make Command from: " + parameterLineSplit);
        }
        String parameter = parameterLineSplit.getFirst();
        if (!this.isInteger(parameter)) {
            throw new InvalidParameterException(parameter + " is not a valid parameter");
        }
    }
}
