package seedu.address.model.shortcut;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;

public class ShortcutLibrary {

    public static final String NO_SHORTCUT_FEEDBACK = "You have no shortcuts!";
    private static final String TITLE = "Shortcuts";

    private HashMap<String, String> shortcuts;

    public ShortcutLibrary() {
        this.shortcuts = new HashMap<>();
    }

    /**
     * Creates a ShortcutLibrary using the Shortcuts in the {@code toBeCopied}
     */
    public ShortcutLibrary(HashMap<String, String> toBeCopied) {
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Resets the existing data of this {@code ShortcutLibrary} with {@code newData}.
     */
    public void resetData(HashMap<String, String> newData) {
        requireNonNull(newData);
        this.shortcuts = newData;
    }

    //// person-level operations

    /**
     * Returns true if a shortcut with the same identity {@code shortcutName} exists in the shortcut library.
     */
    public boolean hasShortcut(String shortcutName) {
        requireNonNull(shortcutName);
        return this.shortcuts.containsKey(shortcutName);
    }

    /**
     * Adds a shortcut to the shortcut library.
     * The shortcut must not already exist in the shortcut library.
     */
    public void addShortcut(String shortcutName, String shortcutCommand) {
        this.shortcuts.put(shortcutName, shortcutCommand);
    }

    /**
     * Replaces the given shortcut named {@code target} in the list with a new {@code shortcutCommand}.
     * {@code target} must exist in the shortcut library.
     */
    public void setShortcut(String target, String shortcutCommand) {
        requireNonNull(target);
        requireNonNull(shortcutCommand);
        shortcuts.replace(target, shortcutCommand);
    }

    /**
     * Removes a shortcut named {@code shortcutName} from this {@code ShortcutLibrary}.
     * {@code shortcutName} must exist in the shortcut library.
     */
    public void removeShortcut(String shortcutName) {
        shortcuts.remove(shortcutName);
    }

    /**
     * Returns true is ShortcutLibrary is empty.
     */
    public boolean isEmpty() {
        return shortcuts.isEmpty();
    }

    public String getAllShortcutsInString() {
        if (shortcuts.isEmpty()) {
            return NO_SHORTCUT_FEEDBACK;
        }

        final StringBuilder sb = new StringBuilder();
        sb.append(TITLE).append("\n");
        sb.append(this.toString());
        return sb.toString();
    }

    //// accessor methods

    public HashMap<String, String> getShortcuts() {
        return this.shortcuts;
    }

    //// util methods

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        shortcuts.forEach((name, command) -> {
            String mapping = name + " = " + command + "\n";
            sb.append(mapping);
        });
        return sb.substring(0, sb.length() - 1);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShortcutLibrary // instanceof handles nulls
                && shortcuts.equals(((ShortcutLibrary) other).shortcuts));
    }

    @Override
    public int hashCode() {
        return shortcuts.hashCode();
    }

}
