import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SlotTest {
    @Test
    public void testParkUnParkCar() {
        Slot slot = new Slot(1,1);
        slot.parkCar();
        assertTrue(slot.isTaken());
    }
}
