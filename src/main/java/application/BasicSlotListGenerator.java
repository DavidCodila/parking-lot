package application;

import java.util.ArrayList;
import java.util.List;

public class BasicSlotListGenerator implements SlotListGenerator {

    private final int size;

    public BasicSlotListGenerator(int numberOfSlots) {
        this.size = numberOfSlots;
    }

    @Override
    public List<Slot> generate() {
        List<Slot> result = new ArrayList<>(size);
        for (int i = 0; i < this.size; i++) {
            result.add(new Slot(i, (int)(Math.random() * 1000)));
        }
        return result;
    }
}
