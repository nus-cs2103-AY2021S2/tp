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
    private final String birthday;
    private final String remark;

    /**
     * Constructs a {@code JsonAdaptedEvent} with the given event details.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("name") String name, @JsonProperty("birthday") String birthday,
                            @JsonProperty("remark") String remark) {
        this.name = name;
        this.birthday = birthday;
        this.remark = remark;
    }

    /**
     * Converts a given {@code Event} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        name = source.getName().fullName;
        birthday = source.getDate().value;
        remark = source.getDetails().value;
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

        Birthday modelBirthday;
        if (birthday == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Birthday.class.getSimpleName()));
        }
        if (birthday.equals(Birthday.EMPTY_BIRTHDAY_STRING)) {
            modelBirthday = Birthday.EMPTY_BIRTHDAY;
        } else {
            try {
                modelBirthday = new Birthday(birthday);
            } catch (DateTimeException err) { // date in wrong format
                throw new IllegalValueException(Birthday.MESSAGE_CONSTRAINTS);
            } catch (IllegalArgumentException err) { // birthday year exceeds current year
                throw new IllegalValueException(Birthday.MESSAGE_YEAR_CONSTRAINTS);
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

        return new Event(modelName, modelBirthday, modelRemark);
    }

}
