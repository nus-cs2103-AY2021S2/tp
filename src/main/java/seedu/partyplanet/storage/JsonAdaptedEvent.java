package seedu.partyplanet.storage;

import java.time.DateTimeException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.partyplanet.commons.exceptions.IllegalValueException;
import seedu.partyplanet.logic.parser.exceptions.ParseException;
import seedu.partyplanet.model.event.Event;
import seedu.partyplanet.model.event.EventDate;
import seedu.partyplanet.model.name.Name;
import seedu.partyplanet.model.remark.Remark;

/**
 * Jackson-friendly version of {@link Event}.
 */
class JsonAdaptedEvent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Event's %s field is missing!";

    private final String name;
    private final String eventDate;
    private final String remark;
    private final String isDone;

    /**
     * Constructs a {@code JsonAdaptedEvent} with the given event details.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("name") String name, @JsonProperty("eventDate") String eventDate,
                            @JsonProperty("remark") String remark, @JsonProperty("isDone") String isDone) {
        this.name = name;
        this.eventDate = eventDate;
        this.remark = remark;
        this.isDone = isDone;
    }

    /**
     * Converts a given {@code Event} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        name = source.getName().fullName;
        eventDate = source.getEventDate().value;
        remark = source.getRemark().value;
        isDone = source.isDone() ? "1" : "0";
    }

    /**
     * Converts this Jackson-friendly adapted event object into the model's {@code Event} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted event.
     */
    public Event toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        EventDate modelDate;
        if (eventDate == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, EventDate.class.getSimpleName()));
        }
        if (eventDate.equals(EventDate.EMPTY_DATE_STRING)) {
            modelDate = EventDate.EMPTY_EVENT_DATE;
        } else {
            try {
                modelDate = new EventDate(eventDate);
            } catch (DateTimeException err) { // date in wrong format
                throw new ParseException(EventDate.MESSAGE_CONSTRAINTS);
            } catch (IllegalArgumentException err) { // no year field;
                throw new ParseException(EventDate.MESSAGE_YEAR_CONSTRAINTS);
            }
        }

        Remark modelRemark;
        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }
        if (remark.equals(Remark.EMPTY_REMARK_STRING)) {
            modelRemark = Remark.EMPTY_REMARK;
        } else if (!Remark.isValidRemark(remark)) {
            throw new IllegalValueException(Remark.MESSAGE_CONSTRAINTS);
        } else {
            modelRemark = new Remark(remark);
        }

        boolean modelIsDone;
        if (isDone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "isDone"));
        }
        if (isDone.equals("1")) {
            modelIsDone = true;
        } else if (isDone.equals("0")) {
            modelIsDone = false;
        } else {
            throw new IllegalValueException("isDone should be either \"1\" or \"0\"");
        }

        return new Event(modelName, modelDate, modelRemark, modelIsDone);
    }

}
