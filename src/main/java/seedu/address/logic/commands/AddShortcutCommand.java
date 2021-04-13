package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.shortcut.Shortcut;

/**
 * Adds a shortcut to the existing shortcut library.
 */
public class AddShortcutCommand extends Command {

    public static final String COMMAND_WORD = "addshortcut";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a shortcut to the shortcut library.\n"
            + "Parameters: sn/SHORTCUT_NAME (must be alphanumeric without spacing and must not be an existing shortcut "
            + "name) sc/SHORTCUT_COMMAND (must be a valid ClientBook command)\n"
            + "Example: " + COMMAND_WORD + " sn/sna sc/sort -n -asc";

    public static final String MESSAGE_SUCCESS = "New shortcut added: %1$s";
    public static final String MESSAGE_DUPLICATE_SHORTCUT = "This shortcut name already exists in the shortcut library";

    private final Shortcut shortcut;

    public AddShortcutCommand(String shortcutName, String shortcutCommand) {
        this.shortcut = new Shortcut(shortcutName, shortcutCommand);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasShortcut(shortcut.getShortcutName())) {
            throw new CommandException(MESSAGE_DUPLICATE_SHORTCUT);
        }

        model.addShortcut(shortcut.getShortcutName(), shortcut.getShortcutCommand());
        return new CommandResult(String.format(MESSAGE_SUCCESS, shortcut));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddShortcutCommand // instanceof handles nulls
                && shortcut.equals(((AddShortcutCommand) other).shortcut));
    }
}
