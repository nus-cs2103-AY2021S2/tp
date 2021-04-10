package seedu.address.logic.commands.resident;

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
import seedu.address.model.resident.Resident;
import seedu.address.model.residentroom.ResidentRoom;

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
    public static final String MESSAGE_RESIDENT_ALLOCATED_FAILURE =
            "The resident has been allocated to a room. Please deallocate the resident before deletion.";
    private final Logger logger = LogsCenter.getLogger(DeleteResidentCommand.class);


    private final Index targetIndex;

    public DeleteResidentCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Resident> lastShownList = model.getFilteredResidentList();

        if (lastShownList.size() == 0) {
            throw new CommandException(Messages.MESSAGE_NO_RESIDENTS);
        }

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            logger.warning("Provided index was more than current list size");
            throw new CommandException(
                    String.format(Messages.MESSAGE_INVALID_RESIDENT_DISPLAYED_INDEX, lastShownList.size()));
        }

        Resident residentToDelete = lastShownList.get(targetIndex.getZeroBased());
        if (model.hasEitherResidentRoom(new ResidentRoom(residentToDelete.getName(), null))) {
            logger.warning("The resident has been allocated to a room. Cannot be deleted.");
            throw new CommandException(MESSAGE_RESIDENT_ALLOCATED_FAILURE);
        }

        assert residentToDelete != null;
        model.deleteResident(residentToDelete);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_DELETE_RESIDENT_SUCCESS, residentToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteResidentCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteResidentCommand) other).targetIndex)); // state check
    }
}
