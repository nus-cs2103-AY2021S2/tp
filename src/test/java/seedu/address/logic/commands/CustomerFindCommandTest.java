package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.customer.CustomerFindCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PersonNameContainsWordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class CustomerFindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        PersonNameContainsWordsPredicate firstPredicate =
                new PersonNameContainsWordsPredicate(Collections.singletonList("first"));
        PersonNameContainsWordsPredicate secondPredicate =
                new PersonNameContainsWordsPredicate(Collections.singletonList("second"));

        CustomerFindCommand findFirstCommand = new CustomerFindCommand(firstPredicate);
        CustomerFindCommand findSecondCommand = new CustomerFindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        CustomerFindCommand findFirstCommandCopy = new CustomerFindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(Messages.MESSAGE_ITEMS_LISTED_OVERVIEW, 0, Messages.ITEM_PERSONS);
        PersonNameContainsWordsPredicate predicate = preparePredicate(" ");
        CustomerFindCommand command = new CustomerFindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(Messages.MESSAGE_ITEMS_LISTED_OVERVIEW, 3, Messages.ITEM_PERSONS);
        PersonNameContainsWordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        CustomerFindCommand command = new CustomerFindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private PersonNameContainsWordsPredicate preparePredicate(String userInput) {
        return new PersonNameContainsWordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
