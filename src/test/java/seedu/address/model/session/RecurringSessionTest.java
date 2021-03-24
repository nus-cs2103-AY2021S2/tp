package seedu.address.model.session;

import org.junit.jupiter.api.Test;

import java.time.temporal.ChronoUnit;

class RecurringSessionTest {

    @Test
    void getInterval() {
    }

    @Test
    void getEndDateTime() {
    }

    @Test
    void endAfter() {
        Interval interval = new Interval(7);
        SessionDate firstSessionDate = new SessionDate("2021-03-01", "10:00");
        SessionDate firstOfMarch = new SessionDate("2021-03-01", "00:00");
        SessionDate lastOfMarch = new SessionDate("2021-03-31", "23:59");

        int dayToStart = (int) ChronoUnit.DAYS.between(firstSessionDate.getDateTime(), firstOfMarch.getDateTime());
        System.out.println(dayToStart);
        int numOfDays = (int) ChronoUnit.DAYS.between(firstOfMarch.getDateTime(), lastOfMarch.getDateTime());
        System.out.println(numOfDays);
        System.out.println( (numOfDays - (dayToStart % interval.value)) / interval.value);
    }

}