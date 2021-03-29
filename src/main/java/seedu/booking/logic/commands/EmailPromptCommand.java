package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_EMAIL;
import static seedu.booking.commons.core.Messages.MESSAGE_PROMPT_TRYAGAIN;

import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;
import seedu.booking.model.person.Email;

public class EmailPromptCommand extends Command {

    private final Email email;

    public EmailPromptCommand(Email email) {
        this.email = email;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasPersonWithEmail(email)) {
            throw new CommandException(MESSAGE_INVALID_PERSON_DISPLAYED_EMAIL
                    + MESSAGE_PROMPT_TRYAGAIN);
        }


        ModelManager.processStateInput(email);
        ModelManager.setNextState();
        return new CommandResult(ModelManager.getNextPromptMessage());
    }
}
