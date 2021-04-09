package seedu.smartlib.logic.commands;

import static seedu.smartlib.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.smartlib.logic.commands.CommandTestUtil.showOverdueBook;
import static seedu.smartlib.testutil.TypicalModels.getTypicalSmartLib;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.smartlib.model.Model;
import seedu.smartlib.model.ModelManager;
import seedu.smartlib.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListOverdueBookCommand.
 */
public class ListOverdueBookCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSmartLib(), new UserPrefs());
        expectedModel = new ModelManager(model.getSmartLib(), new UserPrefs());
    }

    @Test
    public void execute_listIsFiltered_showsOverdueBookOnly() {
        showOverdueBook(expectedModel);
        assertCommandSuccess(new ListOverdueBookCommand(), model,
                ListOverdueBookCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
