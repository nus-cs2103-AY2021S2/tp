package seedu.booking.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.booking.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.booking.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.booking.testutil.TypicalPersons.CARL;
import static seedu.booking.testutil.TypicalPersons.ELLE;
import static seedu.booking.testutil.TypicalPersons.FIONA;
import static seedu.booking.testutil.TypicalPersons.getTypicalBookingSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;
import seedu.booking.model.UserPrefs;
import seedu.booking.model.person.NameContainsKeywordsPredicate;
import seedu.booking.model.person.Person;
import seedu.booking.model.person.PersonTagContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindPersonCommand}.
 */
public class FindPersonCommandTest {
    private Model model = new ModelManager(getTypicalBookingSystem(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalBookingSystem(), new UserPrefs());

    @Test
    public void equals() {
        List<Predicate<Person>> firstPredicateList = new ArrayList<>();
        PersonTagContainsKeywordsPredicate personTagPredicateStudent = new PersonTagContainsKeywordsPredicate("Student");
        firstPredicateList.add(personTagPredicateStudent);

        List<Predicate<Person>> secondPredicateList = new ArrayList<>();
        PersonTagContainsKeywordsPredicate personTagPredicateProfessor = new PersonTagContainsKeywordsPredicate("Professor");
        secondPredicateList.add(personTagPredicateProfessor);

        FindPersonCommand findFirstCommand = new FindPersonCommand(firstPredicateList);
        FindPersonCommand findSecondCommand = new FindPersonCommand(secondPredicateList);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindPersonCommand findFirstCommandCopy = new FindPersonCommand(firstPredicateList);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different predicate lists -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_noKeywordsMatch_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        List<Predicate<Person>> predicate = preparePredicate("nonExistentTag");
        FindPersonCommand command = new FindPersonCommand(predicate);
        expectedModel.updateFilteredPersonList(combinePersonPredicates(predicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    /**
     * Parses {@code tagKeyword} into a {@code List<Predicate<Person>>}.
     */
    private List<Predicate<Person>> preparePredicate(String tagKeyword) {
        List<Predicate<Person>> firstPredicateList = new ArrayList<>();
        PersonTagContainsKeywordsPredicate personTagPredicateStudent = new PersonTagContainsKeywordsPredicate(tagKeyword);
        firstPredicateList.add(personTagPredicateStudent);
        return firstPredicateList;
    }

    private static Predicate<Person> combinePersonPredicates(List<Predicate<Person>> predicateList) {
        return predicateList.stream().reduce(Predicate::and).orElse(PREDICATE_SHOW_ALL_PERSONS);
    }
}
