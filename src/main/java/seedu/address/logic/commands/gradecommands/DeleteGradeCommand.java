package seedu.address.logic.commands.gradecommands;

import static java.util.Objects.requireNonNull;

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
    public static final String MESSAGE_DELETE_GRADE_FAILURE = "Grade does"
            + " not exists in grade list.";

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
     * Create {@code DeleteGradeCommand} with {@code Grade} to delete.
     * @param toDelete Grade to delete.
     */
    public DeleteGradeCommand(Grade toDelete) {
        requireNonNull(toDelete);
        this.targetIndex = null;
        this.toDelete = toDelete;
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

        // Delete by grade
        if (targetIndex == null) {
            if (model.hasGrade(toDelete)) {
                model.deleteGrade(toDelete);
                return new CommandResult(MESSAGE_DELETE_GRADE_SUCCESS);
            } else {
                return new CommandResult(MESSAGE_DELETE_GRADE_FAILURE);
            }
        } else {
            // Delete by index
            try {
                model.removeGradeIndex(targetIndex.getZeroBased());
                return new CommandResult(MESSAGE_DELETE_GRADE_SUCCESS);
            } catch (IndexOutOfBoundsException e) {
                return new CommandResult(MESSAGE_DELETE_GRADE_FAILURE);
            }
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteGradeCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteGradeCommand) other).targetIndex)); // state check
    }
}
