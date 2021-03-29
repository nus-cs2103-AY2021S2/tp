package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.shortcut.Shortcut;

/**
 * Jackson-friendly version of {@link Shortcut}.
 */
public class JsonAdaptedShortcut {

    private final String shortcut;

    /**
     * Constructs a {@code JsonAdaptedShortcut} with the given {@code shortcutName} and {@code shortcutCommand}.
     */
    @JsonCreator
    public JsonAdaptedShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

    /**
     * Converts a given {@code Shortcut} into this class for Jackson use.
     */
    public JsonAdaptedShortcut(Shortcut source) {
        shortcut = source.getShortcutName() + "=" + source.getShortcutCommand();
    }

    @JsonValue
    public String getShortcut() {
        return shortcut;
    }

    /**
     * Converts this Jackson-friendly adapted shortcut object into the model's {@code Shortcut} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted shortcut.
     */
    public Shortcut toModelType() throws IllegalValueException {
        String[] split = shortcut.split("=");
        String shortcutName = split[0];
        String shortcutCommand = split[1];

        if (!Shortcut.isValidShortcutName(shortcutName)) {
            throw new IllegalValueException(Shortcut.MESSAGE_NAME_CONSTRAINTS);
        }
        if (!Shortcut.isValidShortcutCommand(shortcutCommand)) {
            throw new IllegalValueException(Shortcut.MESSAGE_COMMAND_CONSTRAINTS);
        }
        return new Shortcut(shortcutName, shortcutCommand);
    }

}
