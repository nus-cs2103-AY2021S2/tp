package seedu.partyplanet.storage;

import java.time.DateTimeException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.partyplanet.commons.exceptions.IllegalValueException;
import seedu.partyplanet.model.event.Event;
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

    /**
     * Constructs a {@code JsonAdaptedEvent} with the given event details.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("name") String name, @JsonProperty("birthday") String date,
                            @JsonProperty("remark") String detail) {
        this.name = name;
        this.date = date;
        this.detail = detail;
    }

    /**
     * Converts a given {@code Event} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        name = source.getName().fullName;
        date = source.getDate().value;
        detail = source.getDetails().value;
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

        Birthday modelDate;
        if (date == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Birthday.class.getSimpleName()));
        }
        if (date.equals(Birthday.EMPTY_BIRTHDAY_STRING)) {
            modelDate = Birthday.EMPTY_BIRTHDAY;
        } else {
            try {
                modelDate = new Birthday(date);
            } catch (DateTimeException err) { // date in wrong format
                throw new IllegalValueException(Birthday.MESSAGE_CONSTRAINTS);
            } catch (IllegalArgumentException err) { // birthday year exceeds current year
                throw new IllegalValueException(Birthday.MESSAGE_YEAR_CONSTRAINTS);
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

        return new Event(modelName, modelDate, modelDetail);
    }

}
