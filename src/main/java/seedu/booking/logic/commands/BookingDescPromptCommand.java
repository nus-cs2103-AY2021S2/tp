package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;
import seedu.booking.model.booking.Description;

public class BookingDescPromptCommand extends Command {

    private final Description description;

    public BookingDescPromptCommand(Description description) {
        this.description = description;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ModelManager.processStateInput(description);
        ModelManager.setNextState();
        return new CommandResult(ModelManager.getNextPromptMessage());
    }
}
