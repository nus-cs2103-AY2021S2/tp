package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOOKING1;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showResidenceAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_BOOKING;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_RESIDENCE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_RESIDENCE;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_RESIDENCE;
import static seedu.address.testutil.TypicalResidences.getTypicalResidenceTracker;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.EditBookingDescriptorBuilder;

public class EditBookingCommandTest {

    private Model model = new ModelManager(getTypicalResidenceTracker(), new UserPrefs());

    @Test
    public void execute_validResidenceIndexInvalidBookingIndexFilteredList_failure() {
        showResidenceAtIndex(model, INDEX_FIRST_RESIDENCE);
        Index outOfBoundBookingIndex = INDEX_FIRST_BOOKING;
        EditBookingCommand editBookingCommand = new EditBookingCommand(INDEX_FIRST_RESIDENCE, outOfBoundBookingIndex,
                new EditBookingDescriptorBuilder().withName(VALID_NAME_BOOKING1).build());
        assertCommandFailure(editBookingCommand, model, Messages.MESSAGE_INVALID_BOOKING_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidResidenceIndexValidBookingIndexFilteredList_failure() {
        showResidenceAtIndex(model, INDEX_SECOND_RESIDENCE);
        Index outOfBoundResidenceIndex = INDEX_THIRD_RESIDENCE;
        assertTrue(outOfBoundResidenceIndex.getZeroBased() < model.getResidenceTracker().getResidenceList().size());
        Index validBookingIndex = INDEX_FIRST_BOOKING;
        assertTrue(outOfBoundResidenceIndex.getZeroBased() < model.getResidenceTracker().getResidenceList().size());
        EditBookingCommand editBookingCommand = new EditBookingCommand(INDEX_SECOND_RESIDENCE, validBookingIndex,
                new EditBookingDescriptorBuilder().withName(VALID_NAME_BOOKING1).build());
        assertCommandFailure(editBookingCommand, model, Messages.MESSAGE_INVALID_RESIDENCE_DISPLAYED_INDEX);

    }

}
