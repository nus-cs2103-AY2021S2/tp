package seedu.dictionote.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dictionote.testutil.TypicalContacts.getTypicalContactsList;
import static seedu.dictionote.testutil.TypicalContent.getTypicalDictionary;
import static seedu.dictionote.testutil.TypicalDefinition.getTypicalDefinitionBook;
import static seedu.dictionote.testutil.TypicalNotes.getTypicalNoteBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.dictionote.model.Model;
import seedu.dictionote.model.ModelManager;
import seedu.dictionote.model.UserPrefs;
import seedu.dictionote.model.dictionary.ContentContainsKeywordsPredicate;
import seedu.dictionote.testutil.TypicalContent;
import seedu.dictionote.testutil.TypicalDictionaryContentConfig;

/**
 * Contains integration tests (interaction with the Model) for {@code FindContentCommand}.
 */
public class FindContentCommandTest {
    private Model model = new ModelManager(getTypicalContactsList(), new UserPrefs(),
            getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());
    private Model expectedModel = new ModelManager(getTypicalContactsList(), new UserPrefs(),
            getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());

    @BeforeEach
    public void init() {
        model.setDictionaryContentConfig(TypicalDictionaryContentConfig.getTypicalDictionaryContentConfig());
        expectedModel.setDictionaryContentConfig(TypicalDictionaryContentConfig.getTypicalDictionaryContentConfig());
    }

    public void equals() {
        ContentContainsKeywordsPredicate firstContentPredicate =
                new ContentContainsKeywordsPredicate(Collections.singletonList("first"));
        ContentContainsKeywordsPredicate secondContentPredicate =
                new ContentContainsKeywordsPredicate(Collections.singletonList("second"));


        FindContentCommand findFirstCommand = new FindContentCommand(firstContentPredicate);
        FindContentCommand findSecondCommand = new FindContentCommand(secondContentPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindContentCommand findFirstCommandCopy = new FindContentCommand(firstContentPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_allContentFound() {

        ContentContainsKeywordsPredicate contentPredicate = prepareContentPredicate(" ");

        expectedModel.updateFilteredContentList(contentPredicate);

        assertEquals(TypicalContent.getTypicalContent(), model.getFilteredContentList());
    }

    /**
     * Parses {@code userInput} into a {@code ContentContainsKeywordsPredicate}.
     */
    private ContentContainsKeywordsPredicate prepareContentPredicate(String userInput) {
        return new ContentContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
