import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SlotTest {
    private Slot slot;
    private Slot slot2;

    @Mock private Car car;
    @Mock private Car car2;

    private final int slotId = 1;
    private final int slot2Id = 2;
    private final int carId = 1;
    private final int car2Id = 2;

    @BeforeEach
    public void setUp() {
        this.slot = new Slot(this.slotId, 1);
        this.slot2 = new Slot(this.slot2Id, 2);
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

    @Test
    public void testAssignSlotNumberToCar() {
        when(this.car.getSlotNumber()).thenReturn(this.slotId);
        assertEquals(this.slotId, this.car.getSlotNumber());
    }

    @Test
    public void testFindCarThatIsPresentInFirstSlot() throws Exception {
        String expectedOutput = this.carId + " is parked at Slot number " + this.slotId;
        when(this.car.printCarIdSlotId(any(int.class)))
                .thenReturn(expectedOutput);
        String methodOutput = tapSystemOut(() -> this.slot.getCar(this.carId));
        assertEquals(expectedOutput, methodOutput.trim());
    }

    @Test
    public void testFindCarThatIsNotPresentInFirstSlot() throws Exception {
        String expectedOutput = this.carId + " is parked at Slot number "+ this.slot2Id;
        this.slot2.parkCar(this.car2);
        when(this.car.printCarIdSlotId(any(int.class)))
                .thenReturn("");
        when(this.car2.printCarIdSlotId(any(int.class)))
                .thenReturn(expectedOutput);
        String methodOutput = tapSystemOut(() -> this.slot.getCar(this.car2Id));
        assertEquals(expectedOutput, methodOutput.trim());
    }
}
