package seedu.address.model.project;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;

public class EventList {

    private final List<Events> events = new ArrayList<>();

    /**
     * Constructs an empty {@code EventList}.
     */
    public EventList () {}

    /**
     * Constructs an {@code EventList}.
     *
     * @param events A list of {@code Events}.
     */
    public EventList (List<Events> events) {
        requireAllNonNull(events);

        this.events.addAll(events);
    }

    public List<Events> getEvents() {
        return events;
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
