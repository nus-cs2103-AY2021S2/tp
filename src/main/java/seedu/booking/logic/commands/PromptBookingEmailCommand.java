package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_EMAIL;
import static seedu.booking.commons.core.Messages.PROMPT_MESSAGE_EXIT_PROMPT;
import static seedu.booking.commons.core.Messages.PROMPT_MESSAGE_TRY_AGAIN;

import seedu.booking.logic.StatefulLogicManager;
import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
import seedu.booking.model.person.Email;

public class PromptBookingEmailCommand extends Command {

    private final Email email;

    public PromptBookingEmailCommand(Email email) {
        this.email = email;
    }

    public Email getEmail() {
        return email;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasPersonWithEmail(email)) {
            throw new CommandException(MESSAGE_INVALID_PERSON_DISPLAYED_EMAIL
                    + PROMPT_MESSAGE_TRY_AGAIN);
        }

        StatefulLogicManager.processStateInput(email);
        StatefulLogicManager.setNextState();
        return new CommandResult(StatefulLogicManager.getNextPromptMessage() + PROMPT_MESSAGE_EXIT_PROMPT);
    }

}
