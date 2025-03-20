package application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SlotHandlerTest {
    private static final int MAX_CAPACITY = 5;
    private static final int ZER0TH_INDEX = 0;
    private static final int FIRST_INDEX = 1;

    private SlotHandler slotHandler;
    @Mock private BasicSlotRecordGenerator basicSlotRecordGenerator;

    @BeforeEach
    public void setUp() {
        this.basicSlotRecordGenerator = mock(BasicSlotRecordGenerator.class);
        List<SlotRecord> slotList = new ArrayList<>(MAX_CAPACITY);
        for (int i = 0; i < MAX_CAPACITY; i++) {
            slotList.add(new SlotRecord(new Slot(i), i));
        }
        when(this.basicSlotRecordGenerator.generate()).thenReturn(slotList);
        this.slotHandler = new SlotHandler(this.basicSlotRecordGenerator);
    }

    @Test
    public void testGetNextVacantSlot() {
        Slot expected = this.slotHandler.getVacantSlotAtIndex(ZER0TH_INDEX);
        assertEquals(this.slotHandler.getNextVacantSlot(), expected);
    }

    @Test
    public void testGetSecondVacantSlot() {
        Slot expected = this.slotHandler.getVacantSlotAtIndex(FIRST_INDEX);
        this.slotHandler.getNextVacantSlot();
        assertEquals(this.slotHandler.getNextVacantSlot(), expected);
    }

    @Test
    public void testParkTooManyCars() {
        for (int i = ZER0TH_INDEX; i < MAX_CAPACITY; i++) {
            this.slotHandler.setSlotAsOccupied(i);
        }
        var exception = assertThrows(RuntimeException.class,
                () -> this.slotHandler.getNextVacantSlot());
        assertEquals("Can not park car, the vacantSlotRegister is empty", exception.getMessage());
    }

    @Test
    public void testWillParkInClosestSlot() {
        final int SECOND_INDEX = 2;
        Slot expected = this.slotHandler.getVacantSlotAtIndex(SECOND_INDEX);
        this.slotHandler.setSlotAsOccupied(ZER0TH_INDEX);
        this.slotHandler.setSlotAsOccupied(FIRST_INDEX);
        assertEquals(this.slotHandler.getNextVacantSlot(), expected);
    }

    @Test
    public void testUnParkingACarCreatesAVacantSlot() {
        this.slotHandler.getNextVacantSlot();
        Slot slot = this.slotHandler.getOccupiedSlotAtIndex(ZER0TH_INDEX);
        this.slotHandler.setOnUnParkCar(slot);
        assertNull(this.slotHandler.getOccupiedSlotAtIndex(ZER0TH_INDEX));
    }

    @Test
    public void testExceptionThrownIncorrectCarUnParking() {
        Slot slot = mock(Slot.class);
        var exception = assertThrows(RuntimeException.class,
                () -> this.slotHandler.setOnUnParkCar(slot));
        assertEquals("Error with onUnParkCar method call", exception.getMessage());
    }
}
