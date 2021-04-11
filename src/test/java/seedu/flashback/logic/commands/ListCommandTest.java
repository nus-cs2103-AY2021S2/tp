package seedu.flashback.logic.commands;

import static seedu.flashback.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.flashback.logic.commands.CommandTestUtil.showFlashcardAtIndex;
import static seedu.flashback.testutil.TypicalFlashcards.getTypicalFlashBack;
import static seedu.flashback.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.flashback.model.Model;
import seedu.flashback.model.ModelManager;
import seedu.flashback.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFlashBack(), new UserPrefs());
        expectedModel = new ModelManager(model.getFlashBack(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showFlashcardAtIndex(model, INDEX_FIRST_FLASHCARD);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
