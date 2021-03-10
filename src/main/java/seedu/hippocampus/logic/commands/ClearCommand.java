package seedu.hippocampus.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.hippocampus.model.AddressBook;
import seedu.hippocampus.model.Model;

/**
 * Clears HippoCampus.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "HippoCampus has been cleared!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clears HippoCampus. ";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
