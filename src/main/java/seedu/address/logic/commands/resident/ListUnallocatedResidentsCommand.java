package seedu.address.logic.commands.resident;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.resident.ResidentUnallocatedPredicate;

/**
 * Lists all unallocated residents in the address book to the user.
 */
public class ListUnallocatedResidentsCommand extends Command {

    public static final String COMMAND_WORD = "rulist";

    public static final String MESSAGE_SUCCESS = "Listed all unallocated residents";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ResidentUnallocatedPredicate predicate = new ResidentUnallocatedPredicate();
        model.updateFilteredResidentList(predicate);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
