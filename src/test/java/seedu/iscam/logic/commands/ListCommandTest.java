package seedu.iscam.logic.commands;

import static seedu.iscam.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.iscam.logic.commands.CommandTestUtil.showClientAtIndex;
import static seedu.iscam.testutil.TypicalClients.getTypicalLocationBook;
import static seedu.iscam.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.iscam.testutil.TypicalMeetings.getTypicalMeetingBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.iscam.model.Model;
import seedu.iscam.model.ModelManager;
import seedu.iscam.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalLocationBook(), getTypicalMeetingBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getClientBook(), model.getMeetingBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showClientAtIndex(model, INDEX_FIRST_CLIENT);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
