import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SlotTest {
    private Slot slot;
    private static final int SLOT_NUMBER = 1;

    @Mock private Slot slot2;
    @Mock private Car car;
    @Mock private Car car2;

    @BeforeEach
    public void setUp() {
        this.slot = new Slot(SLOT_NUMBER, 1);
        this.slot.setNextSlot(this.slot2);
        this.slot.parkCar(this.car);
    }

    @Test
    public void testParkCar() {
        assertEquals(this.car, this.slot.getCar());
    }

    @Test
    public void testSendsCarToNextSlot() {
        doNothing().when(this.slot2).parkCar(any(Car.class));
        this.slot.parkCar(this.car2);
        verify(this.slot2, times(1)).parkCar(any(Car.class));
    }

    @Test
    public void testFinalSlotThrowsParkingLotIsFullException() {
        this.slot.setNextSlot(null);
        var exception = assertThrows(RuntimeException.class,
                () -> this.slot.parkCar(this.car2));
        assertEquals("Can not park car, the parking lot is full", exception.getMessage());
    }

    @Test
    public void testUnParkCar() throws Exception {
        String methodOutput = tapSystemOut(() -> this.slot.unParkCar(this.car)).trim();
        assertEquals("Slot " + SLOT_NUMBER + " is free", methodOutput);
        assertNull(this.slot.getCar());
    }

    @Test
    public void testSendsUnParkCommandToNextSlot() {
        doNothing().when(this.slot2).unParkCar(any(Car.class));
        this.slot.unParkCar(this.car2);
        verify(this.slot2, times(1)).unParkCar(any(Car.class));
    }

    @Test
    public void testUnParkCarWithACarThatDoesNotHaveASlot() {
        this.slot.unParkCar(this.car);
        this.slot.setNextSlot(null);
        var exception = assertThrows(RuntimeException.class,
                () -> this.slot.unParkCar(this.car));
        assertEquals("That car does not has a slot", exception.getMessage());
    }
}
