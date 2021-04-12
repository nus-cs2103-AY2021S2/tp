package seedu.us.among.logic.commands;

import static seedu.us.among.logic.commands.CommandTestUtil.assertAddCommandSuccess;
import static seedu.us.among.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.us.among.testutil.TypicalEndpoints.getTypicalEndpointList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.us.among.model.Model;
import seedu.us.among.model.ModelManager;
import seedu.us.among.model.UserPrefs;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.testutil.EndpointBuilder;

/**
 * Contains integration tests (interaction with the Model) for
 * {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalEndpointList(), new UserPrefs());
    }

    @Test
    public void execute_newEndpoint_success() {
        Endpoint validEndpoint = new EndpointBuilder().build();

        Model expectedModel = new ModelManager(model.getEndpointList(), new UserPrefs());
        expectedModel.addEndpoint(validEndpoint);

        assertAddCommandSuccess(new AddCommand(validEndpoint), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validEndpoint), expectedModel, validEndpoint);
    }

    @Test
    public void execute_duplicateEndpoint_throwsCommandException() {
        Endpoint endpointInList = model.getEndpointList().getEndpointList().get(0);
        assertCommandFailure(new AddCommand(endpointInList), model, AddCommand.MESSAGE_DUPLICATE_ENDPOINT);
    }

}
