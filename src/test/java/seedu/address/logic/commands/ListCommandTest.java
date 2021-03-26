package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.clear.ClearPropertyCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PropertyBook;
import seedu.address.model.UserPrefs;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;

class ListCommandTest {
    @Test
    public void execute_emptyPropertyBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearPropertyCommand(), model, ClearPropertyCommand.MESSAGE_SUCCESS,
                expectedModel);
    }

    @Test
    public void execute_nonEmptyPropertyBook_success() {
        Model model = new ModelManager(getTypicalPropertyBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalPropertyBook(), new UserPrefs());
        expectedModel.setPropertyBook(new PropertyBook());

        assertCommandSuccess(new ClearPropertyCommand(), model, ClearPropertyCommand.MESSAGE_SUCCESS,
                expectedModel);
    }
}
