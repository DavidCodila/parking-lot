import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BasicSlotGeneratorTest {
    private BasicSlotGenerator slotGenerator;

    @BeforeEach
    public void setUp() {
        this.slotGenerator = new BasicSlotGenerator();
    }
    @Test
    public void testOneSlot() {
        List<Slot> expectedResult = List.of(new Slot(0,2));
        assertTrue(areTheSameListOfSlot(this.slotGenerator.generateSlots(1), expectedResult));
    }

    @Test
    public void testManySlots() {
        List<Slot> expectedResult = List.of(new Slot(0,2), new Slot(1, 4));
        assertTrue(areTheSameListOfSlot(this.slotGenerator.generateSlots(2), expectedResult));
    }

    @Test
    public void testWithNoSlots() {
        var exception = assertThrows(RuntimeException.class,
                () -> this.slotGenerator.generateSlots(0));
        assertEquals("Can not generate less than 1 slot", exception.getMessage());

    }

    private boolean areTheSameListOfSlot(List<Slot> slotList1, List<Slot> slotList2) {
        for (int i = 0; i < slotList1.size(); i++) {
            if (!areTheSameSlot(slotList1.get(i), slotList2.get(i))) {
                return false;
            }
        }
        return true;
    }

    private Boolean areTheSameSlot(Slot slot1, Slot slot2) {
        return slot1.getNumber() == slot2.getNumber();
    }
}
