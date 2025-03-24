package command.interfaces;

import java.security.InvalidParameterException;
import java.util.List;

public interface SingleParameterCommandInterface extends CommandInterface {
    default Integer extractValidSingleParameter(List<String> parameterLineSplit) throws RuntimeException {
        List<Integer> parameterList = this.extractValidParameterList(parameterLineSplit);
        if (parameterList.size() > 1) {
            throw new InvalidParameterException("Can not make Command from: " + parameterLineSplit);
        }
        return parameterList.getFirst();
    }
}
