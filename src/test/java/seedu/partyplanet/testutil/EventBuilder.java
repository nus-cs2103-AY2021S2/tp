package seedu.partyplanet.testutil;

import seedu.partyplanet.model.event.Event;
import seedu.partyplanet.model.event.EventDate;
import seedu.partyplanet.model.name.Name;
import seedu.partyplanet.model.remark.Remark;

/**
 * A utility class to help with building Person objects.
 */
public class EventBuilder {

    public static final String DEFAULT_NAME = "CNY";
    public static final String DEFAULT_DATE = "2021-02-01";
    public static final String DEFAULT_REMARK = "buy food";

    private Name name;
    private EventDate eventDate;
    private Remark remark;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        name = new Name(DEFAULT_NAME);
        eventDate = new EventDate(DEFAULT_DATE);
        remark = new Remark(DEFAULT_REMARK);
    }

    /**
     * Initializes the EventBuilder with the data of {@code eventToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        name = eventToCopy.getName();
        eventDate = eventToCopy.getEventDate();
        remark = eventToCopy.getRemark();
    }

    /**
     * Creates an {@code EventBuilder} with only name specified.
     */
    public EventBuilder(String nameString) {
        name = new Name(nameString);
        eventDate = EventDate.EMPTY_EVENT_DATE;
        remark = Remark.EMPTY_REMARK;
    }

    /**
     * Sets the {@code Name} of the {@code Event} that we are building.
     */
    public EventBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Event} that we are building.
     */
    public EventBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Event} that we are building.
     */
    public EventBuilder withDate(String eventDate) {
        this.eventDate = new EventDate(eventDate);
        return this;
    }

    public Event build() {
        return new Event(name, eventDate, remark);
    }

}
