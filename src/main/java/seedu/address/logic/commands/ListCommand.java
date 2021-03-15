package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RESIDENCES;

import seedu.address.model.Model;

/**
 * Lists all residences in the residence tracker to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all residences";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredResidenceList(PREDICATE_SHOW_ALL_RESIDENCES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
