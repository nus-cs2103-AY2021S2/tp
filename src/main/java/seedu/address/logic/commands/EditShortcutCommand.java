package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.shortcut.Shortcut;
import seedu.address.model.shortcut.ShortcutLibrary;

/**
 * Edits the shortcut command of an existing shortcut in the shortcut library.
 */
public class EditShortcutCommand extends Command {

    public static final String COMMAND_WORD = "editshortcut";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the shortcut command of the shortcut identified "
            + "by the shortcut name used in the shortcut library. "
            + "Existing command will be overwritten by the input command.\n"
            + "Parameters: sn/SHORTCUT_NAME (must alphanumeric without any spacing and must be an existing shortcut "
            + "name) sc/SHORTCUT_COMMAND (must be a valid ClientBook command)\n"
            + "Example: " + COMMAND_WORD + " sn/findfriends sc/find t/friend";

    public static final String MESSAGE_EDIT_SHORTCUT_SUCCESS = "Edited Shortcut: %1$s";

    private final Shortcut shortcut;

    /**
     * @param shortcutName of the shortcut in the shortcut library to edit
     * @param shortcutCommand to edit the shortcut with
     */
    public EditShortcutCommand(String shortcutName, String shortcutCommand) {
        requireNonNull(shortcutName);
        requireNonNull(shortcutCommand);

        this.shortcut = new Shortcut(shortcutName, shortcutCommand);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ShortcutLibrary shortcutLibrary = model.getShortcutLibrary();

        if (!shortcutLibrary.hasShortcut(shortcut.getShortcutName())) {
            throw new CommandException(Messages.MESSAGE_SHORTCUT_NAME_NOT_EXIST);
        }

        model.setShortcut(shortcut.getShortcutName(), shortcut.getShortcutCommand());

        return new CommandResult(String.format(MESSAGE_EDIT_SHORTCUT_SUCCESS, shortcut));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditShortcutCommand)) {
            return false;
        }

        // state check
        EditShortcutCommand e = (EditShortcutCommand) other;
        return shortcut.equals(e.shortcut);
    }
}
