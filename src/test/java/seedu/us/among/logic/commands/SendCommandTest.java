package seedu.us.among.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.us.among.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.us.among.logic.commands.CommandTestUtil.showEndpointAtIndex;
import static seedu.us.among.testutil.Assert.assertThrows;
import static seedu.us.among.testutil.TypicalEndpoints.getTypicalEndpointList;
import static seedu.us.among.testutil.TypicalIndexes.INDEX_EIGHTH_ENDPOINT;
import static seedu.us.among.testutil.TypicalIndexes.INDEX_FIRST_ENDPOINT;
import static seedu.us.among.testutil.TypicalIndexes.INDEX_SECOND_ENDPOINT;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import seedu.us.among.commons.core.Messages;
import seedu.us.among.commons.core.index.Index;
import seedu.us.among.commons.util.JsonUtil;
import seedu.us.among.logic.commands.exceptions.CommandException;
import seedu.us.among.logic.request.exceptions.AbortRequestException;
import seedu.us.among.logic.request.exceptions.RequestException;
import seedu.us.among.model.Model;
import seedu.us.among.model.ModelManager;
import seedu.us.among.model.UserPrefs;
import seedu.us.among.model.util.SampleDataUtil;

public class SendCommandTest {

    private final Model model = new ModelManager(getTypicalEndpointList(), new UserPrefs());
    private final String sampleValidResponse = SampleDataUtil.getSampleValidResponse();

    @Test
    public void constructor_nullEndpoint_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SendCommand(null));
    }

    @Test
    public void execute_endpoint_sendSuccessful() throws Exception {
        SendCommand standardCommand = new SendCommand(INDEX_EIGHTH_ENDPOINT);
        CommandResult commandResult = standardCommand.execute(model);
        String expectedResponse = JsonUtil.toPrettyPrintJsonString(
                sampleValidResponse);
        assertEquals(expectedResponse,
                commandResult.getFeedbackToUser());
    }

    @Test
    public void constructor_validIndex_success() {
        new SendCommand(INDEX_FIRST_ENDPOINT);
    }

    @Test
    public void execute_outOfBoundEndpointIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEndpointList().size() + 1);
        SendCommand SendCommand = new SendCommand(outOfBoundIndex);
        assertCommandFailure(SendCommand, model, Messages.MESSAGE_INVALID_ENDPOINT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_outOfBoundEndpointIndexFilteredList_failure() {
        showEndpointAtIndex(model, INDEX_FIRST_ENDPOINT);
        Index outOfBoundIndex = INDEX_SECOND_ENDPOINT;
        // ensures that outOfBoundIndex is still in bounds of API endpoint list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getEndpointList().getEndpointList().size());

        assertCommandFailure(new SendCommand(outOfBoundIndex), model,
                Messages.MESSAGE_INVALID_ENDPOINT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validEndpointIndex_success() throws CommandException, AbortRequestException,
            RequestException, IOException {
        SendCommand SendCommand = new SendCommand(Index.fromZeroBased(7));
        assertEquals(JsonUtil.toPrettyPrintJsonString(SampleDataUtil.getSampleValidResponse()),
                SendCommand.execute(model).getFeedbackToUser());
    }

    @Test
    public void equals() {
        final SendCommand standardCommand = new SendCommand(INDEX_FIRST_ENDPOINT);

        // same values -> returns true
        SendCommand commandWithSameValues = new SendCommand(INDEX_FIRST_ENDPOINT);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new SendCommand(INDEX_SECOND_ENDPOINT)));
    }

}
