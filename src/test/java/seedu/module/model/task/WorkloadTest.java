package seedu.module.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.module.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class WorkloadTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Workload(null));
    }

    @Test
    public void constructor_invalidWorkload_throwsIllegalArgumentException() {
        String invalidWorkload = "";
        assertThrows(IllegalArgumentException.class, () -> new Workload("4"));
        assertThrows(IllegalArgumentException.class, () -> new Workload(invalidWorkload));
    }

    @Test
    public void isValidWorkloadTest() {
        // null workload
        assertThrows(NullPointerException.class, () -> Workload.isValidWorkload(null));

        // invalid strings
        assertFalse(Workload.isValidWorkload("")); // empty string
        assertFalse(Workload.isValidWorkload(" ")); // spaces only
        assertFalse(Workload.isValidWorkload("1 a")); // contains letters

        //EP: Negative to 0
        assertFalse(Workload.isValidWorkload(String.valueOf(Integer.MIN_VALUE)));
        assertFalse(Workload.isValidWorkload("0"));
        assertFalse(Workload.isValidWorkload("-1"));

        //EP: Beyond 3
        assertFalse(Workload.isValidWorkload("4"));
        assertFalse(Workload.isValidWorkload(String.valueOf(Integer.MAX_VALUE)));

        //EP: Valid workload
        assertTrue(Workload.isValidWorkload("1")); // 1
        assertTrue(Workload.isValidWorkload("2")); // 2
        assertTrue(Workload.isValidWorkload("3")); // 3
    }

    @Test
    public void testDisplayUi() {
        assertEquals(new Workload("1").displayUi(), Workload.LOW_WORKLOAD + " workload");
        assertEquals(new Workload("2").displayUi(), Workload.MEDIUM_WORKLOAD + " workload");
        assertEquals(new Workload("3").displayUi(), Workload.HIGH_WORKLOAD + " workload");
    }

    @Test
    public void equals() {
        Workload firstWorkload = new Workload("1");
        Workload secondWorkload = new Workload("1");
        Workload thirdWorkload = new Workload("2");
        Workload fourthWorkload = new Workload("3");
        String somethingElse = "Not Workload";

        //EP: Same Workload object
        assertTrue(firstWorkload.equals(firstWorkload));

        //EP: Different Workload object, same value
        assertTrue(firstWorkload.equals(secondWorkload));

        //EP: Different Workload object, different values
        assertFalse(firstWorkload.equals(thirdWorkload));
        assertFalse(firstWorkload.equals(fourthWorkload));
        assertFalse(thirdWorkload.equals(fourthWorkload));

        //EP: Not a Workload object
        assertFalse(firstWorkload.equals(somethingElse));
    }

    @Test
    public void compareTo() {
        Workload firstWorkload = new Workload("1");
        Workload secondWorkload = new Workload("1");
        Workload thirdWorkload = new Workload("2");
        Workload fourthWorkload = new Workload("3");

        //EP: Same Workload object
        assertTrue(firstWorkload.compareTo(firstWorkload) == 0);

        //EP: Different Workload object, same value
        assertTrue(firstWorkload.compareTo(secondWorkload) == 0);

        //EP: Different Workload object, different values
        assertTrue(firstWorkload.compareTo(thirdWorkload) > 0);
        assertTrue(firstWorkload.compareTo(fourthWorkload) > 0);

        assertTrue(thirdWorkload.compareTo(firstWorkload) < 0);
        assertTrue(fourthWorkload.compareTo(firstWorkload) < 0);

        assertTrue(thirdWorkload.compareTo(fourthWorkload) > 0);
        assertTrue(fourthWorkload.compareTo(thirdWorkload) < 0);
    }

    @Test
    public void hashCodeTest() {
        Integer firstWorkloadInteger = 1;
        Workload firstWorkload = new Workload("1");
        assertEquals(firstWorkload.hashCode(), firstWorkloadInteger.hashCode());
    }
}
