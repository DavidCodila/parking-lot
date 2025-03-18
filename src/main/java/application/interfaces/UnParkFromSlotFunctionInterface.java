package application.interfaces;

import application.Slot;

@FunctionalInterface
public interface UnParkFromSlotFunctionInterface {
    public void execute(Slot slot);
}
