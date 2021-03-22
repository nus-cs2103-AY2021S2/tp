package seedu.partyplanet.testutil;

import seedu.partyplanet.model.event.Event;
import seedu.partyplanet.model.event.EventDate;
import seedu.partyplanet.model.person.Name;
import seedu.partyplanet.model.person.Remark;

/**
 * A utility class to help with building Person objects.
 */
public class EventBuilder {

    public static final String DEFAULT_NAME = "CNY";
    public static final String DEFAULT_DATE = "2021-02-01";
    public static final String DEFAULT_DETAIL = "buy food";

    private Name name;
    private EventDate date;
    private Remark detail;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        name = new Name(DEFAULT_NAME);
        date = new EventDate(DEFAULT_DATE);
        detail = new Remark(DEFAULT_DETAIL);
    }

    /**
     * Initializes the EventBuilder with the data of {@code eventToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        name = eventToCopy.getName();
        date = eventToCopy.getDate();
        detail = eventToCopy.getDetails();
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
    public EventBuilder withDetail(String detail) {
        this.detail = new Remark(detail);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Event} that we are building.
     */
    public EventBuilder withDate(String date) {
        this.date = new EventDate(date);
        return this;
    }

    public Event build() {
        return new Event(name, date, detail);
    }

}
