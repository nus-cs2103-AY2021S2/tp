package seedu.booking.logic.commands;

import static seedu.booking.commons.core.Messages.PROMPT_EMAIL_MESSAGE;
import static seedu.booking.logic.commands.states.BookingCommandState.STATE_EMAIL;

import seedu.booking.logic.commands.states.BookingCommandState;
import seedu.booking.logic.commands.states.CommandState;
import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;

/**
 * Sets up necessary conditions for multi-step command for create booking
 */
public class PromptCreateBookingCommand extends Command {

    public static final String COMMAND_WORD = "create_booking";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Starts the multi-step create booking.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) {

        CommandState commandState = new BookingCommandState();
        ModelManager.setCommandState(commandState);
        ModelManager.setStateActive();
        ModelManager.setState(STATE_EMAIL);
        return new CommandResult(PROMPT_EMAIL_MESSAGE);
    }

}
