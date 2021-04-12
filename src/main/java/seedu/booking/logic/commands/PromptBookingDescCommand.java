package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.core.Messages.PROMPT_MESSAGE_EXIT_PROMPT;
import static seedu.booking.logic.commands.CommandShowType.COMMAND_SHOW_BOOKINGS;

import seedu.booking.logic.StatefulLogicManager;
import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
import seedu.booking.model.booking.Description;

public class PromptBookingDescCommand extends Command {

    private final Description description;

    public PromptBookingDescCommand(Description description) {
        this.description = description;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        StatefulLogicManager.processStateInput(description);
        StatefulLogicManager.setNextState();
        return new CommandResult(StatefulLogicManager.getNextPromptMessage() + PROMPT_MESSAGE_EXIT_PROMPT,
                COMMAND_SHOW_BOOKINGS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PromptBookingDescCommand // instanceof handles nulls
                && description.equals(((PromptBookingDescCommand) other).description));
    }

}
