package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.event.Description;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventStatus;
import seedu.address.model.event.EventTime;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Event objects
 */
public class EventBuilder {

    public static final String DEFAULT_NAME = "CS2103T";
    public static final String DEFAULT_TIME_START = "20/12/2022 18:00";
    public static final String DEFAULT_TIME_END = "01/10/2023 19:00";
    public static final EventStatus DEFAULT_STATUS = EventStatus.TODO;
    public static final String DEFAULT_DESCRIPTION = "This is the first software engineering module in NUS";

    // Identity Fields
    private EventName eventName;
    private EventTime timeStart;
    private EventTime timeEnd;
    private EventStatus status;
    private int identifier;

    // Data Fields
    private Description description;
    private Set<Tag> tags;
    private Set<Person> persons;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        eventName = new EventName(DEFAULT_NAME);
        timeStart = new EventTime(DEFAULT_TIME_START);
        timeEnd = new EventTime(DEFAULT_TIME_END);
        status = DEFAULT_STATUS;
        description = new Description(DEFAULT_DESCRIPTION);
        tags = new HashSet<>();
        persons = new HashSet<>();
    }

    /**
     * Initializes the EventBuilder with the data of {@code eventToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        eventName = eventToCopy.getName();
        // timeStart = eventToCopy.getTimeStart(); // Commented out in v1.2
        // timeEnd = eventToCopy.getTimeEnd(); // Commented out in v1.2
        status = eventToCopy.getStatus();
        description = eventToCopy.getDescription();
        tags = new HashSet<>(eventToCopy.getTags());
        persons = new HashSet<>(eventToCopy.getPersons());
        identifier = eventToCopy.getIdentifier();
    }

    /**
     * Sets the {@code EventName} of the {@code Event} that we are building.
     */
    public EventBuilder withName(String name) {
        this.eventName = new EventName(name);
        return this;
    }

    /* Commented out v1.2
    public EventBuilder withTimeStart(String timeStartString) {
        this.timeStart = new EventTime(timeStartString);
        return this;
    }
     */

    /* Commented out v1.2
    public EventBuilder withTimeEnd(String endTimeString) {
        this.timeEnd = new EventTime(endTimeString);
        return this;
    }
     */

    /**
     * Sets the {@code EventStatus} of the {@code Event} that we are building.
     */
    public EventBuilder withStatus(EventStatus status) {
        this.status = status;
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
     * Takes sample set {@code Set<Person>} and set it to the {@code Event} that we are building.
     */
    public EventBuilder withTestPersons() {
        this.persons = SampleDataUtil.getSamplePersonSet();
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public EventBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
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
        return new Event(eventName, status, description, identifier);
    }

    /**
     * Builds the event with existing EventBuilder attributes
     * @return Event built with EventBuilder attributes
     */
    public Event build() {
        return new Event(eventName, status, description);
    }

}
