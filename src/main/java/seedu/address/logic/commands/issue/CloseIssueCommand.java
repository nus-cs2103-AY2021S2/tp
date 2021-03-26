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
import seedu.address.model.issue.IssueStatus;

/**
 * Closes a issue identified using it's displayed index from the address book.
 */
public class CloseIssueCommand extends Command {

    public static final String COMMAND_WORD = "icol";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Closes the issue identified by the index number used in the displayed issue list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_CLOSE_ISSUE_SUCCESS = "Closed Issue: %1$s";

    public static final String MESSAGE_CLOSE_ISSUE_CLOSED = "Issue has already been closed.";

    private final Index targetIndex;

    public CloseIssueCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Issue> lastShownList = model.getFilteredIssueList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ISSUE_DISPLAYED_INDEX);
        }

        Issue issueToClose = lastShownList.get(targetIndex.getZeroBased());

        if (issueToClose.getStatus().value == IssueStatus.Closed) {
            throw new CommandException(MESSAGE_CLOSE_ISSUE_CLOSED);
        }

        Issue closedIssue = Issue.closeIssue(issueToClose);

        model.setIssue(issueToClose, closedIssue);

        return new CommandResult(String.format(MESSAGE_CLOSE_ISSUE_SUCCESS, issueToClose));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CloseIssueCommand // instanceof handles nulls
                        && targetIndex.equals(((CloseIssueCommand) other).targetIndex)); // state check
    }
}
