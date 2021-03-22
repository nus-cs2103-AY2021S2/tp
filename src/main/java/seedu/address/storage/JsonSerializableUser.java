package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.user.User;

/**
 * A User that is serializable to JSON format.
 */
@JsonRootName(value = "user")
public class JsonSerializableUser {

    private final JsonAdaptedUser user;

    /**
     * Constructs a {@code JsonSerializableUser} with the given parameter.
     */
    @JsonCreator
    public JsonSerializableUser(@JsonProperty("user") JsonAdaptedUser user) {
        this.user = user;
    }

    /**
     * Converts a given {@code User} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableUser}.
     */
    public JsonSerializableUser(User source) {
        this.user = new JsonAdaptedUser(source);
    }

    /**
     * Converts this User into the model's {@code User} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public User toModelType() throws IllegalValueException {
        return user.toModelType();
    }

}
