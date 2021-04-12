package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_EMAIL;
import static seedu.booking.commons.core.Messages.PROMPT_BOOKING_VENUE_MESSAGE;
import static seedu.booking.commons.core.Messages.PROMPT_MESSAGE_EXIT_PROMPT;
import static seedu.booking.commons.core.Messages.PROMPT_MESSAGE_TRY_AGAIN;
import static seedu.booking.logic.commands.states.AddBookingCommandState.STATE_VENUE;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_EMAIL;

import seedu.booking.logic.StatefulLogicManager;
import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.logic.commands.states.AddBookingCommandState;
import seedu.booking.logic.commands.states.CommandState;
import seedu.booking.model.Model;
import seedu.booking.model.person.Email;

/**
 * Sets up necessary conditions for multi-step command for create booking
 */
public class PromptAddBookingCommand extends Command {

    public static final String COMMAND_WORD = "add_booking";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " "
            + PREFIX_EMAIL + "EMAIL: Starts multi-step process to add booking.\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_EMAIL + "alice@gmail.com";

    private final Email email;

    public PromptAddBookingCommand(Email email) {
        this.email = email;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasPersonWithEmail(email)) {
            throw new CommandException(MESSAGE_INVALID_PERSON_DISPLAYED_EMAIL
                    + PROMPT_MESSAGE_TRY_AGAIN);
        }

        CommandState commandState = new AddBookingCommandState(email);
        StatefulLogicManager.setCommandState(commandState);
        StatefulLogicManager.setStateActive();
        StatefulLogicManager.setState(STATE_VENUE);
        return new CommandResult(PROMPT_BOOKING_VENUE_MESSAGE + PROMPT_MESSAGE_EXIT_PROMPT);
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PromptAddBookingCommand // instanceof handles nulls
                && this.email.equals(((PromptAddBookingCommand) other).email));
    }
}
