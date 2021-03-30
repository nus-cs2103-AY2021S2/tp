package seedu.address.model.colabfolderhistory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.uicommands.ShowTodayUiCommand;
import seedu.address.model.ColabFolder;
import seedu.address.testutil.TypicalColabFolder;

class SavedStateTest {

    @Test
    public void constructor_success() {
        ColabFolder colabFolder = TypicalColabFolder.getTypicalColabFolder();

        // Constructor with colab folder and command result
        CommandResult commandResult = new CommandResult("Feedback", new ShowTodayUiCommand());
        SavedState savedStateWithCommandResult = new SavedState(colabFolder, commandResult);

        assertEquals(savedStateWithCommandResult.getColabFolder(), colabFolder);
        assertEquals(savedStateWithCommandResult.getCommandResult(), commandResult);

        // Constructor with only colab folder
        SavedState savedStateWithoutCommandResult = new SavedState(colabFolder);

        assertEquals(savedStateWithoutCommandResult.getColabFolder(), colabFolder);
        assertNull(savedStateWithoutCommandResult.getCommandResult());
    }
}
