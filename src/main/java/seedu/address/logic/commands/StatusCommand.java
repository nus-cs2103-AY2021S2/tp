package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RESIDENCES;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.residence.Booking;
import seedu.address.model.residence.Residence;
import seedu.address.model.residence.ResidenceAddress;
import seedu.address.model.residence.ResidenceName;
import seedu.address.model.tag.CleanStatusTag;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing residence in the residence tracker.
 */
public class StatusCommand extends Command {

    public static final String COMMAND_WORD = "status";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": updates multiple residences clean status  "
            + "by the index number used in the displayed residence list. \n"
            + "Parameters: status (clean or unclean) "
            + "INDEX1 INDEX2... (must be positive integers) "
            + "Example: " + COMMAND_WORD
            + " clean" + " 1 2 4 ";

    public static final String MESSAGE_STATUS_RESIDENCE_SUCCESS = "Residences with updated status: %1$s";
    public static final String MESSAGE_ERROR_STATUS = "Must input a correct clean status (clean/unclean)";
    public static final String MESSAGE_NOT_STATUS = "At least one residence index to update status must be provided.";

    private final ArrayList<Index> indexArray;
    private final String status;

    /**
     * @param indexArray of the residences in the filtered residence list to update clean status
     */
    public StatusCommand(ArrayList<Index> indexArray , String status) {
        requireNonNull(indexArray);
        requireNonNull(status);
        this.indexArray = indexArray;
        this.status = status;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Residence> lastShownList = model.getFilteredResidenceList();
        String updatedResidenceArrayString = "";
        for (Index index : indexArray) {
            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_RESIDENCE_DISPLAYED_INDEX);
            }
            Residence residenceToUpdateStatus = lastShownList.get(index.getZeroBased());
            Residence updatedResidence = createUpdatedResidence(residenceToUpdateStatus, status);
            model.setResidence(residenceToUpdateStatus, updatedResidence);
            updatedResidenceArrayString += updatedResidence.toString() + "\n";
        }

        model.updateFilteredResidenceList(PREDICATE_SHOW_ALL_RESIDENCES);
        return new CommandResult(String.format(MESSAGE_STATUS_RESIDENCE_SUCCESS, updatedResidenceArrayString));
    }

    /**
     * Creates and returns a {@code Residence} with the details of {@code residenceToEdit}
     * edited with {@code editResidenceDescriptor}.
     */
    private static Residence createUpdatedResidence(Residence residenceToUpdateStatus,
                                                   String status) throws CommandException {
        assert residenceToUpdateStatus != null;

        ResidenceName updatedName = residenceToUpdateStatus.getResidenceName();
        ResidenceAddress updatedAddress = residenceToUpdateStatus.getResidenceAddress();
        Booking updatedBooking = residenceToUpdateStatus.getBookingDetails();

        CleanStatusTag updatedCleanStatus;
        if (status.equals("clean")) {
            updatedCleanStatus = new CleanStatusTag("y");
        } else if (status.equals("unclean")) {
            updatedCleanStatus = new CleanStatusTag("n");
        } else {
            throw new CommandException(MESSAGE_ERROR_STATUS);
        }
        Set<Tag> updatedTags = residenceToUpdateStatus.getTags();

        return new Residence(updatedName, updatedAddress, updatedBooking, updatedCleanStatus, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StatusCommand)) {
            return false;
        }

        // state check
        StatusCommand e = (StatusCommand) other;
        return indexArray.equals(e.indexArray)
                && status.equals(e.status);
    }

}
