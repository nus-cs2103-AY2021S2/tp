package seedu.address.model.shortcut;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.logic.commands.CommandWord;
import seedu.address.logic.parser.ClientBookParser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents an Shortcut in the shortcut library.
 * Guarantees: immutable; name is valid as declared in {@link #isValidShortcutName(String)} and
 * command is valid as declared in {@link #isValidShortcutCommand(String)}
 */
public class Shortcut {

    public static final String MESSAGE_NAME_CONSTRAINTS = "Shortcut names should be alphanumeric without spacing";
    public static final String MESSAGE_COMMAND_CONSTRAINTS = "Shortcut commands should be valid ClientBook commands "
            + "(e.g. sort -n -asc)";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String shortcutName;
    public final String shortcutCommand;

    /**
     * Constructs a {@code Shortcut}.
     *
     * @param shortcutName A valid shortcut name.
     * @param shortcutCommand A valid shortcut command.
     */
    public Shortcut(String shortcutName, String shortcutCommand) {
        requireNonNull(shortcutName);
        requireNonNull(shortcutCommand);
        checkArgument(isValidShortcutName(shortcutName), MESSAGE_NAME_CONSTRAINTS);
        checkArgument(isValidShortcutCommand(shortcutCommand), MESSAGE_COMMAND_CONSTRAINTS);
        this.shortcutName = shortcutName;
        this.shortcutCommand = shortcutCommand;
    }

    /**
     * Returns true if a given string is a valid shortcut name.
     */
    public static boolean isValidShortcutName(String test) {
        requireNonNull(test);
        return test.matches(VALIDATION_REGEX) && !CommandWord.contains(test);
    }

    /**
     * Returns true if a given string is a valid command.
     */
    public static boolean isValidShortcutCommand(String test) {
        requireNonNull(test);
        try {
            ClientBookParser clientBookParser = new ClientBookParser();
            clientBookParser.parseCommand(test);
        } catch (ParseException ex) {
            return false;
        }
        return true;
    }

    /**
     * Returns the shortcut name.
     */
    public String getShortcutName() {
        return this.shortcutName;
    }

    /**
     * Returns the shortcut command.
     */
    public String getShortcutCommand() {
        return this.shortcutCommand;
    }

    /**
     * Returns true if both shortcuts have the same name.
     */
    public boolean isSameShortcutName(Shortcut otherShortcut) {
        return otherShortcut != null
                && otherShortcut.getShortcutName().equals(getShortcutName());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Shortcut // instanceof handles nulls
                && shortcutName.equals(((Shortcut) other).shortcutName) // state check
                && shortcutCommand.equals(((Shortcut) other).shortcutCommand)); // state check
    }

    @Override
    public int hashCode() {
        return shortcutName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return shortcutName + " = " + shortcutCommand;
    }

}
