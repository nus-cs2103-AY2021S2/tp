package seedu.timeforwheels.logic.commands;

import static seedu.timeforwheels.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.timeforwheels.testutil.TypicalCustomers.getTypicalDeliveryList;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.timeforwheels.model.Model;
import seedu.timeforwheels.model.ModelManager;
import seedu.timeforwheels.model.UserPrefs;
import seedu.timeforwheels.model.customer.AttributeContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for CompletedCommand.
 */
public class CompletedCommandTest {

    private final String done = "[✓]";
    private Model model = new ModelManager(getTypicalDeliveryList(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalDeliveryList(), new UserPrefs());

    @Test
    public void execute_listIsCompleted_showsSameList() {
        String expectedMessage = "Listed below are the completed deliveries";
        String[] completed = done.split("\\s+");
        AttributeContainsKeywordsPredicate complete =
                new AttributeContainsKeywordsPredicate(Arrays.asList(completed));
        expectedModel.updateFilteredCustomerList(complete);
        assertCommandSuccess(new CompletedCommand(), model, expectedMessage, expectedModel);
    }
}
