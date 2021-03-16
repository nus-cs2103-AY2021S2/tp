package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_CUSTOMERS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCustomers.CARL;
import static seedu.address.testutil.TypicalCustomers.ELLE;
import static seedu.address.testutil.TypicalCustomers.FIONA;
import static seedu.address.testutil.TypicalModels.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.predicates.CustomerNamePredicate;
import seedu.address.model.util.predicate.CompositeFieldPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCustomerCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        final List<String> firstList = Collections.singletonList("first");
        final List<String> secondList = Collections.singletonList("second");

        CompositeFieldPredicate<Customer> firstPredicate =
                new CompositeFieldPredicate<>(new CustomerNamePredicate(firstList));
        CompositeFieldPredicate<Customer> secondPredicate =
                new CompositeFieldPredicate<>(new CustomerNamePredicate(secondList));

        FindCustomerCommand findFirstCommand = new FindCustomerCommand(firstPredicate);
        FindCustomerCommand findSecondCommand = new FindCustomerCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCustomerCommand findFirstCommandCopy = new FindCustomerCommand(firstPredicate);
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
        String keywordsString = " ";
        CompositeFieldPredicate<Customer> predicate = preparePredicate(keywordsString);
        FindCustomerCommand command = new FindCustomerCommand(predicate);
        expectedModel.updateFilteredCustomerList(predicate);
        expectedModel.setPanelToCustomerList();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredCustomerList());
    }

    @Test
    public void execute_multipleKeywords_multipleCustomersFound() {
        String expectedMessage = String.format(MESSAGE_CUSTOMERS_LISTED_OVERVIEW, 3);

        final String keywordsString = "Kurz Elle Kunz";
        CompositeFieldPredicate<Customer> predicate = preparePredicate(keywordsString);
        FindCustomerCommand command = new FindCustomerCommand(predicate);
        expectedModel.updateFilteredCustomerList(predicate);
        expectedModel.setPanelToCustomerList();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredCustomerList());
    }

    /**
     * Parses {@code userInput} into a {@code ModelCompositePredicate}.
     */
    private CompositeFieldPredicate<Customer> preparePredicate(String userInput) {
        return new CompositeFieldPredicate<>(new CustomerNamePredicate(Arrays.asList(userInput.split("\\s+"))));
    }

}
