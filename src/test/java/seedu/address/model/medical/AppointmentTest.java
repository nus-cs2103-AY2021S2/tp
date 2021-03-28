package seedu.address.model.medical;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class AppointmentTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Appointment(null));
        assertThrows(NullPointerException.class, () -> new Appointment(null));
    }

}
