package seedu.address.model;

import java.util.ArrayList;
import java.util.Collections;

import seedu.address.model.person.Birthday;

/**
 * A list specifically to store events.
 */
public class EventList {
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

    public void sort() {
        Collections.sort(events);
    }

    @Override
    public String toString() {
        String displayString = "";
        for (Event event : events) {
            String tagName = event.getTag().tagName;
            if (tagName.equals("Birthday")) {
                displayString += ((Birthday) event).getDescription() + ": " + event.toString() + "\n";
            } else {
                displayString += event.tag + ": " + event.toString() + "\n";
            }
        }

        return displayString;
    }
}
