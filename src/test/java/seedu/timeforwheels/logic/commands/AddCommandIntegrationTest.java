package seedu.timeforwheels.logic.commands;

import static seedu.timeforwheels.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.timeforwheels.testutil.TypicalCustomers.getTypicalDeliveryList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.timeforwheels.model.Model;
import seedu.timeforwheels.model.ModelManager;
import seedu.timeforwheels.model.UserPrefs;
import seedu.timeforwheels.model.customer.Customer;
import seedu.timeforwheels.testutil.CustomerBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalDeliveryList(), new UserPrefs());
    }

    @Test
    public void execute_newCustomer_success() {
        Customer validCustomer = new CustomerBuilder().build();

        Model expectedModel = new ModelManager(model.getDeliveryList(), new UserPrefs());
        expectedModel.addCustomer(validCustomer);

        assertCommandSuccess(new AddCommand(validCustomer), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validCustomer), expectedModel);
    }

    @Test
    public void execute_duplicateCustomer_throwsCommandException() {
        Customer customerInList = model.getDeliveryList().getCustomerList().get(0);
        assertCommandFailure(new AddCommand(customerInList), model, AddCommand.MESSAGE_DUPLICATE_CUSTOMER);
    }

}
