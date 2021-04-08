package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Chim;
import seedu.address.model.Model;

/**
 * Clears CHIM and all saved data.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "All data in saved file and CHIM has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setChim(new Chim());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
