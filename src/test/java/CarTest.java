import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        String methodOutput = tapSystemOut(() -> this.car.parkInSlot(this.slotId));
        assertEquals("SLOT " + this.slotId + " is allocated to " + this.carId, methodOutput.trim());
    }

    @Test
    public void testPrintCarIdSlotId() {
        this.car.parkInSlot(this.slotId);
        assertEquals(this.carId + " is parked at Slot number " + this.slotId,
                this.car.printCarIdSlotId(this.carId)
        );
    }

    @Test
    public void testPrintCarIdSlotIdIfIncorrectIdIsPassed() {
        this.car.parkInSlot(this.slotId);
        assertEquals("", this.car.printCarIdSlotId(this.slotId + 1));
    }
}
