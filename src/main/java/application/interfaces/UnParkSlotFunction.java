package application.interfaces;

import application.Slot;

@FunctionalInterface
public interface UnParkSlotFunction {
    void execute(Slot slot);
}
