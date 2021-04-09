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

    private final Logger logger = LogsCenter.getLogger(DeleteIssueCommand.class);

    private final Index targetIndex;

    /**
     * Creates a DeleteIssueCommand to delete the specified issue at {@code targetIndex}.
     *
     * @param targetIndex Target index of the issue in the filtered issue list to delete.
     * @throws NullPointerException If {@code targetIndex} is null.
     */
    public DeleteIssueCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    /**
     * Executes an DeleteIssuecommand to delete a targeted issue.
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

        Issue issueToDelete = lastShownList.get(targetIndex.getZeroBased());
        assert issueToDelete != null;

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
