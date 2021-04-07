package dog.pawbook.logic.commands;

import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_ENTITY_ID;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_OUT_OF_BOUNDS_ID_INTEGER;
import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static dog.pawbook.testutil.TypicalEntities.getTypicalDatabase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dog.pawbook.model.Model;
import dog.pawbook.model.ModelManager;
import dog.pawbook.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ViewCommand.
 */
public class ViewCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalDatabase(), new UserPrefs());
        expectedModel = new ModelManager(model.getDatabase(), new UserPrefs());
    }

    @Test
    public void execute_invalidId_throwsException() {
        assertCommandFailure(new ViewCommand(INVALID_OUT_OF_BOUNDS_ID_INTEGER), model, MESSAGE_INVALID_ENTITY_ID);
    }
}
