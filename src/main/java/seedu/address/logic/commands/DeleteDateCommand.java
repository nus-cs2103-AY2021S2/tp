package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.date.ImportantDate;


/**
 * Deletes an important date identified using it's displayed index from TutorsPet.
 */
public class DeleteDateCommand extends Command {

    public static final String COMMAND_WORD = "delete-date";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the important date identified by the index number used in the displayed important dates list.\n"
        + "Parameters: INDEX (must be a positive integer and lesser than 2147483648)\n"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_DATE_SUCCESS = "Deleted Date: %1$s";

    private final Index targetIndex;

    public DeleteDateCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<ImportantDate> lastShownList = model.getSortedImportantDatesList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_IMPORTANT_DATE_DISPLAYED_INDEX);
        }

        ImportantDate importantDateToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteImportantDate(importantDateToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_DATE_SUCCESS, importantDateToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteDateCommand // instanceof handles nulls
            && targetIndex.equals(((DeleteDateCommand) other).targetIndex)); // state check
    }

}
