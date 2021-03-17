package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.CakeCollate;
import seedu.address.model.Model;

/**
 * Clears the cakecollate.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "CakeCollate has been cleared!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clears all entries from CakeCollate.";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setCakeCollate(new CakeCollate());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
