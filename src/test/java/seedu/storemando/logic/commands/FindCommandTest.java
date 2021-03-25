package seedu.storemando.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.storemando.commons.core.Messages.MESSAGE_MORE_THAN_ONE_ITEM_LISTED_OVERVIEW;
import static seedu.storemando.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.storemando.testutil.TypicalItems.CREAM;
import static seedu.storemando.testutil.TypicalItems.EGGS;
import static seedu.storemando.testutil.TypicalItems.FLOUR;
import static seedu.storemando.testutil.TypicalItems.getTypicalStoreMando;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.storemando.model.Model;
import seedu.storemando.model.ModelManager;
import seedu.storemando.model.UserPrefs;
import seedu.storemando.model.item.ItemNameContainsKeywordsPredicate;
import seedu.storemando.model.item.ItemNameContainsPartialKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private final Model model = new ModelManager(getTypicalStoreMando(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalStoreMando(), new UserPrefs());

    @Test
    public void equalsKeywords() {
        ItemNameContainsKeywordsPredicate firstPredicate =
            new ItemNameContainsKeywordsPredicate(Collections.singletonList("first"));
        ItemNameContainsKeywordsPredicate secondPredicate =
            new ItemNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different item -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void equalsPartialKeywords() {
        ItemNameContainsPartialKeywordsPredicate firstPredicate =
            new ItemNameContainsPartialKeywordsPredicate(Collections.singletonList("first"));
        ItemNameContainsPartialKeywordsPredicate secondPredicate =
            new ItemNameContainsPartialKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different item -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_multipleKeywords_multipleItemsFound() {
        String expectedMessage = String.format(MESSAGE_MORE_THAN_ONE_ITEM_LISTED_OVERVIEW, 3);
        ItemNameContainsKeywordsPredicate predicate = preparePredicate("Cream Eggs Flour");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredItemList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CREAM, EGGS, FLOUR), model.getFilteredItemList());
    }

    /**
     * Parses {@code userInput} into a {@code ItemNameContainsKeywordsPredicate}.
     */
    private ItemNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new ItemNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
