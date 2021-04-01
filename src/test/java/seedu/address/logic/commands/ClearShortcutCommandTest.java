package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalShortcuts.getTypicalShortcutLibrary;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.shortcut.ShortcutLibrary;
import seedu.address.storage.Authentication;

public class ClearShortcutCommandTest {

    @Test
    public void execute_emptyShortcutLibrary_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearShortcutCommand(), model, ClearShortcutCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonShortcutLibrary_success() {
        Model model = new ModelManager(new AddressBook(), new UserPrefs(), new Authentication(),
                getTypicalShortcutLibrary());
        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(), new Authentication(),
                getTypicalShortcutLibrary());
        expectedModel.setShortcutLibrary(new ShortcutLibrary());

        assertCommandSuccess(new ClearShortcutCommand(), model, ClearShortcutCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
