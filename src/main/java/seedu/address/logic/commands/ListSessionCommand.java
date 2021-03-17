package seedu.address.logic.commands;

import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

/**
 * Lists all sessions in the address book to the user.
 */
public class ListSessionCommand extends Command {

    public static final String COMMAND_WORD = "list_session";

    public static final String MESSAGE_SUCCESS = "Listed all sessions";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
