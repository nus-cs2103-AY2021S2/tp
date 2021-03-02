package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date(null));
    }

    @Test
    public void constructor_invalidExpiryDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDate));
    }

    @Test
    public void parseDate() {
        assertEquals(Date.parseDate("2020-02-10"), LocalDateTime.of(2020, 02, 10, 0, 0, 0));
        assertEquals(Date.parseDate("2020-02-10 08:01"), LocalDateTime.of(2020, 02, 10, 8, 1, 0));
        assertEquals(Date.parseDate("2021-04-18 13:23"), LocalDateTime.of(2021, 04, 18, 13, 23, 0));

        assertEquals(Date.parseDate("2020-13-10 08:01"), null);
        assertEquals(Date.parseDate("2019-05-07 08:01am"), null);
        assertEquals(Date.parseDate("2020-13-10 08:01"), null);
    }
}
