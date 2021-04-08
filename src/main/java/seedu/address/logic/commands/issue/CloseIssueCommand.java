package seedu.address.logic.commands.issue;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
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

    public static final String COMMAND_WORD = "iclo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Closes the issue identified by the index number used in the displayed issue list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_CLOSE_ISSUE_SUCCESS = "Closed Issue: %1$s";

    public static final String MESSAGE_CLOSE_ISSUE_CLOSED = "Issue has already been closed.";

    private final Logger logger = LogsCenter.getLogger(CloseIssueCommand.class);

    private final Index targetIndex;

    /**
     * Creates a CloseIssueCommand to close the specified issue at {@code targetIndex}.
     *
     * @param targetIndex Target index of the issue in the filtered issue list to close.
     * @throws NullPointerException If {@code targetIndex} is null.
     */
    public CloseIssueCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    /**
     * Executes an CloseIssuecommand to set the status of the targeted issue to Closed.
     *
     * @param model {@code Model} which the command should operate on.
     * @return Result of command execution.
     * @throws CommandException     If {@code model} is invalid.
     * @throws NullPointerException If the {@code model} is null.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Issue> lastShownList = model.getFilteredIssueList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            logger.warning("Provided index was more than current list size");
            throw new CommandException(Messages.MESSAGE_INVALID_ISSUE_DISPLAYED_INDEX);
        }

        Issue issueToClose = lastShownList.get(targetIndex.getZeroBased());
        assert issueToClose != null;

        if (issueToClose.getStatus().value == IssueStatus.Closed) {
            logger.warning("Issue to close is already closed");
            throw new CommandException(MESSAGE_CLOSE_ISSUE_CLOSED);
        }

        model.closeIssue(issueToClose);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_CLOSE_ISSUE_SUCCESS, issueToClose));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CloseIssueCommand // instanceof handles nulls
                        && targetIndex.equals(((CloseIssueCommand) other).targetIndex)); // state check
    }
}
