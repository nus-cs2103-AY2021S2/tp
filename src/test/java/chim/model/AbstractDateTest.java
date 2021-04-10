package chim.model;

import static chim.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class AbstractDateTest {

    public static class ImplementedAbstractDate extends AbstractDate {
        public ImplementedAbstractDate(String date) {
            super(date);
        }
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ImplementedAbstractDate(null));
    }

    @Test
    public void constructor_invalidExpiryDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new ImplementedAbstractDate(invalidDate));
    }

    @Test
    public void parseDate() {
        // Valid Dates

        // [d/M/yyyy HHmm] format
        LocalDateTime validDate = LocalDateTime.of(2020, 02, 06, 8, 1, 0);
        assertEquals(AbstractDate.parseDate("06/2/2020 08:01"), validDate);
        assertEquals(AbstractDate.parseDate("6/02/2020 08:01"), validDate);

        // [d/M/yyyy] format
        validDate = LocalDateTime.of(2020, 07, 01, 0, 0, 0);
        assertEquals(AbstractDate.parseDate("1/07/2020"), validDate);
        assertEquals(AbstractDate.parseDate("01/7/2020"), validDate);

        // [yyyy-M-d] format
        validDate = LocalDateTime.of(2020, 02, 10, 0, 0, 0);
        assertEquals(AbstractDate.parseDate("2020-02-10"), validDate);
        assertEquals(AbstractDate.parseDate("2020-2-10"), validDate);

        // [yyyy-M-d HH:mm]
        validDate = LocalDateTime.of(2020, 02, 10, 8, 1, 0);
        assertEquals(AbstractDate.parseDate("2020-02-10 08:01"), validDate);
        assertEquals(AbstractDate.parseDate("2020-2-10 08:01"), validDate);

        // [MMM d yyyy HH:mm] format
        validDate = LocalDateTime.of(2020, 03, 8, 9, 10, 0);
        assertEquals(AbstractDate.parseDate("Mar 8 2020 09:10"), validDate);
        assertEquals(AbstractDate.parseDate("Mar 08 2020 09:10"), validDate);

        // [MMM d yyyy] format
        validDate = LocalDateTime.of(2020, 06, 8, 0, 0, 0);
        assertEquals(AbstractDate.parseDate("Jun 8 2020"), validDate);
        assertEquals(AbstractDate.parseDate("Jun 08 2020"), validDate);


        // Invalid Dates
        // Missing information

        // Missing Day
        // [d/M/yyyy HHmm] format
        assertEquals(AbstractDate.parseDate("/23/2020 08:01"), null);
        // [d/M/yyyy] format
        assertEquals(AbstractDate.parseDate("/-10/2020"), null);
        // [yyyy-M-d HH:mm] format
        assertEquals(AbstractDate.parseDate("2020-2- 08:01"), null);
        // [yyyy-m-d] format
        assertEquals(AbstractDate.parseDate("2020-12-"), null);
        // [MMM d yyyy HH:mm] format
        assertEquals(AbstractDate.parseDate("Feb  2020 09:10"), null);
        // [MMM d yyyy] format
        assertEquals(AbstractDate.parseDate("Feb  2020"), null);

        // Missing Month
        // [d/M/yyyy HHmm] format
        assertEquals(AbstractDate.parseDate("10//2020 08:01"), null);
        // [d/M/yyyy] format
        assertEquals(AbstractDate.parseDate("10//2020"), null);
        // [yyyy-M-d HH:mm] format
        assertEquals(AbstractDate.parseDate("2020-10 08:01"), null);
        // [yyyy-m-d] format
        assertEquals(AbstractDate.parseDate("2020-10"), null);
        // [MMM d yyyy HH:mm] format
        assertEquals(AbstractDate.parseDate(" 2 2020 09:10"), null);
        // [MMM d yyyy] format
        assertEquals(AbstractDate.parseDate(" 10 2020"), null);

        // Missing Year
        // [d/M/yyyy HHmm] format
        assertEquals(AbstractDate.parseDate("3/12/ 08:01"), null);
        // [d/M/yyyy] format
        assertEquals(AbstractDate.parseDate("13/10/"), null);
        // [yyyy-M-d HH:mm] format
        assertEquals(AbstractDate.parseDate("-2-23 08:01"), null);
        // [yyyy-m-d] format
        assertEquals(AbstractDate.parseDate("12-40"), null);
        // [MMM d yyyy HH:mm] format
        assertEquals(AbstractDate.parseDate("Mar 2  09:10"), null);
        // [MMM d yyyy] format
        assertEquals(AbstractDate.parseDate("Feb 10 "), null);

        // Missing Hour
        // [d/M/yyyy HHmm] format
        assertEquals(AbstractDate.parseDate("6/02/2020 :01"), null);

        // [yyyy-M-d HH:mm]
        assertEquals(AbstractDate.parseDate("2020-2-10 01"), null);

        // [MMM d yyyy HH:mm] format
        assertEquals(AbstractDate.parseDate("Mar 08 2020 :10"), null);

        // Missing Minutes
        // [d/M/yyyy HHmm] format
        assertEquals(AbstractDate.parseDate("6/02/2020 08:"), null);

        // [yyyy-M-d HH:mm]
        assertEquals(AbstractDate.parseDate("2020-2-10 08"), null);

        // [MMM d yyyy HH:mm] format
        assertEquals(AbstractDate.parseDate("Mar 08 2020 09:"), null);

        // Additional information
        assertEquals(AbstractDate.parseDate("2019-05-07 08:01am"), null);
        assertEquals(AbstractDate.parseDate("2019-05-07 08:01pm"), null);
        assertEquals(AbstractDate.parseDate("2019-5-07 08:01EXTRA"), null);
        assertEquals(AbstractDate.parseDate("EXTRA2019-5-07 08:01"), null);
        assertEquals(AbstractDate.parseDate("2019-5-07EXTRA08:01"), null);

        // Invalid day
        // [d/M/yyyy HHmm] format
        assertEquals(AbstractDate.parseDate("-23/23/2020 08:01"), null);
        // [d/M/yyyy] format
        assertEquals(AbstractDate.parseDate("99/-10/2020"), null);
        // [yyyy-M-d HH:mm] format
        assertEquals(AbstractDate.parseDate("2020-2-0 08:01"), null);
        // [yyyy-m-d] format
        assertEquals(AbstractDate.parseDate("2020-12-40"), null);
        // [MMM d yyyy HH:mm] format
        assertEquals(AbstractDate.parseDate("Feb 561 2020 09:10"), null);
        // [MMM d yyyy] format
        assertEquals(AbstractDate.parseDate("Feb -40 2020"), null);


        // Invalid month
        // [d/M/yyyy HHmm] format
        assertEquals(AbstractDate.parseDate("10/23/2020 08:01"), null);
        // [d/M/yyyy] format
        assertEquals(AbstractDate.parseDate("10/-10/2020"), null);
        // [yyyy-M-d HH:mm] format
        assertEquals(AbstractDate.parseDate("2020-13-10 08:01"), null);
        // [yyyy-m-d] format
        assertEquals(AbstractDate.parseDate("2020-0-10"), null);
        // [MMM d yyyy HH:mm] format
        assertEquals(AbstractDate.parseDate("ASD 2 2020 09:10"), null);
        // [MMM d yyyy] format
        assertEquals(AbstractDate.parseDate("Q 10 2020"), null);

        // Invalid year
        // [d/M/yyyy HHmm] format
        assertEquals(AbstractDate.parseDate("3/12/-234 08:01"), null);
        // [d/M/yyyy] format
        assertEquals(AbstractDate.parseDate("13/10/23"), null);
        // [yyyy-M-d HH:mm] format
        assertEquals(AbstractDate.parseDate("0-2-23 08:01"), null);
        // [yyyy-m-d] format
        assertEquals(AbstractDate.parseDate("12341-12-40"), null);
        // [MMM d yyyy HH:mm] format
        assertEquals(AbstractDate.parseDate("Mar 2 -135 09:10"), null);
        // [MMM d yyyy] format
        assertEquals(AbstractDate.parseDate("Feb 10 21"), null);

        // Invalid Hour
        // [d/M/yyyy HHmm] format
        assertEquals(AbstractDate.parseDate("6/02/2020 63:01"), null);

        // [yyyy-M-d HH:mm]
        assertEquals(AbstractDate.parseDate("2020-2-10 -10:01"), null);

        // [MMM d yyyy HH:mm] format
        assertEquals(AbstractDate.parseDate("Mar 08 2020 25:10"), null);

        // Invalid Minutes
        // [d/M/yyyy HHmm] format
        assertEquals(AbstractDate.parseDate("6/02/2020 08:-94"), null);

        // [yyyy-M-d HH:mm]
        assertEquals(AbstractDate.parseDate("2020-2-10 08:-"), null);

        // [MMM d yyyy HH:mm] format
        assertEquals(AbstractDate.parseDate("Mar 08 2020 09:68"), null);

    }
}
