package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date(null));
    }

    @Test
    public void testStringConversion() {
        assertEquals("Jan 24, 2021",
                new Date(LocalDate.parse("2021-01-24")).toString());
        assertEquals("Feb 29, 2000",
                new Date(LocalDate.parse("2000-02-29")).toString());
    }
}
