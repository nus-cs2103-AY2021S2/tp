package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.testutil.TypicalDates.getTypicalDatesBook;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalLessonBook;

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
import seedu.address.model.person.predicate.NameSchoolAndSubjectContainsKeywordsPredicate;
import seedu.address.model.subject.Subject;

/**
 * Contains integration tests (interaction with the Model) for {@code SearchCommand}.
 */
public class SearchCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalDatesBook(),
            getTypicalLessonBook());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalDatesBook(),
            getTypicalLessonBook());

    @Test
    public void equals() {
        NameSchoolAndSubjectContainsKeywordsPredicate firstPredicate =
                new NameSchoolAndSubjectContainsKeywordsPredicate(Collections.singletonList("first"),
                        null, null);
        NameSchoolAndSubjectContainsKeywordsPredicate secondPredicate =
                new NameSchoolAndSubjectContainsKeywordsPredicate(Collections.singletonList("second"),
                        Collections.singletonList("Jurong"), Collections.singletonList(new Subject("bio")));

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
        NameSchoolAndSubjectContainsKeywordsPredicate predicate = preparePredicate(" n/Kurz Elle Kunz");
        SearchCommand command = new SearchCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleSchoolKeywords_multipleStudentsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        NameSchoolAndSubjectContainsKeywordsPredicate predicate = preparePredicate(" s/Clementi Town");
        SearchCommand command = new SearchCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleSubjectKeywords_multipleStudentsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        NameSchoolAndSubjectContainsKeywordsPredicate predicate = preparePredicate(" t/cn math");
        SearchCommand command = new SearchCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_nonMatchingKeywords_zeroStudentsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameSchoolAndSubjectContainsKeywordsPredicate predicate = preparePredicate(" n/Jade s/abc");
        SearchCommand command = new SearchCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_keywordsOrderSwitched_success() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        NameSchoolAndSubjectContainsKeywordsPredicate predicate = preparePredicate(" s/West Jurong");
        SearchCommand command = new SearchCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameAndSchoolContainsKeywordsPredicate}.
     */
    private NameSchoolAndSubjectContainsKeywordsPredicate preparePredicate(String userInput) {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_SCHOOL, PREFIX_SUBJECT);
        System.out.println(argMultimap);

        String[] nameKeywords = null;
        String[] schoolKeywords = null;
        String[] subjectKeywords = null;
        Subject[] subjects = null;

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            nameKeywords = extractKeywords(argMultimap, PREFIX_NAME);
        }
        if (argMultimap.getValue(PREFIX_SCHOOL).isPresent()) {
            schoolKeywords = extractKeywords(argMultimap, PREFIX_SCHOOL);
        }
        if (argMultimap.getValue(PREFIX_SUBJECT).isPresent()) {
            subjectKeywords = extractKeywords(argMultimap, PREFIX_SUBJECT);
            subjects = new Subject[subjectKeywords.length];
            int i = 0;
            //subject names here are assumed be in correct format.
            for (String name: subjectKeywords) {
                subjects[i] = new Subject(name);
                i++;
            }
        }
        List<String> nameKeywordsList = nameKeywords == null ? null : Arrays.asList(nameKeywords);
        List<String> schoolKeywordsList = schoolKeywords == null ? null : Arrays.asList(schoolKeywords);
        List<Subject> subjectsList = subjectKeywords == null ? null : Arrays.asList(subjects);

        return new NameSchoolAndSubjectContainsKeywordsPredicate(
                nameKeywordsList, schoolKeywordsList, subjectsList);
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
        } else if (prefix.equals(PREFIX_SUBJECT)) {
            keywords = argMultimap.getValue(PREFIX_SUBJECT).get();
        }
        requireNonNull(keywords);
        String trimmedName = keywords.trim();
        return trimmedName.split("\\s+");
    }
}
