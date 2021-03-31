package seedu.cakecollate.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.cakecollate.commons.core.Messages.MESSAGE_ORDERS_LISTED_OVERVIEW;
import static seedu.cakecollate.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.cakecollate.testutil.TypicalOrders.CARL;
import static seedu.cakecollate.testutil.TypicalOrders.ELLE;
import static seedu.cakecollate.testutil.TypicalOrders.FIONA;
import static seedu.cakecollate.testutil.TypicalOrders.getTypicalCakeCollate;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.cakecollate.logic.parser.Prefix;
import seedu.cakecollate.model.Model;
import seedu.cakecollate.model.ModelManager;
import seedu.cakecollate.model.UserPrefs;
import seedu.cakecollate.model.order.ContainsKeywordsPredicate;
import seedu.cakecollate.testutil.TypicalOrderItems;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalCakeCollate(), new UserPrefs(),
            TypicalOrderItems.getTypicalOrderItemsModel());
    private Model expectedModel = new ModelManager(getTypicalCakeCollate(), new UserPrefs(),
            TypicalOrderItems.getTypicalOrderItemsModel());

    @Test
    public void equals() {
        HashMap<Prefix, List<String>> map1 = new HashMap<>();
        map1.put(PREFIX_NAME, Collections.singletonList("first"));
        HashMap<Prefix, List<String>> map2 = new HashMap<>();
        map1.put(PREFIX_NAME, Collections.singletonList("second"));
        ContainsKeywordsPredicate firstPredicate = new ContainsKeywordsPredicate(map1);
        ContainsKeywordsPredicate secondPredicate = new ContainsKeywordsPredicate(map2);

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

        // different order -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noOrderFound() {
        String expectedMessage = String.format(MESSAGE_ORDERS_LISTED_OVERVIEW, 0);
        ContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredOrderList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredOrderList());
    }

    @Test
    public void execute_multipleKeywords_multipleOrdersFound() {
        String expectedMessage = String.format(MESSAGE_ORDERS_LISTED_OVERVIEW, 3);
        ContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredOrderList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(
                // ordered by delivery date, because model is configured to always sort by date
                Arrays.asList(CARL, FIONA, ELLE),
                model.getFilteredOrderList()
        );
    }

    /**
     * Parses {@code userInput} into a {@code ContainsKeywordsPredicate}.
     */
    private ContainsKeywordsPredicate preparePredicate(String userInput) {
        HashMap<Prefix, List<String>> map = new HashMap<>();
        map.put(PREFIX_NAME, Arrays.asList(userInput.split("\\s+")));
        return new ContainsKeywordsPredicate(map);
    }
}
