package seedu.us.among.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.us.among.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.us.among.logic.commands.CommandTestUtil.showEndpointAtIndex;
import static seedu.us.among.testutil.Assert.assertThrows;
import static seedu.us.among.testutil.TypicalEndpoints.getTypicalEndpointList;
import static seedu.us.among.testutil.TypicalIndexes.INDEX_FIRST_ENDPOINT;
import static seedu.us.among.testutil.TypicalIndexes.INDEX_SECOND_ENDPOINT;

import org.junit.jupiter.api.Test;

import seedu.us.among.commons.core.Messages;
import seedu.us.among.commons.core.index.Index;
import seedu.us.among.logic.commands.exceptions.CommandException;
import seedu.us.among.model.Model;
import seedu.us.among.model.ModelManager;
import seedu.us.among.model.UserPrefs;

public class ShowCommandTest {

    private Model model = new ModelManager(getTypicalEndpointList(), new UserPrefs());

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ShowCommand(null));
    }

    @Test
    public void constructor_validIndex_success() {
        new ShowCommand(INDEX_FIRST_ENDPOINT);
    }

    @Test
    public void execute_outOfBoundEndpointIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEndpointList().size() + 1);
        ShowCommand showCommand = new ShowCommand(outOfBoundIndex);
        assertCommandFailure(showCommand, model, Messages.MESSAGE_INVALID_ENDPOINT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_outOfBoundEndpointIndexFilteredList_failure() {
        showEndpointAtIndex(model, INDEX_FIRST_ENDPOINT);
        Index outOfBoundIndex = INDEX_SECOND_ENDPOINT;
        // ensures that outOfBoundIndex is still in bounds of API endpoint list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getEndpointList().getEndpointList().size());

        assertCommandFailure(new ShowCommand(outOfBoundIndex), model,
                Messages.MESSAGE_INVALID_ENDPOINT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validEndpointIndex_success() throws CommandException {
        ShowCommand showCommand = new ShowCommand(Index.fromZeroBased(0));
        assertEquals(model.getEndpointList().getEndpointList().get(0).toString(),
                showCommand.execute(model).getFeedbackToUser());
    }

    @Test
    public void equals() {
        final ShowCommand standardCommand = new ShowCommand(INDEX_FIRST_ENDPOINT);

        // same values -> returns true
        ShowCommand commandWithSameValues = new ShowCommand(INDEX_FIRST_ENDPOINT);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new ShowCommand(INDEX_SECOND_ENDPOINT)));
    }

}
