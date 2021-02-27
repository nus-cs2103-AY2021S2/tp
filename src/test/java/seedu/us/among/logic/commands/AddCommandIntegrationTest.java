package seedu.us.among.logic.commands;

import static seedu.us.among.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.us.among.model.Model;
import seedu.us.among.model.ModelManager;
import seedu.us.among.model.UserPrefs;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.testutil.EndpointBuilder;
import seedu.us.among.testutil.TypicalEndpoints;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalEndpoints.getTypicalEndpointList(), new UserPrefs());
    }

    @Test
    public void execute_newEndpoint_success() {
        Endpoint validEndpoint = new EndpointBuilder().build();

        Model expectedModel = new ModelManager(model.getEndpointList(), new UserPrefs());
        expectedModel.addEndpoint(validEndpoint);

        CommandTestUtil.assertCommandSuccess(new AddCommand(validEndpoint), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validEndpoint), expectedModel);
    }

    @Test
    public void execute_duplicateEndpoint_throwsCommandException() {
        Endpoint endpointInList = model.getEndpointList().getEndpointList().get(0);
        CommandTestUtil.assertCommandFailure(new AddCommand(endpointInList), model, AddCommand.MESSAGE_DUPLICATE_ENDPOINT);
    }

}
