package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.Description;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventStatus;
import seedu.address.model.event.EventTime;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Event}.
 */
class JsonAdaptedEvent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Event's %s field is missing!";

    private final String eventName;
    private final String start;
    private final String end;
    private final String eventStatus;
    private final String eventDescription;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedEvent} with the given event details.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("name") String eventName, @JsonProperty("start") String timeStart,
                            @JsonProperty("end") String timeEnd, @JsonProperty("status") String eventStatus,
                            @JsonProperty("description") String description,
                            @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                            @JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.eventName = eventName;
        this.start = timeStart;
        this.end = timeEnd;
        this.eventStatus = eventStatus;
        this.eventDescription = description;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (persons != null) {
            this.persons.addAll(persons);
        }
    }

    /**
     * Converts a given {@code Event} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        eventName = source.getName().eventName;
        start = changeEventTimeFormat(source.getTimeStart().eventTime.toString());
        end = changeEventTimeFormat(source.getTimeEnd().eventTime.toString());
        eventStatus = source.getStatus().toString();
        eventDescription = source.getDescription().description;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        persons.addAll(source.getPersons().stream()
                .map(JsonAdaptedPerson::new)
                .collect(Collectors.toList()));
    }

    /**
     * Returns a String with the correct EventTime format from a given string
     */
    private String changeEventTimeFormat(String eventTime) {
        String[] dateAndTime = eventTime.replaceAll("[-T]", " ").split(" ");
        String date = dateAndTime[2] + "/" + dateAndTime[1] + "/" + dateAndTime[0] + " ";
        String time = dateAndTime[3];
        return date + time;
    }

    /**
     * Converts this Jackson-friendly adapted event object into the model's {@code Event} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Event toModelType() throws IllegalValueException {
        final List<Tag> eventTags = new ArrayList<>();
        final List<Person> eventPersons = new ArrayList<>();

        for (JsonAdaptedTag tag : tagged) {
            eventTags.add(tag.toModelType());
        }

        for (JsonAdaptedPerson person : persons) {
            eventPersons.add(person.toModelType());
        }

        if (eventName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EventName.class.getSimpleName()));
        }
        if (!EventName.isValidName(eventName)) {
            throw new IllegalValueException(EventName.MESSAGE_CONSTRAINTS);
        }
        final EventName modelName = new EventName(eventName);


        if (start == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EventTime.class.getSimpleName()));
        }
        if (!EventTime.isValidEventTime(start)) {
            throw new IllegalValueException(EventTime.MESSAGE_CONSTRAINTS);
        }
        final EventTime modelTimeStart = new EventTime(start);

        if (end == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EventTime.class.getSimpleName()));
        }
        if (!EventTime.isValidEventTime(end)) {
            throw new IllegalValueException(EventTime.MESSAGE_CONSTRAINTS);
        }
        final EventTime modelTimeEnd = new EventTime(end);

        if (eventStatus == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EventStatus.class.getSimpleName()));
        }

        final EventStatus modelStatus;
        switch(eventStatus) {
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

        if (eventDescription == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(eventDescription)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(eventDescription);

        final Set<Person> modelPersons = new HashSet<>(eventPersons);
        final Set<Tag> modelTags = new HashSet<>(eventTags);
        return new Event(modelName, modelTimeStart, modelTimeEnd,
                modelStatus, modelDescription, modelTags, modelPersons);
    }

}
