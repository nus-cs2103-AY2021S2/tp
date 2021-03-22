package seedu.us.among.logic.commands;

import static seedu.us.among.commons.util.CollectionUtil.requireAllNonNull;

import seedu.us.among.logic.commands.exceptions.CommandException;
import seedu.us.among.logic.request.exceptions.RequestException;
import seedu.us.among.model.Model;

/**
 * Toggles the theme of the application based on user input.
 */
public class ToggleCommand extends Command {

    public static final String COMMAND_WORD = "toggle";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Toggles the theme of the application.\n"
            + "Parameters: THEME (currently supported themes are light/dark)\n"
            + "Example: " + COMMAND_WORD + " light";

    private final String theme;

    /**
     * @param theme to toggle to
     */
    public ToggleCommand(String theme) {
        requireAllNonNull(theme);
        this.theme = theme;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException, RequestException {
        return new CommandResult("You have set the application theme to " + theme + ".", theme);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ToggleCommand)) {
            return false;
        }

        // state check
        ToggleCommand e = (ToggleCommand) other;
        return theme.equals(e.theme);
    }
}
