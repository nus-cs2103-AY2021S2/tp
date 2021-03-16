package seedu.address.logic.commands.issue;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ISSUES;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all issues in the address book to the user.
 */
public class ListIssueCommand extends Command {

    public static final String COMMAND_WORD = "ilist";

    public static final String MESSAGE_SUCCESS = "Listed all issues";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredIssueList(PREDICATE_SHOW_ALL_ISSUES);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
