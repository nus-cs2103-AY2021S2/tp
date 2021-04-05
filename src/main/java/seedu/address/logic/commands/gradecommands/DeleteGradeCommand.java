package seedu.address.logic.commands.gradecommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.grade.Grade;

/**
 * Deletes a grade identified using it's displayed index from the grade list.
 */
public class DeleteGradeCommand extends Command {

    public static final String COMMAND_WORD = "delete_grade";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the grade identified by the index number used in the displayed grade list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_GRADE_SUCCESS = "Deleted Grade: %1$s";

    private final Index targetIndex;
    private final Grade toDelete;

    /**
     * Create {@code DeleteGradeCommand} with target index to delete.
     * @param targetIndex Target index of appointment to delete.
     */
    public DeleteGradeCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
        this.toDelete = null;
    }

    /**
     * Deletes grade if exists in grade list (Offer two ways to delete by
     * index or by grade)
     * @param model {@code Model} which the command should operate on.
     * @return Command Result indicating success or failure of delete operation
     * @throws CommandException
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Grade> lastShownList = model.getFilteredGradeList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_GRADE_DISPLAYED_INDEX);
        }

        Grade gradeToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteGrade(gradeToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_GRADE_SUCCESS, gradeToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteGradeCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteGradeCommand) other).targetIndex)); // state check
    }
}
