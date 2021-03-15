package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_VENUE_DISPLAYED;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.booking.VenueNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindVenueCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        VenueNameContainsKeywordsPredicate firstPredicate =
                new VenueNameContainsKeywordsPredicate(Collections.singletonList("first"));
        VenueNameContainsKeywordsPredicate secondPredicate =
                new VenueNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindVenueCommand findFirstCommand = new FindVenueCommand(firstPredicate);
        FindVenueCommand findSecondCommand = new FindVenueCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindVenueCommand findFirstCommandCopy = new FindVenueCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noVenueFound() {
        String expectedMessage = String.format(MESSAGE_VENUE_DISPLAYED, 0);
        VenueNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindVenueCommand command = new FindVenueCommand(predicate);
        expectedModel.updateFilteredVenueList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredVenueList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private VenueNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new VenueNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
