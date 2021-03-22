package seedu.weeblingo.logic.commands;

import static seedu.weeblingo.testutil.TypicalFlashcards.getTypicalFlashcardBook;

import org.junit.jupiter.api.BeforeEach;

import seedu.weeblingo.model.Model;
import seedu.weeblingo.model.ModelManager;
import seedu.weeblingo.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFlashcardBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getFlashcardBook(), new UserPrefs());
    }

    //    @Test
    //    public void execute_listIsNotFiltered_showsSameList() {
    //        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    //    }

    //    @Test
    //    public void execute_listIsFiltered_showsEverything() {
    //        showFlashcardAtIndex(model, INDEX_FIRST_FLASHCARD);
    //        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    //    }
}
