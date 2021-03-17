package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalOrders.getTypicalCakeCollate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.Order;
import seedu.address.testutil.OrderBuilder;

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
