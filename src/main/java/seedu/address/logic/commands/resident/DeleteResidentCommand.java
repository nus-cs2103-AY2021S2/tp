package seedu.address.logic.commands.resident;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.resident.Resident;

/**
 * Deletes a resident identified using it's displayed index from the address book.
 */
public class DeleteResidentCommand extends Command {

    public static final String COMMAND_WORD = "rdel";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the resident identified by the index number used in the displayed resident list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_RESIDENT_SUCCESS = "Deleted Resident: %1$s";

    private final Index targetIndex;

    public DeleteResidentCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Resident> lastShownList = model.getFilteredResidentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RESIDENT_DISPLAYED_INDEX);
        }

        Resident residentToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteResident(residentToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_RESIDENT_SUCCESS, residentToDelete)).setResidentCommand();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteResidentCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteResidentCommand) other).targetIndex)); // state check
    }
}
