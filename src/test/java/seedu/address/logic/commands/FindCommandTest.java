package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PersonTagContainsKeywordsPredicate;
import seedu.address.model.person.ReturnTruePredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private ReturnTruePredicate returnTruePredicate = new ReturnTruePredicate();

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        PersonTagContainsKeywordsPredicate firstTagPredicate =
                new PersonTagContainsKeywordsPredicate(Collections.singletonList("tagOne"));
        PersonTagContainsKeywordsPredicate secondTagPredicate =
                new PersonTagContainsKeywordsPredicate(Collections.singletonList("tagTwo"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate, returnTruePredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate, returnTruePredicate);

        FindCommand findThirdCommand = new FindCommand(returnTruePredicate, firstTagPredicate);
        FindCommand findFourthCommand = new FindCommand(returnTruePredicate, secondTagPredicate);

        FindCommand findFifthCommand = new FindCommand(firstPredicate, firstTagPredicate);
        FindCommand findSixthCommand = new FindCommand(secondPredicate, secondTagPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));
        assertTrue(findThirdCommand.equals(findThirdCommand));
        assertTrue(findFifthCommand.equals(findFifthCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate, returnTruePredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        FindCommand findFourthCommandCopy = new FindCommand(returnTruePredicate, secondTagPredicate);
        assertTrue(findFourthCommand.equals(findFourthCommandCopy));

        FindCommand findSixthCommandCopy = new FindCommand(secondPredicate, secondTagPredicate);
        assertTrue(findSixthCommand.equals(findSixthCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
        assertFalse(findThirdCommand.equals(findFourthCommand));
        assertFalse(findFifthCommand.equals(findSixthCommand));

        assertFalse(findFirstCommand.equals(findThirdCommand));
        assertFalse(findFirstCommand.equals(findFifthCommand));
        assertFalse(findThirdCommand.equals(findFifthCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        PersonTagContainsKeywordsPredicate tagPredicate = prepareTagPredicate(" ");
        FindCommand command = new FindCommand(predicate, tagPredicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleNameKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate, returnTruePredicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleTagKeywords_multiplePersonsFound() {
        model.addPerson(BOB);
        expectedModel.addPerson(BOB);
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 4);
        PersonTagContainsKeywordsPredicate predicate = prepareTagPredicate("friends husband");
        FindCommand command = new FindCommand(returnTruePredicate, predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL, BOB), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleNameAndTagKeywords_multiplePersonsFound() {
        model.addPerson(BOB);
        expectedModel.addPerson(BOB);
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        NameContainsKeywordsPredicate namePredicate = preparePredicate("Pauline Elle Choo");
        PersonTagContainsKeywordsPredicate tagPredicate = prepareTagPredicate("friends husband");
        FindCommand command = new FindCommand(namePredicate, tagPredicate);
        expectedModel.updateFilteredPersonList(namePredicate.and(tagPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BOB), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code PersonTagContainsKeywordsPredicate}.
     */
    private PersonTagContainsKeywordsPredicate prepareTagPredicate(String userInput) {
        return new PersonTagContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
