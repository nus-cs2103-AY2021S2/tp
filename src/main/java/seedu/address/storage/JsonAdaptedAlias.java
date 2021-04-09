package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.alias.Alias;

/**
 * Jackson-friendly version of {@link Alias}.
 */
public class JsonAdaptedAlias {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Alias's %s field is missing!";

    private final String aliasName;
    private final String command;

    /**
     * Constructs a {@code JsonAdaptedAlias} with the given resident details.
     *
     * @param aliasName Name of the alias.
     * @param command The command of the alias.
     */
    @JsonCreator
    public JsonAdaptedAlias(@JsonProperty("aliasName") String aliasName, @JsonProperty("command") String command) {
        this.aliasName = aliasName;
        this.command = command;
    }

    /**
     * Converts a given {@code Alias} into this class for Jackson use.
     *
     * @param source The given alias.
     */
    public JsonAdaptedAlias(Alias source) {
        aliasName = source.getAliasName();
        command = source.getCommand();
    }

    /**
     * Converts this Jackson-friendly adapted alias object into the model's {@code Alias} object.
     *
     * @return An Alias object.
     * @throws IllegalValueException If any field value is invalid.
     */
    public Alias toModelType() throws IllegalValueException {
        if (aliasName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "name"));
        }
        if (!Alias.isValidName(aliasName)) {
            throw new IllegalValueException(Alias.MESSAGE_NAME_CONSTRAINTS);
        }

        if (command == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "command"));
        }
        if (!Alias.isValidCommand(command)) {
            throw new IllegalValueException(Alias.MESSAGE_COMMAND_CONSTRAINTS);
        }

        return new Alias(aliasName, command);
    }
}
