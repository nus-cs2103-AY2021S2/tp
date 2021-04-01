package seedu.address.logic.commands.tutorcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TUTORS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all tutors in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list_tutors";

    public static final String MESSAGE_SUCCESS = "Listed all tutors";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTutorList(PREDICATE_SHOW_ALL_TUTORS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
