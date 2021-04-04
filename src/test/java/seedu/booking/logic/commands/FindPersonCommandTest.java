package seedu.booking.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.booking.logic.commands.CommandTestUtil.NON_EXISTENT_EMAIL;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.booking.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.booking.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.booking.testutil.TypicalPersons.getTypicalBookingSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;
import seedu.booking.model.UserPrefs;
import seedu.booking.model.person.EmailMatchesKeywordPredicate;
import seedu.booking.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) for {@code FindPersonCommand}.
 */
public class FindPersonCommandTest {
    private Model model = new ModelManager(getTypicalBookingSystem(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalBookingSystem(), new UserPrefs());

    @Test
    public void equals() {
        List<Predicate<Person>> firstPredicateList = new ArrayList<>();
        EmailMatchesKeywordPredicate emailPredicateAmy =
                new EmailMatchesKeywordPredicate(VALID_EMAIL_AMY);
        firstPredicateList.add(emailPredicateAmy);

        List<Predicate<Person>> secondPredicateList = new ArrayList<>();
        EmailMatchesKeywordPredicate emailPredicateBob =
                new EmailMatchesKeywordPredicate(VALID_ADDRESS_BOB);
        secondPredicateList.add(emailPredicateBob);

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

        // different persons -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_noEmailKeywordMatches_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);

        List<Predicate<Person>> predicate = prepareEmailPredicate(NON_EXISTENT_EMAIL);

        FindPersonCommand command = new FindPersonCommand(predicate);
        expectedModel.updateFilteredPersonList(combinePersonPredicates(predicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_nameKeywordsMatch_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);

        String emailKeyword = expectedModel.getFilteredPersonList().get(0).getEmail().toString();
        List<Predicate<Person>> predicate = prepareEmailPredicate(emailKeyword);

        FindPersonCommand command = new FindPersonCommand(predicate);
        expectedModel.updateFilteredPersonList(combinePersonPredicates(predicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    /**
     * Parses {@code tagKeyword} into a {@code List<Predicate<Person>>}.
     */
    private List<Predicate<Person>> prepareEmailPredicate(String tagKeyword) {
        List<Predicate<Person>> firstPredicateList = new ArrayList<>();
        EmailMatchesKeywordPredicate emailPredicate = new EmailMatchesKeywordPredicate(tagKeyword);
        firstPredicateList.add(emailPredicate);
        return firstPredicateList;
    }

    private static Predicate<Person> combinePersonPredicates(List<Predicate<Person>> predicateList) {
        return predicateList.stream().reduce(Predicate::and).orElse(PREDICATE_SHOW_ALL_PERSONS);
    }
}
