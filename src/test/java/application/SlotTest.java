package application;

import application.interfaces.UnParkCarFunction;
import application.interfaces.UnParkSlotFunction;
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
    }

    @Test
    public void testParkCar() {
        doNothing().when(this.car).parkInSlot(SLOT_NUMBER);
        doNothing().when(this.car).setUnParkCarFunction(any(UnParkCarFunction.class));
        this.slot.parkCar(this.car);
        verify(this.car, times(1)).parkInSlot(SLOT_NUMBER);
        verify(this.car, times(1)).setUnParkCarFunction(any(UnParkCarFunction.class));
    }

    @Test
    public void testUnParkCar() throws Exception {
        this.slot.parkCar(this.car);
        UnParkSlotFunction function = mock(UnParkSlotFunction.class);
        this.slot.setUnParkSlotFunction(function);
        doNothing().when(function).execute(this.slot);

        String methodOutput = tapSystemOut(() -> this.slot.evokeUnParkCar()).trim();

        assertEquals("Slot " + SLOT_NUMBER + " is free", methodOutput);
        verify(function, times(1)).execute(this.slot);
    }

    @Test
    public void testUnParkCarThrowsExceptionWhenThereIsNoCar() {
        var exception = assertThrows(RuntimeException.class,
                () -> this.slot.evokeUnParkCar());
        assertEquals("There is no car in slot: " + SLOT_NUMBER + " to un park." , exception.getMessage());
    }

    @Test
    public void testWillThroughExceptionForParkingMoreThan1Car() {
        this.slot.parkCar(this.car);
        var exception = assertThrows(RuntimeException.class,
                () -> this.slot.parkCar(this.car));
        assertEquals("Can not park car in slot number: " + SLOT_NUMBER, exception.getMessage());
    }
}
