package application;

import application.interfaces.SlotRecordGenerator;

import java.util.ArrayList;
import java.util.List;

public class BasicSlotRecordGenerator implements SlotRecordGenerator {

    private final int size;

    public BasicSlotRecordGenerator(int numberOfSlots) {
        this.size = numberOfSlots;
    }

    @Override
    public List<SlotRecord> generate() {
        List<SlotRecord> result = new ArrayList<>(this.size);
        for (int i = 0; i < this.size; i++) {
            result.add(new SlotRecord(new Slot(i), (int)(Math.random() * 1000)));
        }
        return result;
    }
}
