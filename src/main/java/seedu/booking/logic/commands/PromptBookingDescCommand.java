package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.core.Messages.PROMPT_MESSAGE_EXIT_PROMPT;
import static seedu.booking.logic.commands.CommandShowType.COMMAND_SHOW_BOOKINGS;

import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;
import seedu.booking.model.booking.Description;

public class PromptBookingDescCommand extends Command {

    private final Description description;

    public PromptBookingDescCommand(Description description) {
        this.description = description;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ModelManager.processStateInput(description);
        ModelManager.setNextState();
        return new CommandResult(ModelManager.getNextPromptMessage() + PROMPT_MESSAGE_EXIT_PROMPT,
                COMMAND_SHOW_BOOKINGS);
    }
}
