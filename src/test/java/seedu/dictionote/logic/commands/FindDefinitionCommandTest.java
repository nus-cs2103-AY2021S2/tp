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
import seedu.dictionote.model.dictionary.DefinitionContainsKeywordsPredicate;
import seedu.dictionote.testutil.TypicalDefinition;
import seedu.dictionote.testutil.TypicalDictionaryContentConfig;

/**
 * Contains integration tests (interaction with the Model) for {@code FindContentCommand}.
 */
public class FindDefinitionCommandTest {
    private Model model = new ModelManager(getTypicalContactsList(), new UserPrefs(),
            getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());
    private Model expectedModel = new ModelManager(getTypicalContactsList(), new UserPrefs(),
            getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());

    @BeforeEach
    public void init() {
        model.setDictionaryContentConfig(TypicalDictionaryContentConfig.getTypicalDictionaryContentConfig());
        expectedModel.setDictionaryContentConfig(TypicalDictionaryContentConfig.getTypicalDictionaryContentConfig());
    }

    @Test
    public void equals() {
        DefinitionContainsKeywordsPredicate firstDefinitionPredicate =
                new DefinitionContainsKeywordsPredicate(Collections.singletonList("first"));
        DefinitionContainsKeywordsPredicate secondDefinitionPredicate =
                new DefinitionContainsKeywordsPredicate(Collections.singletonList("second"));


        FindDefinitionCommand findFirstCommand = new FindDefinitionCommand(firstDefinitionPredicate);
        FindDefinitionCommand findSecondCommand = new FindDefinitionCommand(secondDefinitionPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindDefinitionCommand findFirstCommandCopy = new FindDefinitionCommand(firstDefinitionPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_allDefinitionFound() {

        DefinitionContainsKeywordsPredicate definitionPredicate = prepareDefinitionPredicate(" ");

        expectedModel.updateFilteredDefinitionList(definitionPredicate);

        assertEquals(TypicalDefinition.getTypicalDefinition(), model.getFilteredDefinitionList());
    }

    /**
     * Parses {@code userInput} into a {@code ContentContainsKeywordsPredicate}.
     */
    private DefinitionContainsKeywordsPredicate prepareDefinitionPredicate(String userInput) {
        return new DefinitionContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
