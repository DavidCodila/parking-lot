package application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
        CarObserver carObserver = mock(CarObserver.class);
        doNothing().when(carObserver).unParkCar();

        this.car.setCarObserver(carObserver);
        this.car.unPark();

        verify(carObserver, times(1)).unParkCar();
    }
}
