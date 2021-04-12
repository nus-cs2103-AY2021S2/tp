package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTeachingAssistant.getTypicalTeachingAssistant;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

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
        model.clearOverdueEntries();
        assertCommandSuccess(new ClearOverdueEntryCommand(), model,
                ClearOverdueEntryCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
