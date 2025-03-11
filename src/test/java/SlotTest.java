import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SlotTest {
    private Slot slot;
    private Slot slot2;

    @Mock private Car car;
    @Mock private Car car2;

    private final int slotId = 1;

    @BeforeEach
    public void setUp() {
        int slot2Id = 2;
        this.slot = new Slot(this.slotId, 1);
        this.slot2 = new Slot(slot2Id, 2);
        this.slot.setNextSlot(this.slot2);
        this.slot.parkCar(this.car);
    }

    @Test
    public void testParkCar() {
        assertEquals(this.car, this.slot.getCar());
    }

    @Test
    public void testSendsCarToNextSlot() {
        this.slot.parkCar(this.car2);
        assertEquals(this.car2, this.slot2.getCar());
    }

    @Test
    public void testFinalSlotThrowsParkingLotIsFullException() {
        this.slot.setNextSlot(null);
        var exception = assertThrows(RuntimeException.class,
                () -> this.slot.parkCar(this.car2));
        assertEquals("Can not park car, the parking lot is full", exception.getMessage());
    }
}
