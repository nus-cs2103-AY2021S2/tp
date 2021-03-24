package seedu.dictionote.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dictionote.commons.core.Messages.MESSAGE_NOTES_LISTED_OVERVIEW;
import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.dictionote.testutil.TypicalContacts.getTypicalAddressBook;
import static seedu.dictionote.testutil.TypicalContent.getTypicalDictionary;
import static seedu.dictionote.testutil.TypicalDefinition.getTypicalDefinitionBook;
import static seedu.dictionote.testutil.TypicalNotes.MI_AMOR;
import static seedu.dictionote.testutil.TypicalNotes.MI_VIDA;
import static seedu.dictionote.testutil.TypicalNotes.getTypicalNoteBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.dictionote.model.Model;
import seedu.dictionote.model.ModelManager;
import seedu.dictionote.model.UserPrefs;
import seedu.dictionote.model.note.NoteContainsKeywordsPredicate;
import seedu.dictionote.model.note.TagNoteContainKeywordsPredicate;
import seedu.dictionote.testutil.TypicalNotes;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindNoteCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());

    @Test
    public void equals() {
        NoteContainsKeywordsPredicate firstNamePredicate =
                new NoteContainsKeywordsPredicate(Collections.singletonList("first"));
        NoteContainsKeywordsPredicate secondNamePredicate =
                new NoteContainsKeywordsPredicate(Collections.singletonList("second"));
        TagNoteContainKeywordsPredicate firstTagsPredicate =
                new TagNoteContainKeywordsPredicate(Collections.singletonList("first"));
        TagNoteContainKeywordsPredicate secondTagsPredicate =
                new TagNoteContainKeywordsPredicate(Collections.singletonList("second"));

        FindNoteCommand findFirstCommand = new FindNoteCommand(firstNamePredicate, firstTagsPredicate);
        FindNoteCommand findSecondCommand = new FindNoteCommand(secondNamePredicate, secondTagsPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindNoteCommand findFirstCommandCopy = new FindNoteCommand(firstNamePredicate, firstTagsPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_allContactsFound() {

        NoteContainsKeywordsPredicate namePredicate = prepareNotePredicate(" ");
        TagNoteContainKeywordsPredicate tagsPredicate = prepareTagNotePredicate(" ");

        expectedModel.updateFilteredNoteList(namePredicate.and(tagsPredicate));

        assertEquals(TypicalNotes.getTypicalNotes(), model.getFilteredNoteList());
    }

    @Test
    public void execute_nameKeywords_multipleContactsFound() {
        String expectedMessage = String.format(MESSAGE_NOTES_LISTED_OVERVIEW, 2);

        NoteContainsKeywordsPredicate namePredicate = prepareNotePredicate("been");
        TagNoteContainKeywordsPredicate tagsPredicate = prepareTagNotePredicate(" ");

        FindNoteCommand command = new FindNoteCommand(namePredicate, tagsPredicate);
        expectedModel.updateFilteredNoteList(namePredicate.and(tagsPredicate));

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MI_AMOR, MI_VIDA), model.getFilteredNoteList());
    }

    /**
     * Parses {@code userInput} into a {@code NoteContainsKeywordsPredicate}.
     */
    private NoteContainsKeywordsPredicate prepareNotePredicate(String userInput) {
        return new NoteContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code TagNoteContainKeywordsPredicate}.
     */
    private TagNoteContainKeywordsPredicate prepareTagNotePredicate(String userInput) {
        return new TagNoteContainKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
