package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalSessionIds.SESSION_ID_FIRST_CLASS;
import static seedu.address.testutil.TypicalSessions.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.session.SessionIdPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ViewSessionCommand.
 */
public class ViewSessionCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ViewSessionCommand(new SessionIdPredicate(SESSION_ID_FIRST_CLASS)),
                model, ViewSessionCommand.MESSAGE_SUCCESS, expectedModel);
    }
}


