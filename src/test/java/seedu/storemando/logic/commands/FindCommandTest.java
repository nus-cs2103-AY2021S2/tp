package seedu.storemando.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.storemando.commons.core.Messages.MESSAGE_ITEMS_LISTED_OVERVIEW;
import static seedu.storemando.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.storemando.testutil.TypicalItems.OIL;
import static seedu.storemando.testutil.TypicalItems.NESCAFE;
import static seedu.storemando.testutil.TypicalItems.LIPTON;
import static seedu.storemando.testutil.TypicalItems.getTypicalStoreMando;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.storemando.model.Model;
import seedu.storemando.model.ModelManager;
import seedu.storemando.model.UserPrefs;
import seedu.storemando.model.item.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private final Model model = new ModelManager(getTypicalStoreMando(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalStoreMando(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
            new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
            new NameContainsKeywordsPredicate(Collections.singletonList("second"));

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
    public void execute_zeroKeywords_noItemFound() {
        String expectedMessage = String.format(MESSAGE_ITEMS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredItemList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredItemList());
    }

    @Test
    public void execute_multipleKeywords_multipleItemsFound() {
        String expectedMessage = String.format(MESSAGE_ITEMS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Oil Powder Tea");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredItemList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(OIL, NESCAFE, LIPTON), model.getFilteredItemList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
