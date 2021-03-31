package seedu.smartlib.logic.commands;

import static seedu.smartlib.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.smartlib.logic.commands.CommandTestUtil.showReaderAtIndex;
import static seedu.smartlib.testutil.TypicalIndexes.INDEX_FIRST_READER;
import static seedu.smartlib.testutil.TypicalModels.getTypicalSmartLib;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.smartlib.model.Model;
import seedu.smartlib.model.ModelManager;
import seedu.smartlib.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListReaderCommand.
 */
public class ListReaderCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSmartLib(), new UserPrefs());
        expectedModel = new ModelManager(model.getSmartLib(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListReaderCommand(), model, ListReaderCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showReaderAtIndex(model, INDEX_FIRST_READER);
        assertCommandSuccess(new ListReaderCommand(), model, ListReaderCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
