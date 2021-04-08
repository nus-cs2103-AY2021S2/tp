package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.JANE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ModeOfContact;
import seedu.address.model.person.predicates.AddressContainsKeywordsPredicate;
import seedu.address.model.person.predicates.EmailContainsKeywordsPredicate;
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

        PhoneContainsNumbersPredicate firstPhonePredicate =
                new PhoneContainsNumbersPredicate(Collections.singletonList("123"));
        PhoneContainsNumbersPredicate secondPhonePredicate =
                new PhoneContainsNumbersPredicate(Collections.singletonList("456"));

        PersonBlacklistedPredicate firstBlacklistPredicate =
                new PersonBlacklistedPredicate(true);
        PersonBlacklistedPredicate secondBlacklistPredicate =
                new PersonBlacklistedPredicate(false);

        ModeOfContactPredicate firstModeOfContactPredicate =
                new ModeOfContactPredicate(new ModeOfContact("phone"));
        ModeOfContactPredicate secondModeOfContactPredicate =
                new ModeOfContactPredicate(new ModeOfContact("email"));

        FindCommand findFirstCommand = FindCommand.returnDummyCommand().setPredicate(firstPredicate);
        FindCommand findSecondCommand = FindCommand.returnDummyCommand().setPredicate(secondPredicate);

        FindCommand findThirdCommand = FindCommand.returnDummyCommand().setPredicate(firstTagPredicate);
        FindCommand findFourthCommand = FindCommand.returnDummyCommand().setPredicate(secondTagPredicate);

        FindCommand findAddressOneCommand = FindCommand.returnDummyCommand().setPredicate(firstAddressPredicate);
        FindCommand findAddressTwoCommand = FindCommand.returnDummyCommand().setPredicate(secondAddressPredicate);

        FindCommand findEmailOneCommand = FindCommand.returnDummyCommand().setPredicate(firstEmailPredicate);
        FindCommand findEmailTwoCommand = FindCommand.returnDummyCommand().setPredicate(secondEmailPredicate);

        FindCommand findPhoneOneCommand = FindCommand.returnDummyCommand().setPredicate(firstPhonePredicate);
        FindCommand findPhoneTwoCommand = FindCommand.returnDummyCommand().setPredicate(secondPhonePredicate);

        FindCommand findBlacklistOneCommand = FindCommand.returnDummyCommand().setPredicate(firstBlacklistPredicate);
        FindCommand findBlacklistTwoCommand = FindCommand.returnDummyCommand().setPredicate(secondBlacklistPredicate);

        FindCommand findModeOfContactOneCommand = FindCommand.returnDummyCommand()
                .setPredicate(firstModeOfContactPredicate);
        FindCommand findModeOfContactTwoCommand = FindCommand.returnDummyCommand()
                .setPredicate(secondModeOfContactPredicate);

        FindCommand findFifthCommand = FindCommand.returnDummyCommand()
                .setPredicate(firstPredicate).setPredicate(firstTagPredicate);
        FindCommand findSixthCommand = FindCommand.returnDummyCommand()
                .setPredicate(secondPredicate).setPredicate(secondTagPredicate);

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);
        assertEquals(findThirdCommand, findThirdCommand);
        assertEquals(findFifthCommand, findFifthCommand);
        assertEquals(findAddressOneCommand, findAddressOneCommand);
        assertEquals(findPhoneOneCommand, findPhoneOneCommand);
        assertEquals(findBlacklistOneCommand, findBlacklistOneCommand);
        assertEquals(findEmailOneCommand, findEmailOneCommand);
        assertEquals(findModeOfContactOneCommand, findModeOfContactOneCommand);

        // same values -> returns true
        FindCommand findFirstCommandCopy = FindCommand.returnDummyCommand().setPredicate(firstPredicate);
        assertEquals(findFirstCommandCopy, findFirstCommand);

        FindCommand findFourthCommandCopy = FindCommand.returnDummyCommand().setPredicate(secondTagPredicate);
        assertEquals(findFourthCommandCopy, findFourthCommand);

        FindCommand findSixthCommandCopy = FindCommand.returnDummyCommand()
                .setPredicate(secondPredicate).setPredicate(secondTagPredicate);
        assertEquals(findSixthCommandCopy, findSixthCommand);

        FindCommand findAddressOneCopy = findAddressOneCommand.copy();
        // this proves that a new object is returned
        assertNotEquals(findAddressOneCopy.hashCode(), findAddressOneCommand.hashCode());
        assertEquals(findAddressOneCopy, findAddressOneCommand);

        FindCommand findPhoneOneCopy = findPhoneOneCommand.copy();
        assertEquals(findPhoneOneCopy, findPhoneOneCommand);

        FindCommand findBlacklistOneCopy = findBlacklistOneCommand.copy();
        assertEquals(findBlacklistOneCopy, findBlacklistOneCommand);

        FindCommand findEmailOneCopy = findEmailOneCommand.copy();
        assertEquals(findEmailOneCopy, findEmailOneCommand);

        FindCommand findModeOfContactCopy = findModeOfContactOneCommand.copy();
        assertEquals(findModeOfContactCopy, findModeOfContactOneCommand);

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertNotEquals(findFirstCommand, null);

        // different person -> returns false
        List<FindCommand> commandListOne = Arrays.asList(findFirstCommand, findThirdCommand,
                findFifthCommand, findAddressOneCommand, findBlacklistOneCommand, findEmailOneCommand,
                findModeOfContactOneCommand, findPhoneOneCommand);
        List<FindCommand> commandListTwo = Arrays.asList(findSecondCommand, findFourthCommand,
                findSixthCommand, findAddressTwoCommand, findBlacklistTwoCommand, findEmailTwoCommand,
                findModeOfContactTwoCommand, findPhoneTwoCommand);

        for (int i = 0; i < commandListOne.size(); i++) {
            for (int j = i + 1; j < commandListOne.size(); j++) {
                assertNotEquals(commandListOne.get(i), commandListOne.get(j));
            }
        }

        for (FindCommand f1 : commandListOne) {
            for (FindCommand f2 : commandListTwo) {
                assertNotEquals(f1, f2);
            }
        }
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate(" ");
        PersonTagContainsKeywordsPredicate tagPredicate = prepareTagPredicate(" ");
        AddressContainsKeywordsPredicate addressPredicate = prepareAddressPredicate(" ");
        EmailContainsKeywordsPredicate emailPredicate = prepareEmailPredicate(" ");
        PhoneContainsNumbersPredicate phonePredicate = preparePhonePredicate(" ");
        FindCommand command = new FindCommand(predicate, tagPredicate, addressPredicate, emailPredicate,
                phonePredicate, returnTruePredicate, returnTruePredicate);
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
    public void execute_multipleAddressKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        AddressContainsKeywordsPredicate predicate = prepareAddressPredicate("street ave");
        FindCommand command = new FindCommand(returnTruePredicate, returnTruePredicate, predicate, returnTruePredicate,
                returnTruePredicate, returnTruePredicate, returnTruePredicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, GEORGE, JANE), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleEmailKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 4);
        EmailContainsKeywordsPredicate predicate = prepareEmailPredicate("lyd ne corn");
        FindCommand command = new FindCommand(returnTruePredicate, returnTruePredicate, returnTruePredicate, predicate,
                returnTruePredicate, returnTruePredicate, returnTruePredicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL, ELLE, FIONA, JANE), model.getFilteredPersonList());
    }

    @Test
    public void execute_multiplePhoneNumbers_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 6);
        PhoneContainsNumbersPredicate predicate = preparePhonePredicate("482 53");
        FindCommand command = new FindCommand(returnTruePredicate, returnTruePredicate, returnTruePredicate,
                returnTruePredicate, predicate, returnTruePredicate, returnTruePredicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, CARL, DANIEL, ELLE, FIONA, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_blacklisted_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonBlacklistedPredicate predicate = prepareBlacklistPredicate(true);
        FindCommand command = new FindCommand(returnTruePredicate, returnTruePredicate, returnTruePredicate,
                returnTruePredicate, returnTruePredicate, predicate, returnTruePredicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, FIONA, JANE), model.getFilteredPersonList());
    }

    @Test
    public void execute_modeOfContactsPhone_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 4);
        ModeOfContactPredicate predicate = prepareModeOfContactPredicate("phone");
        FindCommand command = new FindCommand(returnTruePredicate, returnTruePredicate, returnTruePredicate,
                returnTruePredicate, returnTruePredicate, returnTruePredicate, predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, DANIEL, GEORGE, JANE), model.getFilteredPersonList());
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
