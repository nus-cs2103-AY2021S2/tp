package seedu.address.model.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.appointment.AppointmentDateTime;

public class AppointmentDateTimeFilterTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AppointmentDateTimeFilter(null));
    }

    @Test
    public void constructor_validAppointmentDateTimeFilter_success() {
        // Note that full testing of valid filters is done in isValidAppointmentDateTimeFilter test

        // EP 2: Valid Date Time
        assertEquals(new AppointmentDateTime("2021-01-01 10:00 AM"),
                new AppointmentDateTimeFilter("2021-01-01 10:00 AM").appointmentDateTimeFilter);

        // EP 3: Inequalities
        assertEquals(">",
                new AppointmentDateTimeFilter(">2021-01-01 10:00 AM").appointmentDateTimeFilterInequality);
        assertEquals("<",
                new AppointmentDateTimeFilter("<2021-01-01 10:00 AM").appointmentDateTimeFilterInequality);
        assertEquals(">=",
                new AppointmentDateTimeFilter(">=2021-01-01 10:00 AM").appointmentDateTimeFilterInequality);
        assertEquals("<=",
                new AppointmentDateTimeFilter("<=2021-01-01 10:00 AM").appointmentDateTimeFilterInequality);
        assertEquals("=",
                new AppointmentDateTimeFilter("=2021-01-01 10:00 AM").appointmentDateTimeFilterInequality);
    }

    @Test
    public void constructor_invalidAppointmentDateTimeFilter_throwsIllegalArgumentException() {
        // Note that full testing of invalid filters is done in isValidAppointmentDateTimeFilter test
        String invalidAppointmentDateTimeFilter = "";
        assertThrows(IllegalArgumentException.class, () ->
                new AppointmentDateTimeFilter(invalidAppointmentDateTimeFilter));
    }

    @Test
    public void isValidAppointmentDateTimeFilter() {
        // EP 1: Date Time
        assertTrue(AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter("2021-01-01 10:00 AM"));
        assertTrue(AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter("0001-01-01 10:00 AM"));
        assertTrue(AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter("9999-01-01 10:00 AM"));

        // EP 2: Inequalities
        assertTrue(AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter(">2021-01-01 10:00 AM"));
        assertTrue(AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter("<2021-01-01 10:00 AM"));
        assertTrue(AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter(">=2021-01-01 10:00 AM"));
        assertTrue(AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter("<=2021-01-01 10:00 AM"));
        assertTrue(AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter("=2021-01-01 10:00 AM"));

        // EP 3: Lowercase Alphabet
        assertFalse(AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter("a"));
        assertFalse(AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter("m"));
        assertFalse(AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter("z"));
        assertFalse(AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter("0a"));
        assertFalse(AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter("a0"));

        // EP 4: Uppercase Alphabet
        assertFalse(AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter("A"));
        assertFalse(AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter("M"));
        assertFalse(AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter("Z"));
        assertFalse(AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter("0A"));
        assertFalse(AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter("A0"));

        // EP 5: Symbols
        assertFalse(AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter("("));
        assertFalse(AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter(")"));
        assertFalse(AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter("!"));
        assertFalse(AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter("@"));
        assertFalse(AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter("%"));
        assertFalse(AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter("-"));
        assertFalse(AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter("+"));
        assertFalse(AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter(">"));
        assertFalse(AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter("<"));
        assertFalse(AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter(">="));
        assertFalse(AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter("<="));
        assertFalse(AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter("="));
        assertFalse(AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter("2021-01-01 10:00 AM>"));
        assertFalse(AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter("2021-01-01 10:00 AM="));

        // EP 6: Empty
        assertFalse(AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter(""));

        // EP 7: Space
        assertFalse(AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter(" "));
        assertFalse(AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter("2021-01-01 10:00 AM "));
        assertFalse(AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter(" 2021-01-01 10:00 AM"));

        // EP 8: null
        assertThrows(NullPointerException.class, () ->
                AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter(null));
    }

    @Test
    public void test() {
        AppointmentDateTimeFilter appointmentDateTimeFilter;

        // EP 1: Exact match
        appointmentDateTimeFilter = new AppointmentDateTimeFilter("2021-01-01 10:00 AM");
        assertTrue(appointmentDateTimeFilter.test(new AppointmentDateTime("2021-01-01 10:00 AM")));

        appointmentDateTimeFilter = new AppointmentDateTimeFilter("=2021-01-01 10:00 AM");
        assertTrue(appointmentDateTimeFilter.test(new AppointmentDateTime("2021-01-01 10:00 AM")));

        appointmentDateTimeFilter = new AppointmentDateTimeFilter(">=2021-01-01 10:00 AM");
        assertTrue(appointmentDateTimeFilter.test(new AppointmentDateTime("2021-01-01 10:00 AM")));

        appointmentDateTimeFilter = new AppointmentDateTimeFilter("<=2021-01-01 10:00 AM");
        assertTrue(appointmentDateTimeFilter.test(new AppointmentDateTime("2021-01-01 10:00 AM")));

        // EP 2: More Than
        appointmentDateTimeFilter = new AppointmentDateTimeFilter(">2021-01-01 10:00 AM");
        assertTrue(appointmentDateTimeFilter.test(new AppointmentDateTime("2021-01-01 11:00 AM")));
        assertFalse(appointmentDateTimeFilter.test(new AppointmentDateTime("2021-01-01 10:00 AM")));
        assertFalse(appointmentDateTimeFilter.test(new AppointmentDateTime("2021-01-01 09:00 AM")));

        // EP 3: Less Than
        appointmentDateTimeFilter = new AppointmentDateTimeFilter("<2021-01-01 10:00 AM");
        assertTrue(appointmentDateTimeFilter.test(new AppointmentDateTime("2021-01-01 09:00 AM")));
        assertFalse(appointmentDateTimeFilter.test(new AppointmentDateTime("2021-01-01 10:00 AM")));
        assertFalse(appointmentDateTimeFilter.test(new AppointmentDateTime("2021-01-01 11:00 AM")));

        // EP 4: null
        assertFalse(appointmentDateTimeFilter.test(null));
    }
}
