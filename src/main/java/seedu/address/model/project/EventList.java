package seedu.address.model.project;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.task.repeatable.Event;

/**
 * Represents a list of Events.
 */
public class EventList {

    private final List<Event> events = new ArrayList<>();

    /**
     * Constructs an empty {@code EventList}.
     */
    public EventList () {}

    /**
     * Constructs an {@code EventList}.
     *
     * @param events A list of {@code Event}.
     */
    public EventList (List<Event> events) {
        requireNonNull(events);

        this.events.addAll(events);
    }

    /**
     * Adds an event to this {@code EventList}.
     *
     * @param event {@code Event} to add.
     */
    public void addEvent(Event event) {
        requireNonNull(event);
        this.events.add(event);
    }

    public List<Event> getEvents() {
        return events;
    }

    /**
     * Returns a sequential stream with this {@code EventList} as its source.
     * @return a sequential Stream over the events in this {@code EventList}.
     */
    public Stream<Event> stream() {
        return events.stream();
    }

    /**
     * Returns {@code events} as an {@code ObservableList<Event>}
     * @return An {@code ObservableList<Event>}
     */
    public ObservableList<Event> getAsObservableList() {
        return FXCollections.observableList(events);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventList // instanceof handles nulls
                && events.equals(((EventList) other).events)); // state check
    }

    @Override
    public int hashCode() {
        return events.hashCode();
    }

}
