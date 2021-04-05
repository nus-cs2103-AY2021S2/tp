package seedu.heymatez.logic.commands;

import static seedu.heymatez.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.heymatez.testutil.TypicalPersons.getTypicalHeyMatez;

import org.junit.jupiter.api.Test;

import seedu.heymatez.model.HeyMatez;
import seedu.heymatez.model.Model;
import seedu.heymatez.model.ModelManager;
import seedu.heymatez.model.UserPrefs;

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
