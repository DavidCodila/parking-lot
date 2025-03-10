import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarTest {
    private Car car1;
    private final int car1Id = 1;
    private final int slot1Id = 1;

    @BeforeEach
    public void setUp() {
        this.car1 = new Car(this.car1Id);
    }

    @Test
    public void testPrintCarIdSlotId() {
        this.car1.parkInSlot(1);
        assertEquals(this.car1Id + " is parked at Slot number " + this.slot1Id,
                this.car1.printCarIdSlotId(this.car1Id)
        );
    }

    @Test
    public void testParkInSlot() throws Exception {
        String methodOutput = tapSystemOut(() -> this.car1.parkInSlot(this.slot1Id));
        assertEquals("SLOT " + this.slot1Id + " is allocated to " + this.car1Id, methodOutput.trim());
    }

    @Test
    public void testPrintCarIdSlotIdIfNotRightId() {
        this.car1.parkInSlot(this.slot1Id);
        assertEquals("", this.car1.printCarIdSlotId(this.slot1Id + 1));
    }
}
