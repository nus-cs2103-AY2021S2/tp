package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.core.Messages.*;

import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;
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

        ModelManager.processStateInput(phone);
        ModelManager.setNextState();
        return new CommandResult(ModelManager.getNextPromptMessage() + PROMPT_MESSAGE_EXIT_PROMPT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PromptPersonPhoneCommand // instanceof handles nulls
                && phone.equals(((PromptPersonPhoneCommand) other).phone));
    }
}
