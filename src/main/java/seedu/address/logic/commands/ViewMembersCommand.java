package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;

/**
 * Lists all persons in HeyMatez to the user.
 */
public class ViewMembersCommand extends Command {

    public static final String COMMAND_WORD = "viewMembers";

    public static final String MESSAGE_SUCCESS = "Listed all members in this CCA!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
