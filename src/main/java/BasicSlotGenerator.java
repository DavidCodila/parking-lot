import java.util.ArrayList;
import java.util.List;

public class BasicSlotGenerator implements SlotGenerator {
    @Override
    public List<Slot> generateSlots(int numberOfSlots) throws RuntimeException {
        if (numberOfSlots < 1) {
            throw new RuntimeException("Can not generate less than 1 slot");
        }
        List<Slot> result = new ArrayList<>(numberOfSlots);
        for (int i = 0; i < numberOfSlots; i++) {
            int distance = (i+1)*2;
            result.add(new Slot(i, distance));
        }
        return result;
    }
}
