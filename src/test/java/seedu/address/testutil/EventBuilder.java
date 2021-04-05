package seedu.address.testutil;

import seedu.address.model.event.Description;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventPriority;
import seedu.address.model.event.EventStatus;

/**
 * A utility class to help with building Event objects
 */
public class EventBuilder {

    public static final String DEFAULT_NAME = "CS2103T";
    public static final EventStatus DEFAULT_STATUS = EventStatus.TODO;
    public static final EventPriority DEFAULT_PRIORITY = EventPriority.LOW;
    public static final String DEFAULT_DESCRIPTION = "This is the first software engineering module in NUS";

    // Identity Fields
    private EventName eventName;
    private EventStatus status;
    private int identifier;

    // Data Fields
    private Description description;
    private EventPriority priority;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        eventName = new EventName(DEFAULT_NAME);
        status = DEFAULT_STATUS;
        description = new Description(DEFAULT_DESCRIPTION);
        priority = DEFAULT_PRIORITY;
    }

    /**
     * Initializes the EventBuilder with the data of {@code eventToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        eventName = eventToCopy.getName();
        status = eventToCopy.getStatus();
        priority = eventToCopy.getPriority();
        description = eventToCopy.getDescription();
        identifier = eventToCopy.getIdentifier();
    }

    /**
     * Sets the {@code EventName} of the {@code Event} that we are building.
     */
    public EventBuilder withName(String name) {
        this.eventName = new EventName(name);
        return this;
    }

    /**
     * Sets the {@code EventStatus} of the {@code Event} that we are building.
     */
    public EventBuilder withStatus(EventStatus status) {
        this.status = status;
        return this;
    }

    /**
     * Sets the {@code EventStatus} of the {@code Event} that we are building.
     */
    public EventBuilder withPriority(EventPriority priority) {
        this.priority = priority;
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Event} that we are building.
     */
    public EventBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Identifier} of the {@code Event} that we are building.
     */
    public EventBuilder withIdentifier(int identifier) {
        this.identifier = identifier;
        return this;
    }

    /**
     * Builds the event with existing EventBuilder attributes with identifier
     * @return Event built with EventBuilder attributes and identifier
     */
    public Event buildWithID() {
        return new Event(eventName, status, priority, description, identifier);
    }

    /**
     * Builds the event with existing EventBuilder attributes
     * @return Event built with EventBuilder attributes
     */
    public Event build() {
        return new Event(eventName, status, priority, description);
    }

}
