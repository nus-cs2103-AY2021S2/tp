package seedu.cakecollate.logic.commands;

import static seedu.cakecollate.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.cakecollate.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.cakecollate.testutil.TypicalOrders.getTypicalCakeCollate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.cakecollate.model.Model;
import seedu.cakecollate.model.ModelManager;
import seedu.cakecollate.model.UserPrefs;
import seedu.cakecollate.model.order.Order;
import seedu.cakecollate.testutil.OrderBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalCakeCollate(), new UserPrefs());
    }

    @Test
    public void execute_newOrder_success() {
        Order validOrder = new OrderBuilder().build();

        Model expectedModel = new ModelManager(model.getCakeCollate(), new UserPrefs());
        expectedModel.addOrder(validOrder);

        assertCommandSuccess(new AddCommand(validOrder), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validOrder), expectedModel);
    }

    @Test
    public void execute_duplicateOrder_throwsCommandException() {
        Order orderInList = model.getCakeCollate().getOrderList().get(0);
        assertCommandFailure(new AddCommand(orderInList), model, AddCommand.MESSAGE_DUPLICATE_ORDER);
    }

}
