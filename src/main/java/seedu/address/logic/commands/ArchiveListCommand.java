package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ARCHIVED_PATIENTS;

import seedu.address.model.Model;

/**
 * Lists all archived patients in DocBob to the user.
 */
public class ArchiveListCommand extends Command {

    public static final String COMMAND_WORD = "archivelist";

    public static final String MESSAGE_SUCCESS = "Hey Doc, here are your archived patients!";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows a list of all your archived patient contacts.\n"
            + "Parameters: None\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ARCHIVED_PATIENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
