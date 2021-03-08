package seedu.address.model.session;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SessionDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        String dateValue = "2021-01-01";
        String timeValue = "23:59";
        assertThrows(NullPointerException.class, () -> new SessionDate(null, null));
        assertThrows(NullPointerException.class, () -> new SessionDate(dateValue, null));
        assertThrows(NullPointerException.class, () -> new SessionDate(null, timeValue));
    }
}
