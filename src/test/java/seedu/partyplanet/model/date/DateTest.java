package seedu.partyplanet.model.date;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DateTest {

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
    }
}
