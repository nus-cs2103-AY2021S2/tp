package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.core.Messages.MESSAGE_DUPLICATE_PERSON_DISPLAYED_PHONE;
import static seedu.booking.commons.core.Messages.PROMPT_MESSAGE_EXIT_PROMPT;
import static seedu.booking.commons.core.Messages.PROMPT_MESSAGE_TRY_AGAIN;
import static seedu.booking.logic.commands.CommandShowType.COMMAND_SHOW_PERSONS;

import seedu.booking.logic.StatefulLogicManager;
import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
import seedu.booking.model.person.Phone;

public class PromptPersonPhoneCommand extends Command {
    private final Phone phone;

    public PromptPersonPhoneCommand(Phone phone) {
        this.phone = phone;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPersonWithPhone(phone)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON_DISPLAYED_PHONE
                    + PROMPT_MESSAGE_TRY_AGAIN);
        }

        StatefulLogicManager.processStateInput(phone);
        StatefulLogicManager.setNextState();
        return new CommandResult(StatefulLogicManager.getNextPromptMessage() + PROMPT_MESSAGE_EXIT_PROMPT,
                COMMAND_SHOW_PERSONS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PromptPersonPhoneCommand // instanceof handles nulls
                && phone.equals(((PromptPersonPhoneCommand) other).phone));
    }
}
