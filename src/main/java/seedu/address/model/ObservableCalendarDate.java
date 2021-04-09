package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import seedu.address.commons.Observable;
import seedu.address.commons.Observer;

/**
 * An observable class encompassing a LocalDate object and notifies its obervers whenever there is a change.
 */
public class ObservableCalendarDate implements Observable<LocalDate> {
    private LocalDate date;
    private final ArrayList<Observer> observers = new ArrayList<>();

    /**
     * Creates an observable date that notifies its observers on changes.
     *
     * @param date Date to be set.
     */
    public ObservableCalendarDate(LocalDate date) {
        if (date == null) {
            this.date = LocalDate.now();
        } else {
            this.date = date;
        }
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

    /**
     * Adds an observer to this observable's list. Throws NullPointerException if argument is null.
     *
     * @param observer Object implementing the Observer interface.
     */
    public void addObserver(Observer observer) {
        requireNonNull(observer);
        observers.add(observer);
    }

    protected void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        ObservableCalendarDate otherDate = (ObservableCalendarDate) other;
        return date.equals(otherDate.date) && observers.equals(otherDate.observers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, observers);
    }
}
