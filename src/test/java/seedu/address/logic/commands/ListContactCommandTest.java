package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showContactAtIndex;
import static seedu.address.testutil.TypicalIndices.INDEX_FIRST;
import static seedu.address.testutil.TypicalTeachingAssistant.getTypicalTeachingAssistant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListContactCommand.
 */
public class ListContactCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTeachingAssistant(), new UserPrefs());
        expectedModel = new ModelManager(model.getTeachingAssistant(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListContactCommand(), model, ListContactCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showContactAtIndex(model, INDEX_FIRST);
        assertCommandSuccess(new ListContactCommand(), model, ListContactCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
