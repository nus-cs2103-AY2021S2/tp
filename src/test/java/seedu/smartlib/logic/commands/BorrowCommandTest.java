package seedu.smartlib.logic.commands;

import static seedu.smartlib.testutil.Assert.assertThrows;
import static seedu.smartlib.testutil.TypicalModels.getTypicalSmartLib;

import org.junit.jupiter.api.Test;

import seedu.smartlib.model.Model;
import seedu.smartlib.model.ModelManager;
import seedu.smartlib.model.UserPrefs;

class BorrowCommandTest {

    private Model model = new ModelManager(getTypicalSmartLib(), new UserPrefs());

    @Test
    public void constructor_nullRecord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new BorrowCommand(null));
    }

}
