package seedu.address.model.colabfolderhistory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.uicommands.ShowTodayUiCommand;
import seedu.address.model.ColabFolder;
import seedu.address.testutil.TypicalColabFolder;

class SavedStateTest {

    @Test
    public void constructor_success() {
        ColabFolder colabFolder = TypicalColabFolder.getTypicalColabFolder();
        CommandResult commandResult = new CommandResult("Feedback", new ShowTodayUiCommand());
        SavedState savedState = new SavedState(colabFolder, commandResult);

        assertEquals(savedState.getColabFolder(), colabFolder);
        assertEquals(savedState.getCommandResult(), commandResult);
    }
}
