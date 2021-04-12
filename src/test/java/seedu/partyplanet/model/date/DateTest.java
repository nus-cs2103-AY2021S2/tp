package seedu.partyplanet.model.date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.partyplanet.model.date.Date.READABLE_FORMAT;
import static seedu.partyplanet.model.date.Date.READABLE_FORMAT_WITHOUT_YEAR;
import static seedu.partyplanet.testutil.Assert.assertThrows;

import java.time.DateTimeException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DateTest {

    private static final String VALID_DATE_ISO = "2020-05-02";
    private static final String VALID_DATE_DMY_DOT = "2.5.2020";
    private static final String VALID_DATE_DMY_SLASH = "2/5/2020";
    private static final String VALID_DATE_DMMY = "2 May 2020";
    private static final String VALID_DATE_MMDY = "May 2 2020";
    private static final String VALID_MONTHDAY_ISO = "--05-02";
    private static final String VALID_MONTHDAY_DMY_DOT = "2.5";
    private static final String VALID_MONTHDAY_DMY_SLASH = "2/5";
    private static final String VALID_MONTHDAY_DMMY = "2 May";
    private static final String VALID_MONTHDAY_MMDY = "May 2";

    @Test
    public void constructor_invalidDate_throwsDateTimeException() {

        // Dates with year
        assertThrows(DateTimeException.class, () -> new Date("2020-05-32")); // day does not exist
        assertThrows(DateTimeException.class, () -> new Date("2020-00-01")); // month does not exist
        assertThrows(DateTimeException.class, () -> new Date("-9999-01-01")); // year must be positive integer
        assertThrows(DateTimeException.class, () -> new Date("0000-01-01")); // year must be positive integer

        // Dates without year
        assertThrows(DateTimeException.class, () -> new Date("32 May")); // wrong date
        assertThrows(DateTimeException.class, () -> new Date("Ma 2")); // month does not exist
        assertThrows(DateTimeException.class, () -> new Date("May/3")); // wrong format
        assertThrows(DateTimeException.class, () -> new Date("-01-01")); // wrong ISO format
    }

    @Test
    public void isValidDate() {
        assertFalse(Date.isValidDate("2020-05-32"));
        assertFalse(Date.isValidDate("-01-01"));
        assertTrue(Date.isValidDate(VALID_DATE_DMMY));
        assertTrue(Date.isValidDate(VALID_MONTHDAY_ISO));
    }

    @Test
    public void constructor_checkValidDateValue() {
        Date dateIsoWithYear = new Date(VALID_DATE_ISO);
        assertEquals(dateIsoWithYear, new Date(dateIsoWithYear.value));

        Date dateIso = new Date(VALID_MONTHDAY_ISO);
        assertEquals(dateIso, new Date(dateIso.value));
    }

    @Test
    public void constructor_equivalentFormats() {
        // Effectively tests parseDate

        Date equivalentDateWithYear = new Date(VALID_DATE_ISO);
        assertEquals(equivalentDateWithYear, new Date(VALID_DATE_DMY_DOT));
        assertEquals(equivalentDateWithYear, new Date(VALID_DATE_DMY_SLASH));
        assertEquals(equivalentDateWithYear, new Date(VALID_DATE_DMMY));
        assertEquals(equivalentDateWithYear, new Date(VALID_DATE_MMDY));

        Date equivalentDate = new Date(VALID_MONTHDAY_ISO);
        assertEquals(equivalentDate, new Date(VALID_MONTHDAY_DMY_DOT));
        assertEquals(equivalentDate, new Date(VALID_MONTHDAY_DMY_SLASH));
        assertEquals(equivalentDate, new Date(VALID_MONTHDAY_DMMY));
        assertEquals(equivalentDate, new Date(VALID_MONTHDAY_MMDY));

        // Test case-insensitivity
        assertEquals(equivalentDateWithYear, new Date(VALID_DATE_DMMY.toLowerCase()));
        assertEquals(equivalentDate, new Date(VALID_MONTHDAY_MMDY.toUpperCase()));
    }

    @Test
    public void getDateWithoutYear() {
        Date equivalentDateWithYear = new Date(VALID_DATE_ISO);
        Date equivalentDateWithoutYear = new Date(VALID_MONTHDAY_ISO);
        assertEquals(equivalentDateWithoutYear, Date.getDateWithoutYear(equivalentDateWithoutYear));
        assertEquals(equivalentDateWithoutYear, Date.getDateWithoutYear(equivalentDateWithYear));

        // Test empty dates
        assertEquals(new Date(), Date.getDateWithoutYear(new Date()));
    }

    @Test
    public void isEmptyDate() {
        assertTrue(Date.isEmptyDate(new Date()));
        assertFalse(Date.isEmptyDate(new Date(VALID_DATE_ISO)));
    }

    @Test
    public void getDaysLeft() {
        Date date = new Date("2020-05-03"); // Fixed specified date

        // Include year
        assertEquals(369, date.getDaysLeft(false, LocalDate.of(2019, 4, 30))); // Past year
        assertEquals(34, date.getDaysLeft(false, LocalDate.of(2020, 3, 30))); // Past, same year
        assertEquals(0, date.getDaysLeft(false, LocalDate.of(2020, 5, 3))); // Same date
        assertEquals(-29, date.getDaysLeft(false, LocalDate.of(2020, 6, 1))); // Future, same year
        assertEquals(-2556, date.getDaysLeft(false, LocalDate.of(2027, 5, 3))); // Future year

        // Exclude year
        assertEquals(3, date.getDaysLeft(true, LocalDate.of(2019, 4, 30))); // Past year
        assertEquals(34, date.getDaysLeft(true, LocalDate.of(2020, 3, 30))); // Past, same year
        assertEquals(0, date.getDaysLeft(true, LocalDate.of(2020, 5, 3))); // Same date
        assertEquals(336, date.getDaysLeft(true, LocalDate.of(2020, 6, 1))); // Future, same year
        assertEquals(0, date.getDaysLeft(true, LocalDate.of(2027, 5, 3))); // Future year

        // Check leap day conditions
        Date leapDate = new Date("2020-03-01");
        assertEquals(181, leapDate.getDaysLeft(true, LocalDate.of(2020, 9, 1))); // Next year no leap day
        assertEquals(182, leapDate.getDaysLeft(true, LocalDate.of(2019, 9, 1))); // Next year has leap day
        assertEquals(29, leapDate.getDaysLeft(true, LocalDate.of(2020, 2, 1))); // Current year has leap day

        // Empty date
        assertEquals(Long.MAX_VALUE, (new Date()).getDaysLeft());
    }

    @Test
    public void getMonth() {
        assertEquals(Date.EMPTY_MONTH, (new Date()).getMonth());
        assertEquals(1, (new Date("2020-01-04")).getMonth());
        assertEquals(11, (new Date("9 November")).getMonth());
    }

    @Test
    public void hasYear() {
        assertFalse((new Date()).hasYear());
        assertTrue((new Date(VALID_DATE_ISO)).hasYear());
        assertFalse((new Date(VALID_MONTHDAY_ISO)).hasYear());
    }


    @Test
    public void compareTo() {
        // Same year
        Date date0606 = new Date("2020-06-06");
        Date dateNoYear0606 = new Date("--06-06");
        Date dateNoYear0607 = new Date("--06-07");
        Date date0608 = new Date("2020-06-08");

        // Different year
        Date datePrevYear = new Date("2019-08-13");

        // Dates with no years are smaller
        assertTrue(date0606.compareTo(dateNoYear0607) > 0);
        assertTrue(dateNoYear0606.compareTo(dateNoYear0607) < 0);
        assertTrue(dateNoYear0606.compareTo(date0608) < 0);

        // Different date
        assertTrue(date0606.compareTo(date0608) < 0);
        assertTrue(date0608.compareTo(datePrevYear) > 0);
        assertTrue(datePrevYear.compareTo(date0606) < 0);

        // Empty date corner case
        Date emptyDate = new Date();
        assertTrue(date0606.compareTo(emptyDate) < 0);
        assertTrue(emptyDate.compareTo(date0606) > 0);
        assertEquals(emptyDate.compareTo(new Date()), 0); // different empty dates
    }

    @Test
    public void toString_validDates() {
        assertEquals((new Date("2020-02-02")).toString(), LocalDate.of(2020, 2, 2).format(READABLE_FORMAT));
        assertEquals((new Date("--02-02")).toString(), LocalDate.of(2020, 2, 2).format(READABLE_FORMAT_WITHOUT_YEAR));
    }
}
