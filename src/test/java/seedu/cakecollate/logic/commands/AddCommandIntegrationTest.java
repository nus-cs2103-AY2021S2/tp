package seedu.cakecollate.logic.commands;

import static seedu.cakecollate.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.cakecollate.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.cakecollate.testutil.TypicalOrderItems.getTypicalOrderItemsModel;
import static seedu.cakecollate.testutil.TypicalOrders.getTypicalCakeCollate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.cakecollate.model.Model;
import seedu.cakecollate.model.ModelManager;
import seedu.cakecollate.model.UserPrefs;
import seedu.cakecollate.model.order.Order;
import seedu.cakecollate.testutil.AddOrderDescriptorBuilder;
import seedu.cakecollate.testutil.OrderBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalCakeCollate(), getTypicalOrderItemsModel(), new UserPrefs());
    }

    @Test
    public void execute_newOrder_success() {
        Order validOrder = new OrderBuilder().build();
        AddCommand.AddOrderDescriptor descriptor = new AddOrderDescriptorBuilder(validOrder).build();

        Model expectedModel = new ModelManager(model.getCakeCollate(), getTypicalOrderItemsModel(), new UserPrefs());
        expectedModel.addOrder(validOrder);

        assertCommandSuccess(new AddCommand(null, descriptor), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validOrder), expectedModel);
    }

    // todo with order index

    // todo with both

    @Test
    public void execute_duplicateOrder_throwsCommandException() {
        Order orderInList = model.getCakeCollate().getOrderList().get(0);
        AddCommand.AddOrderDescriptor descriptor = new AddOrderDescriptorBuilder(orderInList).build();
        assertCommandFailure(new AddCommand(null, descriptor), model, AddCommand.MESSAGE_DUPLICATE_ORDER);
    }

    // todo index list related
    // do after add command

}
