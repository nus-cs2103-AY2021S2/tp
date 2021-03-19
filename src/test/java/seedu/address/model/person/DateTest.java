package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDate));
    }

    @Test
    public void isValidDate() {
        // null date
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        //Invalid Input
        assertFalse(Date.isValidDate("2010-10-40"));
        assertFalse(Date.isValidDate("2010-20-01"));
        assertFalse(Date.isValidDate("20 20-11-11"));
        assertFalse(Date.isValidDate("2020-1 0-12"));
        assertFalse(Date.isValidDate(" 2021-10-10"));
        assertFalse(Date.isValidDate("2019-11-10 "));
        assertFalse(Date.isValidDate("20a1-05-10"));
        assertFalse(Date.isValidDate("2020-02-30"));
        assertFalse(Date.isValidDate("20-20-11-10"));
        assertFalse(Date.isValidDate("10-03-2019"));
        assertFalse(Date.isValidDate("@2015-07-10"));

        // valid date
        assertTrue(Date.isValidDate("2020-10-10"));
        assertTrue(Date.isValidDate("2020-11-11"));
        assertTrue(Date.isValidDate("2021-02-11"));
        assertTrue(Date.isValidDate("2021-03-12"));
        assertTrue(Date.isValidDate("2021-03-12"));
    }
}
