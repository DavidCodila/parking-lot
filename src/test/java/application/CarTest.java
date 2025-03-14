package application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CarTest {
    private Car car;
    private final int carId = 1;
    private final int slotId = 1;

    @BeforeEach
    public void setUp() {
        this.car = new Car(this.carId);
    }

    @Test
    public void testParkInSlot() throws Exception {
        String methodOutput = tapSystemOut(() -> this.car.parkInSlot(this.slotId)).trim();
        assertEquals("SLOT " + this.slotId + " is allocated to " + this.carId, methodOutput);
    }

    @Test
    public void testPrintInformation() throws Exception {
        this.car.parkInSlot(this.slotId);
        String methodOutput = tapSystemOut(() -> this.car.printInformation()).trim();
        assertEquals(this.carId + " is parked at Slot number " + this.slotId, methodOutput);
    }

    @Test
    public void testUnPark() {
        Observer observer = mock(Observer.class);
        doNothing().when(observer).unParkCar();

        this.car.setObserver(observer);
        this.car.unPark();

        verify(observer, times(1)).unParkCar();
    }
}
