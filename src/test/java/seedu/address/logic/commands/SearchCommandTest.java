package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameAndSchoolContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code SearchCommand}.
 */
public class SearchCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameAndSchoolContainsKeywordsPredicate firstPredicate =
                new NameAndSchoolContainsKeywordsPredicate(Collections.singletonList("first"),
                        null, null);
        NameAndSchoolContainsKeywordsPredicate secondPredicate =
                new NameAndSchoolContainsKeywordsPredicate(Collections.singletonList("second"),
                        Collections.singletonList("Jurong"), Collections.singletonList("B"));

        SearchCommand searchFirstCommand = new SearchCommand(firstPredicate);
        SearchCommand searchSecondCommand = new SearchCommand(secondPredicate);

        // same object -> returns true
        assertTrue(searchFirstCommand.equals(searchFirstCommand));

        // same values -> returns true
        SearchCommand searchFirstCommandCopy = new SearchCommand(firstPredicate);
        assertTrue(searchFirstCommand.equals(searchFirstCommandCopy));

        // different types -> returns false
        assertFalse(searchFirstCommand.equals(1));

        // null -> returns false
        assertFalse(searchFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(searchFirstCommand.equals(searchSecondCommand));
    }

    @Test
    public void execute_multipleNameKeywords_multipleStudentsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameAndSchoolContainsKeywordsPredicate predicate = preparePredicate(" n/Kurz Elle Kunz");
        SearchCommand command = new SearchCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleSchoolKeywords_multipleStudentsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        NameAndSchoolContainsKeywordsPredicate predicate = preparePredicate(" s/Clementi Town");
        SearchCommand command = new SearchCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleTagKeywords_multipleStudentsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        NameAndSchoolContainsKeywordsPredicate predicate = preparePredicate(" t/sec3 math");
        SearchCommand command = new SearchCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_nonMatchingKeywords_zeroStudentsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameAndSchoolContainsKeywordsPredicate predicate = preparePredicate(" n/Jade s/abc");
        SearchCommand command = new SearchCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_keywordsOrderSwitched_success() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        NameAndSchoolContainsKeywordsPredicate predicate = preparePredicate(" s/West Jurong");
        SearchCommand command = new SearchCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameAndSchoolContainsKeywordsPredicate}.
     */
    private NameAndSchoolContainsKeywordsPredicate preparePredicate(String userInput) {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_SCHOOL, PREFIX_TAG);
        System.out.println(argMultimap);

        String[] nameKeywords = null;
        String[] schoolKeywords = null;
        String[] tagKeywords = null;

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            nameKeywords = extractKeywords(argMultimap, PREFIX_NAME);
        }
        if (argMultimap.getValue(PREFIX_SCHOOL).isPresent()) {
            schoolKeywords = extractKeywords(argMultimap, PREFIX_SCHOOL);
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            tagKeywords = extractKeywords(argMultimap, PREFIX_TAG);
        }
        List<String> nameKeywordsList = nameKeywords == null ? null : Arrays.asList(nameKeywords);
        List<String> schoolKeywordsList = schoolKeywords == null ? null : Arrays.asList(schoolKeywords);
        List<String> tagKeywordsList = tagKeywords == null ? null : Arrays.asList(tagKeywords);

        return new NameAndSchoolContainsKeywordsPredicate(
                nameKeywordsList, schoolKeywordsList, tagKeywordsList);
    }

    /**
     * Extracts keywords from input into a {@code String[]}.
     */
    private String[] extractKeywords(ArgumentMultimap argMultimap, Prefix prefix) {
        String keywords = "";

        if (prefix.equals(PREFIX_NAME)) {
            keywords = argMultimap.getValue(PREFIX_NAME).get();
        } else if (prefix.equals(PREFIX_SCHOOL)) {
            keywords = argMultimap.getValue(PREFIX_SCHOOL).get();
        } else if (prefix.equals(PREFIX_TAG)) {
            keywords = argMultimap.getValue(PREFIX_TAG).get();
        }
        requireNonNull(keywords);
        String trimmedName = keywords.trim();
        return trimmedName.split("\\s+");
    }
}
