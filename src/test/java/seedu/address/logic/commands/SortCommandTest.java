package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.SORT_ASCENDING;
import static seedu.address.logic.commands.CommandTestUtil.SORT_DESCENDING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORT_DIRECTION_ASCENDING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORT_DIRECTION_DESCENDING;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getUnsortedTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SortCommand.
 */
public class SortCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getUnsortedTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_sortListInAscendingOrder_success() {
        String expectedMessage = String.format(SortCommand.MESSAGE_SORT_SUCCESS,
                VALID_SORT_DIRECTION_ASCENDING);
        assertCommandSuccess(new SortCommand(SORT_ASCENDING), model,
                expectedMessage, expectedModel);
    }

    @Test void execute_sortListInDescendingOrder_success() {
        String expectedMessage = String.format(SortCommand.MESSAGE_SORT_SUCCESS,
                VALID_SORT_DIRECTION_DESCENDING);
        expectedModel.sortByName(false);
        assertCommandSuccess(new SortCommand(SORT_DESCENDING), model,
                expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        SortCommand ascendingSortCommand = new SortCommand(SORT_ASCENDING);
        SortCommand descendingSortCommand = new SortCommand(SORT_DESCENDING);

        // same object -> returns true
        assertTrue(ascendingSortCommand.equals(ascendingSortCommand));

        // same values -> returns true
        SortCommand ascendingSortCommandCopy = new SortCommand(SORT_ASCENDING);
        assertTrue(ascendingSortCommand.equals(ascendingSortCommandCopy));

        // different types -> returns false
        assertFalse(ascendingSortCommand.equals(1));

        // null -> returns false
        assertFalse(ascendingSortCommand.equals(null));

        // different sort direction -> returns false
        assertFalse(ascendingSortCommand.equals(descendingSortCommand));
    }
}
