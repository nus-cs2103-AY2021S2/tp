package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RESIDENCES;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_RESIDENCE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SIXTH_RESIDENCE;
import static seedu.address.testutil.TypicalResidences.getTypicalResidenceTracker;

import java.util.ArrayList;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ResidenceTracker;
import seedu.address.model.UserPrefs;
import seedu.address.model.residence.Booking;
import seedu.address.model.residence.Residence;
import seedu.address.model.residence.ResidenceAddress;
import seedu.address.model.residence.ResidenceName;
import seedu.address.model.tag.CleanStatusTag;
import seedu.address.model.tag.Tag;

public class StatusCommandTest {
    private Model model = new ModelManager(getTypicalResidenceTracker(), new UserPrefs());
    @Test
    public void execute_updateMultipleResidenceStatus_cleanSuccess() throws CommandException {
        ArrayList<Index> indexArray = new ArrayList<>();
        indexArray.add(INDEX_SECOND_RESIDENCE);
        indexArray.add(INDEX_SIXTH_RESIDENCE);
        String cleanStatus = "clean";
        StatusCommand statusCommand = new StatusCommand(indexArray, cleanStatus);
        Model expectedModel = new ModelManager(new ResidenceTracker(model.getResidenceTracker()), new UserPrefs());
        String expectedMessage = "";

        for (Index index : indexArray) {
            Residence residenceToUpdateStatus = model.getFilteredResidenceList().get(index.getZeroBased());
            ResidenceName updatedName = residenceToUpdateStatus.getResidenceName();
            ResidenceAddress updatedAddress = residenceToUpdateStatus.getResidenceAddress();
            Booking updatedBooking = residenceToUpdateStatus.getBookingDetails();
            CleanStatusTag updatedCleanStatus;
            if (cleanStatus.equals("clean")) {
                updatedCleanStatus = new CleanStatusTag("y");
            } else if (cleanStatus.equals("unclean")) {
                updatedCleanStatus = new CleanStatusTag("n");
            } else {
                throw new CommandException(StatusCommand.MESSAGE_ERROR_STATUS);
            }
            Set<Tag> updatedTags = residenceToUpdateStatus.getTags();
            Residence updatedResidence = new Residence(updatedName, updatedAddress,
                    updatedBooking, updatedCleanStatus, updatedTags);
            expectedModel.setResidence(residenceToUpdateStatus, updatedResidence);
            expectedMessage += updatedResidence.toString() + "\n";
        }
        expectedModel.updateFilteredResidenceList(PREDICATE_SHOW_ALL_RESIDENCES);
        expectedMessage = String.format(StatusCommand.MESSAGE_STATUS_RESIDENCE_SUCCESS, expectedMessage);
        assertCommandSuccess(statusCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidResidenceIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredResidenceList().size() + 1);
        ArrayList<Index> indexArray = new ArrayList<>();
        indexArray.add(outOfBoundIndex);
        StatusCommand statusCommand = new StatusCommand(indexArray, "clean");
        assertCommandFailure(statusCommand, model, Messages.MESSAGE_INVALID_RESIDENCE_DISPLAYED_INDEX);
    }
}
