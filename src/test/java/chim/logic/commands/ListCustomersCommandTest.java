package chim.logic.commands;

import static chim.logic.commands.CommandTestUtil.assertCommandSuccess;
import static chim.logic.commands.CommandTestUtil.showCustomerAtIndex;
import static chim.testutil.TypicalIndexes.INDEX_FIRST_CUSTOMER;
import static chim.testutil.TypicalModels.getTypicalChim;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chim.model.Model;
import chim.model.ModelManager;
import chim.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCustomersCommand.
 */
public class ListCustomersCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalChim(), new UserPrefs());
        expectedModel = new ModelManager(model.getChim(), new UserPrefs());
        expectedModel.setPanelToCustomerList();
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        String expectedMessage = String.format(
                ListCustomersCommand.SUMMARY_MESSAGE,
                expectedModel.getFilteredCustomerList().size()
        );
        assertCommandSuccess(new ListCustomersCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showCustomerAtIndex(model, INDEX_FIRST_CUSTOMER);
        String expectedMessage = String.format(
                ListCustomersCommand.SUMMARY_MESSAGE,
                expectedModel.getFilteredCustomerList().size()
        );
        assertCommandSuccess(new ListCustomersCommand(), model, expectedMessage, expectedModel);
    }
}
