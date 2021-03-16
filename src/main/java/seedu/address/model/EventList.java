package seedu.address.model;

import java.util.ArrayList;

public class EventList {
    public static final String NO_ASSIGNMENTS_OUTPUT = "You have no events! Yay! :)\n";
    private ArrayList<Event> events;

    public EventList() {
        this.events = new ArrayList<Event>();
    }

    public void add(Event e) {
        events.add(e);
    }

    public ArrayList<Event> getEvents() {
        return new ArrayList<>(events);
    }
}
