package application;

import java.util.ArrayList;
import java.util.List;

public class BasicRecordGenerator implements SlotRecordGenerator {

    private final int size;

    public BasicRecordGenerator(int numberOfSlots) {
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
