package seedu.storemando.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.storemando.model.Model;
import seedu.storemando.model.StoreMando;

/**
 * Clears the storemando.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Inventory has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setStoreMando(new StoreMando());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
