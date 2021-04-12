package seedu.ta.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ta.commons.core.Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW;
import static seedu.ta.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.ta.testutil.TypicalTeachingAssistant.getTypicalTeachingAssistant;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.ta.model.Model;
import seedu.ta.model.ModelManager;
import seedu.ta.model.UserPrefs;
import seedu.ta.model.contact.ContactNameContainsKeywordsPredicate;
import seedu.ta.testutil.TypicalTeachingAssistant;

/**
 * Contains integration tests (interaction with the Model) for {@code FindContactCommand}.
 */
public class FindContactCommandTest {

    private Model model = new ModelManager(getTypicalTeachingAssistant(), new UserPrefs());
    private Model expectedModel = new ModelManager(model.getTeachingAssistant(), new UserPrefs());

    @Test
    public void equals() {
        ContactNameContainsKeywordsPredicate firstPredicate =
                new ContactNameContainsKeywordsPredicate(Collections.singletonList("first"));
        ContactNameContainsKeywordsPredicate secondPredicate =
                new ContactNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindContactCommand findFirstCommand = new FindContactCommand(firstPredicate);
        FindContactCommand findSecondCommand = new FindContactCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindContactCommand findFirstCommandCopy = new FindContactCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different contact -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_oneKeyword_noContactFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 0);
        ContactNameContainsKeywordsPredicate predicate = preparePredicate("Alison");
        FindContactCommand command = new FindContactCommand(predicate);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredContactList());
    }

    @Test
    public void execute_oneKeyword_multipleContactsFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 2);
        ContactNameContainsKeywordsPredicate predicate = preparePredicate("Meier");
        FindContactCommand command = new FindContactCommand(predicate);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalTeachingAssistant.BEN, TypicalTeachingAssistant.DAVID),
                model.getFilteredContactList());
    }

    /**
     * Parses {@code userInput} into a {@code ContactNameContainsKeywordsPredicate}.
     */
    private ContactNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new ContactNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
