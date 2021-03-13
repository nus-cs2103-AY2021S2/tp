package seedu.module.model.task;

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
        assertThrows(IllegalArgumentException.class, () -> new Workload(4));
        assertThrows(IllegalArgumentException.class, () -> new Workload(invalidWorkload));
    }

    @Test
    public void isValidWorkloadTest() {
        // null name
        assertThrows(NullPointerException.class, () -> Workload.isValidWorkload(null));

        // invalid name
        assertFalse(Workload.isValidWorkload("")); // empty string
        assertFalse(Workload.isValidWorkload(" ")); // spaces only
        assertFalse(Workload.isValidWorkload("^")); // only non-alphanumeric characters
        assertFalse(Workload.isValidWorkload("peter*")); // contains non-alphanumeric characters
        assertFalse(Workload.isValidWorkload(0)); // contains 0
        assertFalse(Workload.isValidWorkload(-1)); // contains negative
        assertFalse(Workload.isValidWorkload(4)); // beyond 3

        // valid name
        assertTrue(Workload.isValidWorkload(1)); // 1
        assertTrue(Workload.isValidWorkload(2)); // 2
        assertTrue(Workload.isValidWorkload(3)); // 3
    }
}
