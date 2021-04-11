package seedu.timeforwheels.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.timeforwheels.commons.core.Messages.MESSAGE_CUSTOMERS_LISTED_OVERVIEW;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.timeforwheels.testutil.TypicalCustomers.ALICE;
import static seedu.timeforwheels.testutil.TypicalCustomers.BENSON;
import static seedu.timeforwheels.testutil.TypicalCustomers.CARL;
import static seedu.timeforwheels.testutil.TypicalCustomers.ELLE;
import static seedu.timeforwheels.testutil.TypicalCustomers.FIONA;
import static seedu.timeforwheels.testutil.TypicalCustomers.getTypicalDeliveryList;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.timeforwheels.model.Model;
import seedu.timeforwheels.model.ModelManager;
import seedu.timeforwheels.model.UserPrefs;
import seedu.timeforwheels.model.customer.AttributeContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalDeliveryList(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalDeliveryList(), new UserPrefs());

    @Test
    public void equals() {
        AttributeContainsKeywordsPredicate firstPredicate =
                new AttributeContainsKeywordsPredicate(Collections.singletonList("first"));
        AttributeContainsKeywordsPredicate secondPredicate =
                new AttributeContainsKeywordsPredicate(Collections.singletonList("second"));

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

        // different customer -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noCustomerFound() {
        String expectedMessage = String.format(MESSAGE_CUSTOMERS_LISTED_OVERVIEW, 0);
        AttributeContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredCustomerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredCustomerList());
    }

    @Test
    public void execute_multipleKeywords_multipleCustomersFound() {
        String expectedMessage = String.format(MESSAGE_CUSTOMERS_LISTED_OVERVIEW, 3);
        AttributeContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredCustomerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredCustomerList());
    }

    @Test
    public void execute_multipleTypeKeywords_multipleCustomersFound() {
        String expectedMessage = String.format(MESSAGE_CUSTOMERS_LISTED_OVERVIEW, 3);
        AttributeContainsKeywordsPredicate predicate = preparePredicate("Kurz Clementi liquid");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredCustomerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL), model.getFilteredCustomerList());
    }

    @Test
    public void execute_multipleTypeKeywordsWithNonMatching_multipleCustomersFound() {
        String expectedMessage = String.format(MESSAGE_CUSTOMERS_LISTED_OVERVIEW, 3);
        AttributeContainsKeywordsPredicate predicate = preparePredicate("Kurz Clementi liquid blablabla");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredCustomerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL), model.getFilteredCustomerList());
    }

    /**
     * Parses {@code userInput} into a {@code AttributeContainsKeywordsPredicate}.
     */
    private AttributeContainsKeywordsPredicate preparePredicate(String userInput) {
        return new AttributeContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
