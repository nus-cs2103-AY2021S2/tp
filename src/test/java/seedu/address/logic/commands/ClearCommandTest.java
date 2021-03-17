package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCustomers.getTypicalDeliveryList;

import org.junit.jupiter.api.Test;

import seedu.address.model.DeliveryList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

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
