package seedu.storemando.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_EXPIRYDATE_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_EXPIRYDATE_CHEESE;
import static seedu.storemando.model.expirydate.ExpiryDate.NO_EXPIRY_DATE;
import static seedu.storemando.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.storemando.model.expirydate.ExpiryDate;

public class ExpiryDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ExpiryDate(null));
    }

    @Test
    public void constructor_invalidExpiryDate_throwsIllegalArgumentException() {
        String invalidExpiryDate = "";
        assertThrows(IllegalArgumentException.class, () -> new ExpiryDate(invalidExpiryDate));
    }

    @Test
    public void isValidExpiryDate() {
        // null expirydate
        assertThrows(NullPointerException.class, () -> ExpiryDate.isValidExpiryDate(null));

        // blank expirydate
        assertFalse(ExpiryDate.isValidExpiryDate("")); // empty string
        assertFalse(ExpiryDate.isValidExpiryDate(" ")); // spaces only

        // missing parts
        assertFalse(ExpiryDate.isValidExpiryDate("2020-10")); // missing DD
        assertFalse(ExpiryDate.isValidExpiryDate("20201010")); // missing '-' symbol
        assertFalse(ExpiryDate.isValidExpiryDate("10-10")); // missing YYYY
        assertFalse(ExpiryDate.isValidExpiryDate("2020-1-10")); // missing digit for MM
        assertFalse(ExpiryDate.isValidExpiryDate("20-11-01")); // missing digit for YYYY
        assertFalse(ExpiryDate.isValidExpiryDate("2020-11-1")); // missing digit for DD

        // invalid parts
        assertFalse(ExpiryDate.isValidExpiryDate("2010-10-40")); // invalid DD greater than 30/31
        assertFalse(ExpiryDate.isValidExpiryDate("2010-10-00")); // invalid DD lesser than 01
        assertFalse(ExpiryDate.isValidExpiryDate("2010-20-01")); // invalid MM greater than 12
        assertFalse(ExpiryDate.isValidExpiryDate("2010-00-01")); // invalid MM lesser than 01
        assertFalse(ExpiryDate.isValidExpiryDate(".2020-01-01")); // invalid YYYY with negative symbol
        assertFalse(ExpiryDate.isValidExpiryDate("+2020-01-01")); // invalid YYYY with positive symbol
        assertFalse(ExpiryDate.isValidExpiryDate("2020-1 0-11")); // spaces in DD
        assertFalse(ExpiryDate.isValidExpiryDate("20 20-10-11")); // spaces in YYYY
        assertFalse(ExpiryDate.isValidExpiryDate(" 2020-10-10")); // leading space
        assertFalse(ExpiryDate.isValidExpiryDate("2021-10-10 ")); // trailing space
        assertFalse(ExpiryDate.isValidExpiryDate("20a1-10-10")); // alphabets in YYYY
        assertFalse(ExpiryDate.isValidExpiryDate("2020-02-30")); // Invalid expiryDate for february
        assertFalse(ExpiryDate.isValidExpiryDate("20-20-10-10")); // '-' symbol in YYYY
        assertFalse(ExpiryDate.isValidExpiryDate("10-02-2019")); // expiryDate not in YYYY-MM-DD format
        assertFalse(ExpiryDate.isValidExpiryDate("@2010-07-10")); // expiryDate starts with symbol


        // invalid expiryDate DD below boundary and above boundary
        assertFalse(ExpiryDate.isValidExpiryDate("2022-08-32"));
        assertFalse(ExpiryDate.isValidExpiryDate("2022-09-31"));
        assertFalse(ExpiryDate.isValidExpiryDate("2022-10-32"));
        assertFalse(ExpiryDate.isValidExpiryDate("2022-11-31"));
        assertFalse(ExpiryDate.isValidExpiryDate("2022-12-32"));
        assertFalse(ExpiryDate.isValidExpiryDate("0000-01-00"));

        // invalid expiryDate MM below boundary and above boundary
        assertFalse(ExpiryDate.isValidExpiryDate("2020-00-01"));
        assertFalse(ExpiryDate.isValidExpiryDate("2020-13-01"));

        // valid expiryDate of months with last dates
        assertTrue(ExpiryDate.isValidExpiryDate("0000-01-31"));
        assertTrue(ExpiryDate.isValidExpiryDate("2022-02-28"));
    }

    @Test
    public void isExpiryDatePastCurrentDate() {
        // no expiryDate
        assertFalse(new ExpiryDate("No Expiry Date").isPastCurrentDate());

        // expiryDates not past current date returns false
        assertFalse(new ExpiryDate("2021-12-02").isPastCurrentDate());
        assertFalse(new ExpiryDate("2023-10-10").isPastCurrentDate());
        assertFalse(new ExpiryDate("2021-11-21").isPastCurrentDate());
        assertFalse(new ExpiryDate("2021-10-09").isPastCurrentDate());
        assertFalse(new ExpiryDate("2022-11-01").isPastCurrentDate());

        // expiryDates same as current date returns false
        assertFalse(new ExpiryDate(LocalDate.now().toString()).isPastCurrentDate());

        // expiryDates past current date returns true
        assertTrue(new ExpiryDate("2012-01-01").isPastCurrentDate());
        assertTrue(new ExpiryDate("2017-11-20").isPastCurrentDate());
        assertTrue(new ExpiryDate("2021-03-11").isPastCurrentDate());
    }

    @Test
    public void compare_expiryDates_success() {
        assertEquals(-1, new ExpiryDate("2021-03-05").compare(new ExpiryDate(NO_EXPIRY_DATE)));
        assertEquals(0, new ExpiryDate(NO_EXPIRY_DATE).compare(new ExpiryDate(NO_EXPIRY_DATE)));
        assertEquals(1, new ExpiryDate(NO_EXPIRY_DATE).compare(new ExpiryDate("2100-07-30")));
        assertTrue(new ExpiryDate(VALID_EXPIRYDATE_BANANA).compare(new ExpiryDate(VALID_EXPIRYDATE_BANANA)) == 0);
        assertTrue(new ExpiryDate(VALID_EXPIRYDATE_CHEESE).compare(new ExpiryDate(VALID_EXPIRYDATE_BANANA)) > 0);
        assertTrue(new ExpiryDate(VALID_EXPIRYDATE_BANANA).compare(new ExpiryDate(VALID_EXPIRYDATE_CHEESE)) < 0);
    }


    @Test
    public void equals() {
        // same expiry date object
        ExpiryDate expiryDate = new ExpiryDate("2021-10-10");
        assertTrue(expiryDate.equals(expiryDate));

        // different expiry date objects with same date
        ExpiryDate anotherExpiryDate = new ExpiryDate("2021-10-10");
        assertTrue(expiryDate.equals(anotherExpiryDate));

        // different expiry date objects with different date
        anotherExpiryDate = new ExpiryDate("2021-10-11");
        assertFalse(expiryDate.equals(anotherExpiryDate));

        // different expiry date objects with different month
        anotherExpiryDate = new ExpiryDate("2021-11-10");
        assertFalse(expiryDate.equals(anotherExpiryDate));

        // different expiry date objects with different year
        anotherExpiryDate = new ExpiryDate("2022-10-10");
        assertFalse(expiryDate.equals(anotherExpiryDate));

    }
}
