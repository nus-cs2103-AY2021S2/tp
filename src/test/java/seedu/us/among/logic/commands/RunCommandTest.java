package seedu.us.among.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.us.among.testutil.Assert.assertThrows;
import static seedu.us.among.testutil.TypicalEndpoints.getTypicalEndpointList;

import org.junit.jupiter.api.Test;

import seedu.us.among.commons.util.JsonUtil;
import seedu.us.among.model.Model;
import seedu.us.among.model.ModelManager;
import seedu.us.among.model.UserPrefs;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.util.SampleDataUtil;

public class RunCommandTest {

    private final Model model = new ModelManager(getTypicalEndpointList(), new UserPrefs());
    private final Endpoint[] endpoints = SampleDataUtil.getSampleEndpoint();
    private final Endpoint sampleValidEndpoint = SampleDataUtil.getSampleValidEndpoint();

    @Test
    public void constructor_nullEndpoint_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RunCommand(null));
    }

    @Test
    public void execute_endpoint_runSuccessful() throws Exception {
        RunCommand standardCommand = new RunCommand(sampleValidEndpoint);
        CommandResult commandResult = standardCommand.execute(model);
        String expectedResponse = JsonUtil.toPrettyPrintJsonString(
                sampleValidEndpoint.getResponseEntity());
        assertEquals(expectedResponse,
                commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        final RunCommand standardCommand = new RunCommand(endpoints[0]);

        // same values -> returns true
        RunCommand commandWithSameValues = new RunCommand(endpoints[0]);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new RunCommand(endpoints[1])));
    }

}
