package seedu.booking.logic.commands;

import static seedu.booking.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.booking.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.booking.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.booking.testutil.TypicalPersons.getTypicalBookingSystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;
import seedu.booking.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalBookingSystem(), new UserPrefs());
        expectedModel = new ModelManager(model.getBookingSystem(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListPersonCommand(), model, ListPersonCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListPersonCommand(), model, ListPersonCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
