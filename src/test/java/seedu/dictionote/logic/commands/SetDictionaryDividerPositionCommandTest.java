package seedu.dictionote.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.dictionote.logic.commands.SetDictionaryDividerPositionCommand.MESSAGE_SET_DIVIDER_SUCCESS;
import static seedu.dictionote.testutil.TypicalContacts.getTypicalContactsList;
import static seedu.dictionote.testutil.TypicalContent.getTypicalDictionary;
import static seedu.dictionote.testutil.TypicalDefinition.getTypicalDefinitionBook;
import static seedu.dictionote.testutil.TypicalNotes.getTypicalNoteBook;
import static seedu.dictionote.testutil.TypicalUiActions.VALID_UI_POSITION;

import org.junit.jupiter.api.Test;

import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.enums.UiActionOption;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.ModelManager;
import seedu.dictionote.model.UserPrefs;


public class SetDictionaryDividerPositionCommandTest {
    private Model model = new ModelManager(getTypicalContactsList(), new UserPrefs(),
        getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());
    private Model expectedModel = new ModelManager(getTypicalContactsList(), new UserPrefs(),
    getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());

    @Test
    public void execute_set_position_success() {
        for (int i = 0; i < VALID_UI_POSITION.length; i++) {
            CommandResult expectedCommandResult = new CommandResult(
                MESSAGE_SET_DIVIDER_SUCCESS + VALID_UI_POSITION[i], UiAction.OPEN,
                UiActionOption.LIST);
            expectedModel.getGuiSettings().setDictionarySplitRatio(
                VALID_UI_POSITION[i] / SetDividerPositionCommand.NORMALIZE_RATIO);

            assertCommandSuccess(new SetDictionaryDividerPositionCommand(VALID_UI_POSITION[i]), model,
                expectedCommandResult, expectedModel);
            assertEquals(model.getGuiSettings().getDictionarySplitRatio(),
                VALID_UI_POSITION[i] / SetDividerPositionCommand.NORMALIZE_RATIO);
        }
    }
}
