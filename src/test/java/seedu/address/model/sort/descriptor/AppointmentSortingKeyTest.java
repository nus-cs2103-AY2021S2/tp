package seedu.address.model.sort.descriptor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AppointmentSortingKeyTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AppointmentSortingKey(null));
    }

    @Test
    public void constructor_invalidAppointmentSortingKey_throwsIllegalArgumentException() {
        String invalidAppointmentSortingKey = "";
        assertThrows(IllegalArgumentException.class, () -> new AppointmentSortingKey(invalidAppointmentSortingKey));
    }

    @Test
    public void isValidAppointmentSortingKey() {
        // null appointmentSortingKey
        assertThrows(NullPointerException.class, () -> AppointmentSortingKey.isValidAppointmentSortingKey(null));

        // invalid appointmentSortingKey
        assertFalse(AppointmentSortingKey.isValidAppointmentSortingKey("")); // empty string
        assertFalse(AppointmentSortingKey.isValidAppointmentSortingKey(" ")); // spaces only

        // valid appointmentSortingKey
        assertTrue(AppointmentSortingKey.isValidAppointmentSortingKey("datetime"));
        assertTrue(AppointmentSortingKey.isValidAppointmentSortingKey("name"));
    }
}
