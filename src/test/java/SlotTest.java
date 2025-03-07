import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SlotTest {
    private Slot slot1;
    private Slot slot2;
    private Car car1;

    @BeforeEach
    public void setUp() {
        this.slot1 = new Slot(1, 1);
        this.slot2 = new Slot(2, 2);
        this.slot1.setNextSlot(this.slot2);
        this.car1 = new Car(1);
    }

    @Test
    public void testParkCar() {
        this.slot1.parkCar(this.car1);
        assertEquals(this.car1, this.slot1.getCar());
    }

    @Test
    public void testSendsCarToNextSlot() {
        this.slot1.parkCar(this.car1);
        Car car2 = new Car(2);
        this.slot1.parkCar(car2);
        assertEquals(car2, this.slot2.getCar());
    }
}
