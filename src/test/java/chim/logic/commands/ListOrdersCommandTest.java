package chim.logic.commands;

import static chim.logic.commands.CommandTestUtil.assertCommandSuccess;
import static chim.testutil.TypicalModels.getTypicalChim;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chim.model.Model;
import chim.model.ModelManager;
import chim.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListOrdersCommand.
 */
public class ListOrdersCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalChim(), new UserPrefs());
        expectedModel = new ModelManager(model.getChim(), new UserPrefs());
        expectedModel.setPanelToOrderList();
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        int incompleteCount = expectedModel.getFilteredOrderListIncompleteCount();
        int totalOrder = expectedModel.getFilteredOrderList().size();
        String expectedMessage = String.format(
                ListOrdersCommand.SUMMARY_MESSAGE,
                expectedModel.getFilteredOrderList().size(),
                totalOrder - incompleteCount,
                incompleteCount
        );
        assertCommandSuccess(new ListOrdersCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        int incompleteCount = expectedModel.getFilteredOrderListIncompleteCount();
        int totalOrder = expectedModel.getFilteredOrderList().size();
        String expectedMessage = String.format(
                ListOrdersCommand.SUMMARY_MESSAGE,
                expectedModel.getFilteredOrderList().size(),
                totalOrder - incompleteCount,
                incompleteCount
        );
        assertCommandSuccess(new ListOrdersCommand(), model, expectedMessage, expectedModel);
    }
}
