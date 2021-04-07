package seedu.iscam.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.iscam.commons.core.Messages.MESSAGE_CLIENTS_LISTED_OVERVIEW;
import static seedu.iscam.commons.core.Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW;
import static seedu.iscam.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.iscam.testutil.TypicalMeetings.FIONA_1;
import static seedu.iscam.testutil.TypicalMeetings.FIONA_2;
import static seedu.iscam.testutil.TypicalClients.getTypicalClientBook;
import static seedu.iscam.testutil.TypicalMeetings.getTypicalMeetingBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.iscam.model.meeting.MeetingContainsKeywordsPredicate;
import seedu.iscam.model.Model;
import seedu.iscam.model.ModelManager;
import seedu.iscam.model.user.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code FindMeetingsCommand}.
 */
public class FindMeetingsCommandTest {
    private Model model = new ModelManager(getTypicalClientBook(), getTypicalMeetingBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalClientBook(), getTypicalMeetingBook(), new UserPrefs());

    @Test
    public void equals() {
        MeetingContainsKeywordsPredicate firstPredicate =
                new MeetingContainsKeywordsPredicate(Collections.singletonList("first"));
        MeetingContainsKeywordsPredicate secondPredicate =
                new MeetingContainsKeywordsPredicate(Collections.singletonList("second"));

        FindMeetingsCommand findFirstCommand = new FindMeetingsCommand(firstPredicate);
        FindMeetingsCommand findSecondCommand = new FindMeetingsCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindMeetingsCommand findFirstCommandCopy = new FindMeetingsCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different client -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noMeetingFound() {
        String expectedMessage = String.format(MESSAGE_MEETINGS_LISTED_OVERVIEW + "\n", 0);
        MeetingContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindMeetingsCommand command = new FindMeetingsCommand(predicate);
        expectedModel.updateFilteredMeetingList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredMeetingList());
    }

    @Test
    public void execute_multipleKeywords_multipleMeetingsFound() {
        String expectedMessage = String.format(MESSAGE_MEETINGS_LISTED_OVERVIEW + "\n" + FIONA_1
                + "\n" + FIONA_2 + "\n", 2);
        MeetingContainsKeywordsPredicate predicate = preparePredicate("Kunz");
        FindMeetingsCommand command = new FindMeetingsCommand(predicate);
        expectedModel.updateFilteredMeetingList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FIONA_1, FIONA_2), model.getFilteredMeetingList());
    }

    /**
     * Parses {@code userInput} into a {@code MeetingContainsKeywordsPredicate}.
     */
    private MeetingContainsKeywordsPredicate preparePredicate(String userInput) {
        return new MeetingContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
