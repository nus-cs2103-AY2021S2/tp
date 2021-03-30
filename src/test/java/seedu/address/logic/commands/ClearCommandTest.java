package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalHeyMatez;

import org.junit.jupiter.api.Test;

import seedu.address.model.HeyMatez;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ClearCommand}.
 */
public class ClearCommandTest {

    @Test
    public void execute_emptyHeyMatez_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyHeyMatez_success() {
        Model model = new ModelManager(getTypicalHeyMatez(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalHeyMatez(), new UserPrefs());
        expectedModel.setHeyMatez(new HeyMatez());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
