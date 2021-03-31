package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;
import seedu.booking.model.booking.StartTime;

public class BookingStartPromptCommand extends Command {

    private final StartTime startTime;

    public BookingStartPromptCommand(StartTime startTime) {
        this.startTime = startTime;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ModelManager.processStateInput(startTime);
        ModelManager.setNextState();
        return new CommandResult(ModelManager.getNextPromptMessage());
    }
}
