package seedu.address.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.Observable;
import seedu.address.commons.Observer;

/**
 * An observable class encompassing a LocalDate object and notifies its obervers whenever there is a change.
 */
public class ObservableCalendarDate implements Observable<LocalDate> {
    private LocalDate date;
    private List<Observer> observers = new ArrayList<>();

    /**
     * Creates an observable date that notifies its observers on changes.
     *
     * @param date Date to be set.
     */
    public ObservableCalendarDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Sets the observable value and notifies all its observers. Cannot be set with a null value.
     *
     * @param date Date to update the observable value with.
     */
    public void set(LocalDate date) {
        assert date != null : "Cannot set observable viewing date with null";
        this.date = date;
        notifyAllObservers();
    }

    /**
     * Resets the observable date to today's date, and notifies all its observers.
     */
    public void reset() {
        this.date = LocalDate.now();
        notifyAllObservers();
    }

    public LocalDate getDate() {
        return date;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    private void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
