package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_MAIN_PATIENTS;

import seedu.address.model.Model;

/**
 * Lists all patients in the main patient list in DocBob to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Hey Doc, here are your patients!";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows a list of all your saved patient contacts.\n"
            + "Parameters: None\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_MAIN_PATIENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
