package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.shortcut.ShortcutLibrary;

/**
 * Lists all persons in the address book to the user. Attribute can be specified to show only specific attributes.
 */
public class ListShortcutCommand extends Command {

    public static final String COMMAND_WORD = "listshortcut";
    public static final String MESSAGE_SUCCESS = "Listed all shortcuts";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ShortcutLibrary shortcutLibrary = model.getShortcutLibrary();

        String shortcutList = shortcutLibrary.getAllShortcutsInString();
        return new CommandResult(shortcutList, false, false, true, false);
    }
}
