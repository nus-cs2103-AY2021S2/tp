package seedu.dictionote.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.dictionote.logic.commands.CommandTestUtil.showContentAtIndex;
import static seedu.dictionote.logic.commands.ShowDictionaryContentCommand.MESSAGE_SHOW_CONTENT_SUCCESS;
import static seedu.dictionote.testutil.TypicalContacts.getTypicalContactsList;
import static seedu.dictionote.testutil.TypicalContent.getTypicalDictionary;
import static seedu.dictionote.testutil.TypicalDefinition.getTypicalDefinitionBook;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_FIRST_CONTENT;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_FIRST_NOTE;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_SECOND_CONTENT;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_SECOND_NOTE;
import static seedu.dictionote.testutil.TypicalNotes.getTypicalNoteBook;

import org.junit.jupiter.api.Test;

import seedu.dictionote.commons.core.Messages;
import seedu.dictionote.commons.core.index.Index;
import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.enums.UiActionOption;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.ModelManager;
import seedu.dictionote.model.UserPrefs;
import seedu.dictionote.model.dictionary.DisplayableContent;
import seedu.dictionote.testutil.TypicalDictionaryContentConfig;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ShowDictionaryContentCommand}.
 */
public class ShowDictionaryContentCommandTest {
    private Model model = new ModelManager(getTypicalContactsList(), new UserPrefs(),
        getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        model.setDictionaryContentConfig(TypicalDictionaryContentConfig.getTypicalDictionaryContentConfig());

        DisplayableContent contentToShow =
            model.getFilteredCurrentDictionaryList().get(INDEX_FIRST_NOTE.getZeroBased());
        ShowDictionaryContentCommand showDictionaryContentCommand = new ShowDictionaryContentCommand(INDEX_FIRST_NOTE);

        String expectedMessage = String.format(MESSAGE_SHOW_CONTENT_SUCCESS, contentToShow);

        ModelManager expectedModel = new ModelManager(model.getContactsList(), new UserPrefs(),
            getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());
        expectedModel.setDictionaryContentConfig(TypicalDictionaryContentConfig.getTypicalDictionaryContentConfig());
        expectedModel.showDictionaryContent(contentToShow);

        assertCommandSuccess(showDictionaryContentCommand, model, expectedMessage,
            UiAction.OPEN, UiActionOption.DICTIONARY_CONTENT, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        model.setDictionaryContentConfig(TypicalDictionaryContentConfig.getTypicalDictionaryContentConfig());

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredNoteList().size() + 1);
        ShowDictionaryContentCommand showDictionaryContentCommand = new ShowDictionaryContentCommand(outOfBoundIndex);

        assertCommandFailure(showDictionaryContentCommand, model,
            Messages.MESSAGE_INVALID_DICTIONARY_CONTENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        model.setDictionaryContentConfig(TypicalDictionaryContentConfig.getTypicalDictionaryContentConfig());

        showContentAtIndex(model, INDEX_FIRST_CONTENT);

        DisplayableContent contentToShow =
            model.getFilteredCurrentDictionaryList().get(INDEX_FIRST_NOTE.getZeroBased());
        ShowDictionaryContentCommand showDictionaryContentCommand = new ShowDictionaryContentCommand(INDEX_FIRST_NOTE);

        String expectedMessage = String.format(MESSAGE_SHOW_CONTENT_SUCCESS, contentToShow);

        Model expectedModel = new ModelManager(model.getContactsList(), new UserPrefs(),
            getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());
        expectedModel.setDictionaryContentConfig(TypicalDictionaryContentConfig.getTypicalDictionaryContentConfig());
        expectedModel.showDictionaryContent(contentToShow);

        assertCommandSuccess(showDictionaryContentCommand, model, expectedMessage,
            UiAction.OPEN, UiActionOption.DICTIONARY_CONTENT, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        model.setDictionaryContentConfig(TypicalDictionaryContentConfig.getTypicalDictionaryContentConfig());

        showContentAtIndex(model, INDEX_FIRST_CONTENT);

        Index outOfBoundIndex = INDEX_SECOND_CONTENT;
        // ensures that outOfBoundIndex is still in bounds of dictionote book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getDictionary().getContentList().size());

        ShowDictionaryContentCommand showDictionaryContentCommand = new ShowDictionaryContentCommand(outOfBoundIndex);

        assertCommandFailure(showDictionaryContentCommand, model,
            Messages.MESSAGE_INVALID_DICTIONARY_CONTENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ShowDictionaryContentCommand showFirstCommand = new ShowDictionaryContentCommand(INDEX_FIRST_NOTE);
        ShowDictionaryContentCommand showSecondCommand = new ShowDictionaryContentCommand(INDEX_SECOND_NOTE);

        // same object -> returns true
        assertTrue(showFirstCommand.equals(showFirstCommand));

        // same values -> returns true
        ShowDictionaryContentCommand showFirstCommandCopy = new ShowDictionaryContentCommand(INDEX_FIRST_NOTE);
        assertTrue(showFirstCommand.equals(showFirstCommandCopy));

        // different types -> returns false
        assertFalse(showFirstCommand.equals(1));

        // null -> returns false
        assertFalse(showFirstCommand.equals(null));

        // different note -> returns false
        assertFalse(showFirstCommand.equals(showSecondCommand));
    }
}
