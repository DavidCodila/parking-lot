import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SlotTest {
    private Slot slot0;
    private Slot slot1;
    private Car car;

    @BeforeEach
    public void setUp() {
        this.slot0 = new Slot(1, 1);
        this.slot1 = new Slot(2, 2);
        this.slot0.setNextSlot(this.slot1);
        this.car = new Car(0);
    }

    @Test
    public void testParkCar() {
        this.slot0.parkCar(this.car);
        assertEquals(this.car, this.slot0.getCar());
    }

    @Test
    public void testSendsCarToNextSlot() {
        this.slot0.parkCar(this.car);
        Car car1 = new Car(1);
        this.slot0.parkCar(car1);
        assertEquals(car1, this.slot1.getCar());
    }

    @Test
    public void testCompareSlot0ToSlot1WithSlot0DistanceCloser() {
        assertEquals(1, this.slot0.compareTo(new Slot(2, 2)));
    }

    @Test
    public void testCompareSlot0ToSlot1WithDistanceEqual() {
        assertEquals(0, this.slot0.compareTo(new Slot(2, 1)));
    }

    @Test
    public void testCompareSlot0ToSlot1WithSlot0DistanceFurther() {
        assertEquals(-1, this.slot0.compareTo(new Slot(2, 0)));
    }
}
