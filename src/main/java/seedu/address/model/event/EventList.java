package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.schedule.Schedule;

public class EventList implements Iterable<Event> {

    private final ObservableList<Event> internalList = FXCollections.observableArrayList();
    private final ObservableList<Event> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent event as the given argument.
     */
    public boolean contains(Event toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Returns true if the list contains an equivalent event as the given argument.
     */
    public boolean containsDate(Event toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(new DateRangePredicate(toCheck));
    }

    public void setEvents(EventList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code events}.
     * {@code events} must not contain duplicate events.
     */
    public void setEvents(List<Appointment> appointments, List<Schedule> schedules) {
        requireAllNonNull(appointments);
        requireAllNonNull(schedules);
        internalList.addAll(appointments);
        internalList.addAll(schedules);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     * Sorts by start time of each event.
     */
    public ObservableList<Event> asUnmodifiableObservableList() {
        Collections.sort(internalList, Comparator.comparing(a -> a.getTimeFrom().value));
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Event> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventList // instanceof handles nulls
                && internalList.equals(((EventList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code Event} contains only unique events.
     */
    private boolean eventsAreUnique(List<Event> events) {
        for (int i = 0; i < events.size() - 1; i++) {
            for (int j = i + 1; j < events.size(); j++) {
                if (events.get(i).equals(events.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
