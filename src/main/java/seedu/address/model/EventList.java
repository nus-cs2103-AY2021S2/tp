package seedu.address.model;

import java.util.ArrayList;
import seedu.address.model.person.Birthday;

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

    public boolean isEmpty() {
        return events.isEmpty();
    }

    @Override
    public String toString() {
        String displayString = "";
        for (Event event : events) {
            if (event instanceof Birthday) {
                displayString += ((Birthday) event).getDescription() + ": " + event.toString() + "\n";
            } else {
                displayString += event.tag + ": " + event.toString() + "\n";
            }
        }

        return displayString;
    }
}
