package seedu.cakecollate.logic.commands;

import static seedu.cakecollate.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.cakecollate.testutil.TypicalOrders.getTypicalCakeCollate;

import org.junit.jupiter.api.Test;

import seedu.cakecollate.model.CakeCollate;
import seedu.cakecollate.model.Model;
import seedu.cakecollate.model.ModelManager;
import seedu.cakecollate.model.OrderItems;
import seedu.cakecollate.model.UserPrefs;
import seedu.cakecollate.testutil.TypicalOrderItems;

public class ClearCommandTest {

    @Test
    public void execute_emptyCakeCollate_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyCakeCollate_success() {
        Model model = new ModelManager(getTypicalCakeCollate(), new UserPrefs(),
                TypicalOrderItems.getTypicalOrderItemsModel());
        Model expectedModel = new ModelManager(getTypicalCakeCollate(), new UserPrefs(),
                TypicalOrderItems.getTypicalOrderItemsModel());
        expectedModel.setCakeCollate(new CakeCollate());
        expectedModel.setOrderItems(new OrderItems());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
