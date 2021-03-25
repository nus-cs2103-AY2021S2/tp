package seedu.address.logic.commands.resident;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RESIDENTS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListResidentCommand extends Command {

    public static final String COMMAND_WORD = "rlist";

    public static final String MESSAGE_SUCCESS = "Listed all persons";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredResidentList(PREDICATE_SHOW_ALL_RESIDENTS);
        return new CommandResult(MESSAGE_SUCCESS).setResidentCommand();
    }
}
