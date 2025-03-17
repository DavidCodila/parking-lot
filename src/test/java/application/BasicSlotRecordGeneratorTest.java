package application;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasicSlotRecordGeneratorTest {
    private static final int MAX_NUMBER_OF_SLOTS = 5;

    @Test
    public void testGeneratesListOfSlots() {
        BasicSlotRecordGenerator basicSlotListGenerator = new BasicSlotRecordGenerator(MAX_NUMBER_OF_SLOTS);
        List<SlotRecord> result = basicSlotListGenerator.generate();
        assertEquals(MAX_NUMBER_OF_SLOTS, result.size());
    }
}
