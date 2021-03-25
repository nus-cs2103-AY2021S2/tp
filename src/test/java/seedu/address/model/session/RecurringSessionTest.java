package seedu.address.model.session;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class RecurringSessionTest {
    // TODO: Add RecurringSessionTest and other parse tests.

    @Test
    void getInterval() {
    }

    @Test
    void getEndDateTime() {
    }

    @Test
    void numOfSessionBetween() {
        SessionDate firstSessionDate = new SessionDate("2021-02-28", "10:00");
        SessionDate lastSessionDate = new SessionDate("2021-04-11", "10:00");
        SessionDate firstOfMarch = new SessionDate("2021-03-01", "00:00");
        SessionDate lastOfMarch = new SessionDate("2021-03-31", "23:59");

        int eyeBalledAns1 = 2;
        Interval interval1 = new Interval("14");
        //first and last must match
        RecurringSession s = new RecurringSession(firstSessionDate, new Duration("100"), new Subject("Math"),
                new Fee("40"), interval1, lastSessionDate);
        int ans1 = s.numOfSessionBetween(firstOfMarch, lastOfMarch);
        assertEquals(ans1, eyeBalledAns1);

        int eyeBalledAns2 = 31;
        Interval interval2 = new Interval("1");
        s = new RecurringSession(firstSessionDate, new Duration("100"), new Subject("Math"),
                new Fee("40"), interval2, lastSessionDate);
        int ans2 = s.numOfSessionBetween(firstOfMarch, lastOfMarch);
        assertEquals(ans2, eyeBalledAns2);
    }

}
