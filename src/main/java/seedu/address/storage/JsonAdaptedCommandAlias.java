package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.alias.Alias;
import seedu.address.model.alias.Command;
import seedu.address.model.alias.CommandAlias;

/**
 * Jackson-friendly version of {@link CommandAlias}.
 */
class JsonAdaptedCommandAlias {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Alias's %s field is missing!";

    private final String alias;
    private final String command;

    /**
     * Constructs a {@code JsonAdaptedCommandAlias} with the given alias details.
     */
    @JsonCreator
    public JsonAdaptedCommandAlias(@JsonProperty("alias") String alias, @JsonProperty("command") String command) {
        this.alias = alias;
        this.command = command;
    }

    /**
     * Converts a given {@code Alias} into this class for Jackson use.
     */
    public JsonAdaptedCommandAlias(CommandAlias source) {
        alias = source.getAlias().alias;
        command = source.getCommand().command;
    }

    /**
     * Converts this Jackson-friendly adapted alias object into the model's {@code Alias} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted alias.
     */
    public CommandAlias toModelType() throws IllegalValueException {
        if (alias == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Alias.class.getSimpleName()));
        }
        if (!Alias.isValidAlias(alias)) {
            throw new IllegalValueException(Alias.MESSAGE_CONSTRAINTS);
        }
        final Alias modelAlias = new Alias(alias);

        if (command == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Command.class.getSimpleName()));
        }
        if (!Command.isValidCommand(command)) {
            throw new IllegalValueException(Command.MESSAGE_CONSTRAINTS);
        }
        final Command modelCommand = new Command(command);

        return new CommandAlias(modelAlias, modelCommand);
    }

}
