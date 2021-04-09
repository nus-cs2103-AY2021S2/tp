package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.Observer;

class ObservableCalendarDateTest {
    private static int observerCount = 0;

    private final ObservableCalendarDate ocd = new ObservableCalendarDate(LocalDate.now());

    @Test
    public void set_validDate_success() {
        LocalDate validDate = LocalDate.of(2020, 1, 1);
        ocd.set(validDate);

        ObservableCalendarDate expectedDate =
                new ObservableCalendarDate(LocalDate.of(2020, 1, 1));
        assertEquals(expectedDate, ocd);
    }

    @Test
    public void reset() {
        LocalDate validDate = LocalDate.of(2020, 1, 1);
        ocd.set(validDate);
        ocd.reset();

        ObservableCalendarDate expectedDate =
                new ObservableCalendarDate(LocalDate.now());
        assertEquals(expectedDate, ocd);
    }

    @Test
    public void addObserver_nullValue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ocd.addObserver(null));
    }

    @Test
    public void notifyAllObservers_multipleObservers_success() {
        observerCount = 0;
        ocd.addObserver(new ObserverStub());
        ocd.addObserver(new ObserverStub());
        ocd.notifyAllObservers();
        assertEquals(2, observerCount);
    }

    @Test
    public void equals() {
        // default -> returns true
        assertTrue(ocd.equals(new ObservableCalendarDate(LocalDate.now())));

        // same dates -> returns true
        ocd.set(LocalDate.of(2021, 5, 5));
        assertTrue(ocd.equals(new ObservableCalendarDate(LocalDate.of(2021, 5, 5))));

        // different dates -> returns false
        ocd.reset();
        assertFalse(ocd.equals(new ObservableCalendarDate(LocalDate.of(2021, 5, 5))));

        // same dates and observer list -> returns true
        ocd.addObserver(new ObserverStub());
        ocd.addObserver(new ObserverStub());
        ObservableCalendarDate ocdCopy = new ObservableCalendarDate(LocalDate.now());
        ocdCopy.addObserver(new ObserverStub());
        ocdCopy.addObserver(new ObserverStub());
        assertTrue(ocd.equals(ocdCopy));

        // different observer list -> returns false
        assertTrue(ocd.equals(new ObservableCalendarDate(LocalDate.now())));
    }

    private static class ObserverStub implements Observer {
        @Override
        public void update() {
            ObservableCalendarDateTest.observerCount++;
        }
    }
}
