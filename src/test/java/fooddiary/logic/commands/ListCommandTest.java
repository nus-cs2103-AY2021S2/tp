package fooddiary.logic.commands;

import static fooddiary.logic.commands.CommandTestUtil.assertCommandSuccess;
import static fooddiary.testutil.TypicalEntries.getTypicalFoodDiaryWithMultipleEntries;
import static fooddiary.testutil.TypicalIndexes.INDEX_FIRST_ENTRY;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fooddiary.model.Model;
import fooddiary.model.ModelManager;
import fooddiary.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFoodDiaryWithMultipleEntries(), new UserPrefs());
        expectedModel = new ModelManager(model.getFoodDiary(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        CommandTestUtil.showEntryAtIndex(model, INDEX_FIRST_ENTRY);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
