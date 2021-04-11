package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.session.Session;
import seedu.address.testutil.SessionBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddPersonCommand}.
 */
public class AddSessionCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newSession_success() {
        Session validSession = new SessionBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addSession(validSession);

        assertCommandSuccess(new AddSessionCommand(validSession), model,
                AddSessionCommand.MESSAGE_SUCCESS + validSession, expectedModel);
    }
}
