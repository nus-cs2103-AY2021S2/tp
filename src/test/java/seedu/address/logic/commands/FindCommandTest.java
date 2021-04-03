package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
import seedu.address.model.person.predicates.AddressContainsKeywordsPredicate;
import seedu.address.model.person.predicates.EmailContainsKeywordsPredicate;
import seedu.address.model.person.ModeOfContact;
import seedu.address.model.person.predicates.ModeOfContactPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PersonBlacklistedPredicate;
import seedu.address.model.person.predicates.PersonTagContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PhoneContainsNumbersPredicate;
import seedu.address.model.person.predicates.ReturnTruePredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final ReturnTruePredicate returnTruePredicate = new ReturnTruePredicate();

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

        AddressContainsKeywordsPredicate firstAddressPredicate =
                new AddressContainsKeywordsPredicate(Collections.singletonList("addressOne"));
        AddressContainsKeywordsPredicate secondAddressPredicate =
                new AddressContainsKeywordsPredicate(Collections.singletonList("addressTwo"));

        EmailContainsKeywordsPredicate firstEmailPredicate =
                new EmailContainsKeywordsPredicate(Collections.singletonList("emailOne"));
        EmailContainsKeywordsPredicate secondEmailPredicate =
                new EmailContainsKeywordsPredicate(Collections.singletonList("emailTwo"));



        FindCommand findFirstCommand = new FindCommand(firstPredicate,
                returnTruePredicate, returnTruePredicate, returnTruePredicate,
                returnTruePredicate, returnTruePredicate, returnTruePredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate,
                returnTruePredicate, returnTruePredicate, returnTruePredicate,
                returnTruePredicate, returnTruePredicate, returnTruePredicate);

        FindCommand findThirdCommand = new FindCommand(returnTruePredicate,
                firstTagPredicate, returnTruePredicate, returnTruePredicate,
                returnTruePredicate, returnTruePredicate, returnTruePredicate);
        FindCommand findFourthCommand = new FindCommand(returnTruePredicate,
                secondTagPredicate, returnTruePredicate, returnTruePredicate,
                returnTruePredicate, returnTruePredicate, returnTruePredicate);

        FindCommand findFifthCommand = new FindCommand(firstPredicate,
                firstTagPredicate, returnTruePredicate, returnTruePredicate,
                returnTruePredicate, returnTruePredicate, returnTruePredicate);
        FindCommand findSixthCommand = new FindCommand(secondPredicate,
                secondTagPredicate, returnTruePredicate, returnTruePredicate,
                returnTruePredicate, returnTruePredicate, returnTruePredicate);

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);
        assertEquals(findThirdCommand, findThirdCommand);
        assertEquals(findFifthCommand, findFifthCommand);

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate,
                returnTruePredicate, returnTruePredicate, returnTruePredicate,
                returnTruePredicate, returnTruePredicate, returnTruePredicate);
        assertEquals(findFirstCommandCopy, findFirstCommand);

        FindCommand findFourthCommandCopy = new FindCommand(returnTruePredicate,
                secondTagPredicate, returnTruePredicate, returnTruePredicate,
                returnTruePredicate, returnTruePredicate, returnTruePredicate);
        assertEquals(findFourthCommandCopy, findFourthCommand);

        FindCommand findSixthCommandCopy = new FindCommand(secondPredicate,
                secondTagPredicate, returnTruePredicate, returnTruePredicate,
                returnTruePredicate, returnTruePredicate, returnTruePredicate);
        assertEquals(findSixthCommandCopy, findSixthCommand);

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertNotEquals(findFirstCommand, null);

        // different person -> returns false
        assertNotEquals(findSecondCommand, findFirstCommand);
        assertNotEquals(findFourthCommand, findThirdCommand);
        assertNotEquals(findSixthCommand, findFifthCommand);

        assertNotEquals(findThirdCommand, findFirstCommand);
        assertNotEquals(findFifthCommand, findFirstCommand);
        assertNotEquals(findFifthCommand, findThirdCommand);
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate(" ");
        PersonTagContainsKeywordsPredicate tagPredicate = prepareTagPredicate(" ");
        AddressContainsKeywordsPredicate addressPredicate = prepareAddressPredicate(" ");
        FindCommand command = new FindCommand(predicate, tagPredicate, addressPredicate, returnTruePredicate,
                returnTruePredicate, returnTruePredicate, returnTruePredicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleNameKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate, returnTruePredicate, returnTruePredicate, returnTruePredicate,
                returnTruePredicate, returnTruePredicate, returnTruePredicate);
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
        FindCommand command = new FindCommand(returnTruePredicate, predicate, returnTruePredicate, returnTruePredicate,
                returnTruePredicate, returnTruePredicate, returnTruePredicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL, BOB), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleNameAndTagKeywords_multiplePersonsFound() {
        model.addPerson(BOB);
        expectedModel.addPerson(BOB);
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate("Pauline Elle Choo");
        PersonTagContainsKeywordsPredicate tagPredicate = prepareTagPredicate("friends husband");
        FindCommand command = new FindCommand(namePredicate, tagPredicate, returnTruePredicate, returnTruePredicate,
                returnTruePredicate, returnTruePredicate, returnTruePredicate);
        expectedModel.updateFilteredPersonList(namePredicate.and(tagPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BOB), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code PersonTagContainsKeywordsPredicate}.
     */
    private PersonTagContainsKeywordsPredicate prepareTagPredicate(String userInput) {
        return new PersonTagContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code AddressContainsKeywordsPredicate}.
     */
    private AddressContainsKeywordsPredicate prepareAddressPredicate(String userInput) {
        return new AddressContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code PhoneContainsNumbersPredicate}.
     */
    private PhoneContainsNumbersPredicate preparePhonePredicate(String userInput) {
        return new PhoneContainsNumbersPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code EmailContainsKeywordsPredicate}.
     */
    private EmailContainsKeywordsPredicate prepareEmailPredicate(String userInput) {
        return new EmailContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code ModeOfContactPredicate}.
     */
    private ModeOfContactPredicate prepareModeOfContactPredicate(String userInput) {
        return new ModeOfContactPredicate(new ModeOfContact(userInput));
    }

    /**
     * Parses {@code userInput} into a {@code PersonBlacklistedPredicate}.
     */
    private PersonBlacklistedPredicate prepareBlacklistPredicate(boolean isBlacklisted) {
        return new PersonBlacklistedPredicate(isBlacklisted);
    }
}
