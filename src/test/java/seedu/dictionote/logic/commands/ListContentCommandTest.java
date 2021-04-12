package seedu.dictionote.logic.commands;

import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.dictionote.testutil.TypicalContacts.getTypicalContactsList;
import static seedu.dictionote.testutil.TypicalContent.getTypicalDictionary;
import static seedu.dictionote.testutil.TypicalDefinition.getTypicalDefinitionBook;
import static seedu.dictionote.testutil.TypicalNotes.getTypicalNoteBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.enums.UiActionOption;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.ModelManager;
import seedu.dictionote.model.UserPrefs;
import seedu.dictionote.testutil.TypicalDictionaryContentConfig;

public class ListContentCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalContactsList(), new UserPrefs(),
                getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());
        expectedModel = new ModelManager(model.getContactsList(), new UserPrefs(),
                getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());
        model.setDictionaryContentConfig(TypicalDictionaryContentConfig.getTypicalDictionaryContentConfig());
        expectedModel.setDictionaryContentConfig(TypicalDictionaryContentConfig.getTypicalDictionaryContentConfig());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListContentCommand(), model, ListContentCommand.MESSAGE_SUCCESS,
            UiAction.OPEN, UiActionOption.DICTIONARY_LIST, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        //showContentAtIndex(model, INDEX_FIRST_Content);
        assertCommandSuccess(new ListContentCommand(), model, ListContentCommand.MESSAGE_SUCCESS,
            UiAction.OPEN, UiActionOption.DICTIONARY_LIST, expectedModel);
    }
}
