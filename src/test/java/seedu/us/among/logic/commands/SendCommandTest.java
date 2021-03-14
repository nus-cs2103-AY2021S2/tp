package seedu.us.among.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.us.among.testutil.Assert.assertThrows;
import static seedu.us.among.testutil.TypicalEndpoints.getTypicalEndpointList;
import static seedu.us.among.testutil.TypicalIndexes.INDEX_EIGHTH_ENDPOINT;
import static seedu.us.among.testutil.TypicalIndexes.INDEX_FIRST_ENDPOINT;
import static seedu.us.among.testutil.TypicalIndexes.INDEX_SECOND_ENDPOINT;

import org.junit.jupiter.api.Test;

import seedu.us.among.commons.util.JsonUtil;
import seedu.us.among.model.Model;
import seedu.us.among.model.ModelManager;
import seedu.us.among.model.UserPrefs;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.util.SampleDataUtil;

public class SendCommandTest {

    private final Model model = new ModelManager(getTypicalEndpointList(), new UserPrefs());
    private final Endpoint sampleEighthTypicalEndpoint = SampleDataUtil.getEighthTypicalEndpoint();

    @Test
    public void constructor_nullEndpoint_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SendCommand(null));
    }

    @Test
    public void execute_endpoint_sendSuccessful() throws Exception {
        SendCommand standardCommand = new SendCommand(INDEX_EIGHTH_ENDPOINT);
        CommandResult commandResult = standardCommand.execute(model);
        String expectedResponse = JsonUtil.toPrettyPrintJsonString(
                sampleEighthTypicalEndpoint.getResponse().getResponseEntity());
        assertEquals(expectedResponse,
                commandResult.getFeedbackToUser());
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
