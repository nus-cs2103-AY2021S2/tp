package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Deadline(null));
    }

    @Test
    public void testStringConversion() {
        assertEquals("Jan 24 2021",
                new Deadline(LocalDate.parse("2021-01-24")).toString());
        assertEquals("Feb 29 2000",
                new Deadline(LocalDate.parse("2000-02-29")).toString());
    }
}
