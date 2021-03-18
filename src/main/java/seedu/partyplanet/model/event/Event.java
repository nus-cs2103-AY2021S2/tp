package seedu.partyplanet.model.event;

import java.util.Date;

/**
 * A very simple Event class
 */
public class Event {

    // Identity fields
    private String name;

    // Data fields
    private Date date;
    private String details;

    /**
     * Default Event constructor
     */
    public Event(String name, Date date, String details) {
        this.name = name;
        this.date = date;
        this.details = details;
    }

    /**
     * Returns name of event
     */
    public String getName() {
        return name;
    }

    /**
     * Returns date of event
     */
    public Date getDate() {
        return date;
    }

    /**
     * Returns details of event
     */
    public String getDetails() {
        return details;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Event)) {
            return false;
        }

        // state check
        Event event = (Event) other;
        return getName().equals(event.getName())
                && getDate().equals(event.getDate())
                && getDetails().equals(event.getDetails());

    }


}
