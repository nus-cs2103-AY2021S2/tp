package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class DateTimeTest {

    @Test
    void isNotBlank() {
        //not blank dateTime
        assertTrue(DateTime.isNotBlank("22/02/2021 11:00"));

        //blank dateTime
        assertFalse(DateTime.isNotBlank(""));
    }

    @Test
    void isValidFormat() {
        //valid dateTime format
        assertTrue(DateTime.isValidFormat("22/02/2021 11:00"));

        //valid dateTime format, but invalid date or time, will be caught by isValidDateTime
        assertTrue(DateTime.isValidFormat("29/02/2021 11:00"));
        assertTrue(DateTime.isValidFormat("15/13/2021 11:00"));
        assertTrue(DateTime.isValidFormat("19/12/2021 32:00"));

        //invalid day
        assertFalse(DateTime.isValidFormat("2/02/2021 11:00"));

        //invalid month
        assertFalse(DateTime.isValidFormat("22/2/2021 11:00"));

        //invalid year
        assertFalse(DateTime.isValidFormat("22/02/21 11:00"));

        //invalid time
        assertFalse(DateTime.isValidFormat("22/02/2021 1:00"));
        assertFalse(DateTime.isValidFormat("22/02/2021 11"));

        //invalid separator
        assertFalse(DateTime.isValidFormat("22-02-2021 11:00"));
        assertFalse(DateTime.isValidFormat("22/02/2021 11/00"));
        assertFalse(DateTime.isValidFormat("22-02-2021 11-00"));

        //strange characters
        assertFalse(DateTime.isValidFormat("apple 22/02"));
        assertFalse(DateTime.isValidFormat("apple"));
        assertFalse(DateTime.isValidFormat("-22/01/2021 11:00"));

        //other invalid dateTime format
        assertFalse(DateTime.isValidFormat("22"));
        assertFalse(DateTime.isValidFormat("22/03"));
        assertFalse(DateTime.isValidFormat("22/02/2021"));
        assertFalse(DateTime.isValidFormat("22/02/2021 11"));
        assertFalse(DateTime.isValidFormat("22/02/2021 11:"));
        assertFalse(DateTime.isValidFormat("22/02/2021 11:0"));
    }

    @Test
    void isValidDateTime() {
        //valid DateTime
        assertTrue(DateTime.isValidDateTime("22/02/2021 11:00"));
        assertTrue(DateTime.isValidDateTime("22/02/2021 00:00"));
        assertTrue(DateTime.isValidDateTime("31/03/2021 11:00"));

        //valid leap year
        assertTrue(DateTime.isValidDateTime("29/02/2020 01:00"));

        //invalid time, bound is 00:00 - 23:59
        assertFalse(DateTime.isValidDateTime("22/02/2021 24:00"));
        assertFalse(DateTime.isValidDateTime("19/12/2021 32:00"));

        //invalid leap year
        assertFalse(DateTime.isValidDateTime("29/02/2021 11:00"));

        //invalid month
        assertFalse(DateTime.isValidDateTime("15/13/2021 11:00"));

        //invalid DateTime
        assertFalse(DateTime.isValidDateTime("31/11/2021 11:00"));
        assertFalse(DateTime.isValidDateTime("30/02/2021 12:00"));
        assertFalse(DateTime.isValidDateTime("32/03/2021 11:00"));
    }

    @Test
    void isFutureDateTime() {
        //valid datetime in the future
        assertTrue(DateTime.isFutureDateTime(LocalDateTime.now().plusDays(5).format(DateTime.DATE_TIME_FORMATTER)));

        //invalid datetime in the past
        assertFalse(DateTime.isFutureDateTime("22/02/2021 11:00"));
    }
}
