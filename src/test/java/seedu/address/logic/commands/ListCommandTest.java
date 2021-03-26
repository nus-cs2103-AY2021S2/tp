package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.clear.ClearPropertyCommand;
import seedu.address.logic.commands.list.ListAppointmentCommand;
import seedu.address.logic.commands.list.ListPropertyCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PropertyBook;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalModelManager;

import static seedu.address.commons.core.Messages.MESSAGE_PROPERTIES_LISTED_OVERVIEW;
import static seedu.address.commons.core.Messages.MESSAGE_PROPERTIES_LISTED_OVERVIEW_SINGULAR;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalProperties.getTypicalProperties;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBookWithClient;

class ListCommandTest {
    private Model model = TypicalModelManager.getTypicalModelManager();
    private Model expectedModel = TypicalModelManager.getTypicalModelManager();

    @Test
    public void listPropertyTest() {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW, getTypicalProperties().size() + 1);
        //clear property list
        expectedModel.updateFilteredPropertyList(a -> false);

        assertCommandSuccess(new ListPropertyCommand(), model, ClearPropertyCommand.MESSAGE_SUCCESS,
                expectedModel);
    }
}
