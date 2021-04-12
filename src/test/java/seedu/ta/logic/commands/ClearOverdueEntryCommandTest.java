package seedu.ta.logic.commands;

import static seedu.ta.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.ta.testutil.TypicalTeachingAssistant.getTypicalTeachingAssistant;

import org.junit.jupiter.api.Test;

import seedu.ta.model.Model;
import seedu.ta.model.ModelManager;
import seedu.ta.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code ClearOverdueEntryCommand}.
 */
public class ClearOverdueEntryCommandTest {

    @Test
    public void execute_emptyTeachingAssistant_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        assertCommandSuccess(new ClearOverdueEntryCommand(), model,
                ClearOverdueEntryCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyTeachingAssistant_success() {
        Model model = new ModelManager(getTypicalTeachingAssistant(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalTeachingAssistant(), new UserPrefs());
        expectedModel.clearOverdueEntries();
        assertCommandSuccess(new ClearOverdueEntryCommand(), model,
                ClearOverdueEntryCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
