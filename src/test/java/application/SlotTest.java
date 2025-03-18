package application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SlotTest {
    private Slot slot;
    private static final int SLOT_NUMBER = 1;

    @Mock private Car car;

    @BeforeEach
    public void setUp() {
        this.slot = new Slot(SLOT_NUMBER);
        this.slot.parkCar(this.car);
    }

    @Test
    public void testParkCar() {
        assertEquals(this.car, this.slot.getCar());
    }

    @Test
    public void testUnParkCar() throws Exception {

        UnParkFromSlotFunctionInterface function = mock(UnParkFromSlotFunctionInterface.class);
        this.slot.setUnParkFromSlotFunction(function);
        doNothing().when(function).execute(this.slot);

        String methodOutput = tapSystemOut(() -> this.slot.unParkCar()).trim();

        assertEquals("Slot " + SLOT_NUMBER + " is free", methodOutput);
        assertNull(this.slot.getCar());
        verify(function, times(1)).execute(this.slot);
    }

    @Test
    public void testWillThroughExceptionForParkingMoreThan1Car() {
        var exception = assertThrows(RuntimeException.class,
                () -> this.slot.parkCar(this.car));
        assertEquals("Can not park car in slot number: " + SLOT_NUMBER, exception.getMessage());
    }
}
