package seedu.address.model.session;

import org.junit.jupiter.api.Test;

class RecurringSessionTest {

    @Test
    void getInterval() {
    }

    @Test
    void getEndDateTime() {
    }

    @Test
    void numOfSessionBetween() {
        Interval interval = new Interval(7);
        SessionDate firstSessionDate = new SessionDate("2021-03-03", "10:00");
        SessionDate lastSessionDate = new SessionDate("2021-04-07", "10:00");
        //first and last must match
        RecurringSession s = new RecurringSession(firstSessionDate, new Duration("100"), new Subject("Math"),
                new Fee("40"), interval, lastSessionDate);
        SessionDate firstOfMarch = new SessionDate("2021-03-01", "00:00");
        SessionDate lastOfMarch = new SessionDate("2021-03-31", "23:59");
        int ans = s.numOfSessionBetween(firstOfMarch, lastOfMarch);
        System.out.println(ans);


    }

}
