package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.core.Messages.PROMPT_MESSAGE_EXIT_PROMPT;
import static seedu.booking.logic.commands.CommandShowType.COMMAND_SHOW_VENUES;

import seedu.booking.logic.StatefulLogicManager;
import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;

public class PromptVenueDescCommand extends Command {
    private final String description;

    public PromptVenueDescCommand(String description) {
        this.description = description;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        StatefulLogicManager.processStateInput(description);
        StatefulLogicManager.setNextState();
        return new CommandResult(StatefulLogicManager.getNextPromptMessage() + PROMPT_MESSAGE_EXIT_PROMPT,
                COMMAND_SHOW_VENUES);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PromptVenueDescCommand // instanceof handles nulls
                && this.description.equals(((PromptVenueDescCommand) other).description));
    }
}
