package seedu.address.model.colabfolderhistory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ViewTodayCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.uicommands.ShowTodayUiCommand;
import seedu.address.model.ColabFolder;
import seedu.address.model.ModelManager;
import seedu.address.model.colabfolderhistory.exceptions.NoRedoableStateException;
import seedu.address.model.colabfolderhistory.exceptions.NoUndoableStateException;
import seedu.address.testutil.TypicalColabFolder;

class ColabFolderHistoryTest {

    @Test
    public void undoAndRedo() {
        ColabFolder colabFolder = TypicalColabFolder.getTypicalColabFolder();
        ColabFolderHistory history = new ColabFolderHistory(colabFolder);

        // Nothing to undo or redo
        assertThrows(NoUndoableStateException.class, history::undo);
        assertThrows(NoRedoableStateException.class, history::redo);

        // One Commit
        CommandResult commandResult = new CommandResult("Feedback", new ShowTodayUiCommand());
        history.commit(new ColabFolder(), commandResult);

        // Redo throws error
        assertThrows(NoRedoableStateException.class, history::redo);

        // Undo success
        assertEquals(history.undo().getColabFolder(), colabFolder);

        // Undo throws error
        assertThrows(NoUndoableStateException.class, history::undo);

        // Redo success
        assertEquals(history.redo(), new SavedState(new ColabFolder(), commandResult));
    }

    @Test
    public void equals() throws CommandException {
        ColabFolder colabFolder = TypicalColabFolder.getTypicalColabFolder();
        ColabFolderHistory history = new ColabFolderHistory(colabFolder);

        // same values -> returns true
        ColabFolderHistory historyCopy = new ColabFolderHistory(colabFolder);
        history.equals(historyCopy);
        assertEquals(history, historyCopy);

        // same object -> returns true
        assertEquals(history, history);

        // null -> returns false
        assertNotEquals(history, null);

        // different type -> returns false
        assertNotEquals(history, 5);

        // different ColabFolder -> returns false
        ColabFolderHistory differentColabFolder = new ColabFolderHistory(new ColabFolder());
        assertNotEquals(history, differentColabFolder);

        // different pointer -> returns false
        ColabFolderHistory differentPointer = new ColabFolderHistory(colabFolder);
        differentPointer.commit(colabFolder, new ViewTodayCommand().execute(new ModelManager()));
        assertNotEquals(history, differentPointer);

    }
}
