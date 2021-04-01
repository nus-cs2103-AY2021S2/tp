package seedu.iscam.logic.commands;

import static seedu.iscam.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.iscam.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.iscam.testutil.TypicalClients.getTypicalLocationBook;
import static seedu.iscam.testutil.TypicalMeetings.getTypicalMeetingBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.iscam.model.Model;
import seedu.iscam.model.ModelManager;
import seedu.iscam.model.UserPrefs;
import seedu.iscam.model.client.Client;
import seedu.iscam.testutil.ClientBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalLocationBook(), getTypicalMeetingBook(), new UserPrefs());
    }

    @Test
    public void execute_newClient_success() {
        Client validClient = new ClientBuilder().build();

        Model expectedModel = new ModelManager(model.getClientBook(), model.getMeetingBook(), new UserPrefs());
        expectedModel.addClient(validClient);

        assertCommandSuccess(new AddCommand(validClient), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validClient), expectedModel);
    }

    @Test
    public void execute_duplicateClient_throwsCommandException() {
        Client clientInList = model.getClientBook().getClientList().get(0);
        assertCommandFailure(new AddCommand(clientInList), model, AddCommand.MESSAGE_DUPLICATE_CLIENT);
    }

}
