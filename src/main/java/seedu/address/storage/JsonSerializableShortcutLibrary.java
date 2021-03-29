package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.shortcut.Shortcut;
import seedu.address.model.shortcut.ShortcutLibrary;

/**
 * An Immutable ShortcutLibrary that is serializable to JSON format.
 */
@JsonRootName(value = "shortcutlibrary")
public class JsonSerializableShortcutLibrary {

    public static final String MESSAGE_DUPLICATE_SHORTCUT = "Shortcuts list contains duplicate shortcut(s).";

    private final List<JsonAdaptedShortcut> shortcuts = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableShortcutLibrary} with the given shortcuts.
     */
    @JsonCreator
    public JsonSerializableShortcutLibrary(@JsonProperty("shortcuts") List<JsonAdaptedShortcut> shortcuts) {
        this.shortcuts.addAll(shortcuts);
    }

    /**
     * Converts a given {@code ShortcutLibrary} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableShortcutLibrary}.
     */
    public JsonSerializableShortcutLibrary(ShortcutLibrary source) {
        source.getShortcuts().forEach((name, command) -> {
            JsonAdaptedShortcut shortcut = new JsonAdaptedShortcut(name + "=" + command);
            shortcuts.add(shortcut);
        });
    }

    /**
     * Converts this shortcut library into the model's {@code ShortcutLibrary} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ShortcutLibrary toModelType() throws IllegalValueException {
        ShortcutLibrary shortcutLibrary = new ShortcutLibrary();
        for (JsonAdaptedShortcut jsonAdaptedShortcut : shortcuts) {
            Shortcut shortcut = jsonAdaptedShortcut.toModelType();
            if (shortcutLibrary.hasShortcut(shortcut.getShortcutName())) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_SHORTCUT);
            }
            shortcutLibrary.addShortcut(shortcut.getShortcutName(), shortcut.getShortcutCommand());
        }
        return shortcutLibrary;
    }

}
