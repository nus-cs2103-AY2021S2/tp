package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.shortcut.ShortcutLibrary;

/**
 * Clears the Shortcut Library.
 */
public class ClearShortcutCommand extends Command {

    public static final String COMMAND_WORD = "clearshortcut";

    public static final String MESSAGE_SUCCESS = "Shortcut library has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setShortcutLibrary(new ShortcutLibrary());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
