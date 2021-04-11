package seedu.storemando.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.storemando.commons.core.Messages.MESSAGE_NO_ITEM_IN_LIST;
import static seedu.storemando.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.storemando.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.storemando.logic.commands.CommandTestUtil.showEmptyListAfterFind;
import static seedu.storemando.logic.commands.CommandTestUtil.showItemAtIndex;
import static seedu.storemando.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.storemando.testutil.TypicalItems.HEATER;
import static seedu.storemando.testutil.TypicalItems.getTypicalStoreMando;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.storemando.model.Model;
import seedu.storemando.model.ModelManager;
import seedu.storemando.model.UserPrefs;
import seedu.storemando.model.item.LocationContainsKeywordsPredicate;
import seedu.storemando.model.tag.TagContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalStoreMando(), new UserPrefs());
        expectedModel = new ModelManager(model.getStoreMando(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showItemAtIndex(model, INDEX_FIRST_ITEM);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equalsPartialKeywords() {
        LocationContainsKeywordsPredicate firstPredicate =
            new LocationContainsKeywordsPredicate(Arrays.asList("first"));

        LocationContainsKeywordsPredicate secondPredicate =
            new LocationContainsKeywordsPredicate(Arrays.asList("second"));

        ListCommand findFirstCommand = new ListCommand(firstPredicate, Arrays.asList("first"));
        ListCommand findSecondCommand = new ListCommand(secondPredicate, Arrays.asList("second"));

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        ListCommand findFirstCommandCopy = new ListCommand(firstPredicate, Arrays.asList("first"));
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different item -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_noItemInList_listLocation() {
        showEmptyListAfterFind(model, HEATER);
        LocationContainsKeywordsPredicate predicate = new LocationContainsKeywordsPredicate(Arrays.asList("Kitchen"));

        assertCommandFailure(new ListCommand(predicate, Arrays.asList("Kitchen")), model, MESSAGE_NO_ITEM_IN_LIST);
    }

    @Test
    public void execute_noItemInList_listTag() {
        showEmptyListAfterFind(model, HEATER);
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Arrays.asList("favourite"));

        assertCommandFailure(new ListCommand(predicate, Arrays.asList("favourite")), model, MESSAGE_NO_ITEM_IN_LIST);
    }
}
