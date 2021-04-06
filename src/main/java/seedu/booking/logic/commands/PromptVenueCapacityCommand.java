package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;
import seedu.booking.model.venue.Capacity;

public class PromptVenueCapacityCommand extends Command {
    private final Capacity capacity;

    public PromptVenueCapacityCommand(Capacity capacity) {
        this.capacity = capacity;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ModelManager.processStateInput(capacity);
        ModelManager.setNextState();
        return new CommandResult(ModelManager.getNextPromptMessage());
    }
}
