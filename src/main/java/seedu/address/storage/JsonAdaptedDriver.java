package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.driver.Driver;

/**
 * Jackson-friendly version of {@link Driver}.
 */
class JsonAdaptedDriver {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Driver's %s field is missing!";
    public static final String MODEL_CLASS_NAME = "Driver";
    public static final StorageUtil MODEL_UTIL = new StorageUtil(MODEL_CLASS_NAME);

    private final String name;
    private final String phone;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code name}.
     */
    @JsonCreator
    public JsonAdaptedDriver(@JsonProperty("name") String name, @JsonProperty("phone") String phone) {
        this.name = name;
        this.phone = phone;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedDriver(Driver source) {
        this.name = source.getName().fullName;
        this.phone = source.getPhone().value;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Driver toModelType() throws IllegalValueException {
        final Name modelName = MODEL_UTIL.verifyAndReturnName(name);
        final Phone modelPhone = MODEL_UTIL.verifyAndReturnPhone(phone);

        return new Driver(modelName, modelPhone);
    }

}
