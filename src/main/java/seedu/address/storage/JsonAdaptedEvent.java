package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.Description;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventPriority;
import seedu.address.model.event.EventStatus;

/**
 * Jackson-friendly version of {@link Event}.
 */
class JsonAdaptedEvent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Event's %s field is missing!";

    private final String eventName;
    private final String eventStatus;
    private final String eventDescription;
    private final String eventPriority;

    /**
     * Constructs a {@code JsonAdaptedEvent} with the given event details.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("eventName") String eventName,
                            @JsonProperty("eventStatus") String eventStatus,
                            @JsonProperty("eventPriority") String eventPriority,
                            @JsonProperty("eventDescription") String description) {
        this.eventName = eventName;
        this.eventPriority = eventPriority;
        this.eventStatus = eventStatus;
        this.eventDescription = description;
    }

    /**
     * Converts a given {@code Event} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        eventName = source.getName().eventName;
        eventStatus = source.getStatus().toString();
        eventDescription = source.getDescription().description;
        eventPriority = source.getPriority().toString();
    }

    /**
     * Converts this Jackson-friendly adapted event object into the model's {@code Event} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Event toModelType() throws IllegalValueException {

        if (eventName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EventName.class.getSimpleName()));
        }
        if (!EventName.isValidName(eventName)) {
            throw new IllegalValueException(EventName.MESSAGE_CONSTRAINTS);
        }
        final EventName modelName = new EventName(eventName);

        if (eventStatus == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EventStatus.class.getSimpleName()));
        }

        final EventStatus modelStatus;
        switch(eventStatus) {
        case ("BACKLOG"):
            modelStatus = EventStatus.BACKLOG;
            break;
        case ("TODO"):
            modelStatus = EventStatus.TODO;
            break;
        case ("IN_PROGRESS"):
            modelStatus = EventStatus.IN_PROGRESS;
            break;
        case ("DONE"):
            modelStatus = EventStatus.DONE;
            break;
        default:
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EventStatus.class.getSimpleName()));
        }

        if (eventPriority == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EventPriority.class.getSimpleName()));
        }


        final EventPriority modelPriority;
        switch(eventPriority) {
        case ("HIGH"):
            modelPriority = EventPriority.HIGH;
            break;
        case ("MEDIUM"):
            modelPriority = EventPriority.MEDIUM;
            break;
        case ("LOW"):
            modelPriority = EventPriority.LOW;
            break;
        default:
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EventPriority.class.getSimpleName()));
        }

        if (eventDescription == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(eventDescription)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(eventDescription);

        return new Event(modelName, modelStatus, modelPriority, modelDescription);
    }

}
