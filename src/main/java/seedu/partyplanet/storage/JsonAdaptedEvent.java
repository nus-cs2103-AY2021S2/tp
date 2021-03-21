package seedu.partyplanet.storage;

import java.time.DateTimeException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.partyplanet.commons.exceptions.IllegalValueException;
import seedu.partyplanet.model.date.Date;
import seedu.partyplanet.model.event.Event;
import seedu.partyplanet.model.event.EventDate;
import seedu.partyplanet.model.person.Birthday;
import seedu.partyplanet.model.person.Name;
import seedu.partyplanet.model.person.Remark;

/**
 * Jackson-friendly version of {@link Event}.
 */
class JsonAdaptedEvent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Event's %s field is missing!";

    private final String name;
    private final String date;
    private final String detail;
    private final String isDone;

    /**
     * Constructs a {@code JsonAdaptedEvent} with the given event details.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("name") String name, @JsonProperty("birthday") String date,
                            @JsonProperty("remark") String detail, @JsonProperty("isDone") String isDone) {
        this.name = name;
        this.date = date;
        this.detail = detail;
        this.isDone = isDone;
    }

    /**
     * Converts a given {@code Event} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        name = source.getName().fullName;
        date = source.getDate().value;
        detail = source.getDetails().value;
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
        if (date == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Birthday.class.getSimpleName()));
        }
        if (date.equals(Date.EMPTY_DATE_STRING)) {
            modelDate = EventDate.EMPTY_EVENT_DATE;
        } else {
            try {
                modelDate = new EventDate(date);
            } catch (DateTimeException err) { // date in wrong format
                throw new IllegalValueException(Birthday.MESSAGE_CONSTRAINTS);
            } catch (IllegalArgumentException err) { // birthday year exceeds current year
                throw new IllegalValueException(Birthday.MESSAGE_BIRTHDAY_CONSTRAINTS);
            }
        }

        Remark modelDetail;
        if (detail == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }
        if (detail.equals(Remark.EMPTY_REMARK_STRING)) {
            modelDetail = Remark.EMPTY_REMARK;
        } else if (!Remark.isValidRemark(detail)) {
            throw new IllegalValueException(Remark.MESSAGE_CONSTRAINTS);
        } else {
            modelDetail = new Remark(detail);
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

        return new Event(modelName, modelDate, modelDetail, modelIsDone);
    }

}
