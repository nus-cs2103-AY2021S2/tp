package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.ui.UiCommand;

/**
 * Show participants tab of a Project.
 */
public class ShowParticipantsTabCommand extends Command {

    public static final String COMMAND_WORD = "tabP";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the participants tab of an open project.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Viewing Participants Tab";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(MESSAGE_SUCCESS,
                UiCommand.SHOW_PARTICIPANTS);
    }
}
