package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.shortcut.ShortcutLibrary;

/**
 * Deletes a shortcut identified using it's shortcut name from the shortcut library.
 */
public class DeleteShortcutCommand extends Command {

    public static final String COMMAND_WORD = "deleteshortcut";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the shortcut identified by the shortcut name used in the shortcut library.\n"
            + "Parameters: SHORTCUT_NAME (must be an existing shortcut name)\n"
            + "Example: " + COMMAND_WORD + " listshowpolicy";

    public static final String MESSAGE_DELETE_SHORTCUT_SUCCESS = "Deleted Shortcut: %1$s";

    private final String shortcutName;

    public DeleteShortcutCommand(String shortcutName) {
        this.shortcutName = shortcutName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ShortcutLibrary shortcutLibrary = model.getShortcutLibrary();

        if (!shortcutLibrary.hasShortcut(shortcutName)) {
            throw new CommandException(Messages.MESSAGE_SHORTCUT_NAME_NOT_EXIST);
        }

        model.deleteShortcut(shortcutName);
        return new CommandResult(String.format(MESSAGE_DELETE_SHORTCUT_SUCCESS, shortcutName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteShortcutCommand // instanceof handles nulls
                && shortcutName.equals(((DeleteShortcutCommand) other).shortcutName)); // state check
    }
}
