package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModelManager.getTypicalModelManager;
import static seedu.address.testutil.TypicalProperties.getTypicalProperties;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;

public class ListPropertyCommandTest {
    private Model model = getTypicalModelManager();
    private Model expectedModel = getTypicalModelManager();

    @Test
    public void listPropertyTest() {
        //clear property list
        model.updateFilteredPropertyList(a -> false);

        assertCommandSuccess(new ListPropertyCommand(), model, ListPropertyCommand.MESSAGE_SUCCESS, expectedModel);
        assertEquals(getTypicalProperties(), model.getFilteredPropertyList());
    }
}
