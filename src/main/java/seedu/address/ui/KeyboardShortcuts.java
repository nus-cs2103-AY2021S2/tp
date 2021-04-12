package seedu.address.ui;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

/**
 * A utility class containing a list of keyboard shortcuts to be used in the UI.
 */
public class KeyboardShortcuts {
    public static final KeyCodeCombination HELP_SHORTCUT =
            new KeyCodeCombination(KeyCode.F1);
    public static final KeyCodeCombination UNDO_SHORTCUT =
            new KeyCodeCombination(KeyCode.Z, KeyCombination.SHORTCUT_DOWN);
    public static final KeyCodeCombination REDO_SHORTCUT =
            new KeyCodeCombination(KeyCode.Z, KeyCombination.SHORTCUT_DOWN, KeyCombination.SHIFT_DOWN);

    /**
     * To prevent instantiation
     */
    private KeyboardShortcuts() {
    }
}
