package seedu.timeforwheels.logic.commands;

import static seedu.timeforwheels.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.timeforwheels.testutil.TypicalCustomers.getTypicalDeliveryList;

import org.junit.jupiter.api.Test;

import seedu.timeforwheels.model.DeliveryList;
import seedu.timeforwheels.model.Model;
import seedu.timeforwheels.model.ModelManager;
import seedu.timeforwheels.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyDeliveryList_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyDeliveryList_success() {
        Model model = new ModelManager(getTypicalDeliveryList(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalDeliveryList(), new UserPrefs());
        expectedModel.setDeliveryList(new DeliveryList());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
