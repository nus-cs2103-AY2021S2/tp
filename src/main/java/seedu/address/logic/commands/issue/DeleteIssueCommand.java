package seedu.address.logic.commands.issue;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.issue.Issue;

/**
 * Deletes a issue identified using it's displayed index from the address book.
 */
public class DeleteIssueCommand extends Command {

    public static final String COMMAND_WORD = "idel";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the issue identified by the index number used in the displayed issue list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ISSUE_SUCCESS = "Deleted Issue: %1$s";

    private final Index targetIndex;

    public DeleteIssueCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Issue> lastShownList = model.getFilteredIssueList();

        if (lastShownList.size() == 0) {
            throw new CommandException(Messages.MESSAGE_NO_ISSUES);
        }

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(
                    String.format(Messages.MESSAGE_INVALID_ISSUE_DISPLAYED_INDEX, lastShownList.size()));
        }

        Issue issueToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteIssue(issueToDelete);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_DELETE_ISSUE_SUCCESS, issueToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteIssueCommand // instanceof handles nulls
                        && targetIndex.equals(((DeleteIssueCommand) other).targetIndex)); // state check
    }
}
