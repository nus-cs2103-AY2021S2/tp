package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;

public class PromptProcessCommand extends Command {

    private final Object value;

    public PromptProcessCommand(Object value) {
        this.value = value;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ModelManager.setNextState();
        ModelManager.processStateInput(value);
        return new CommandResult(ModelManager.getNextPromptMessage());
    }

}
