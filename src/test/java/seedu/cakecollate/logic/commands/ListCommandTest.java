package seedu.cakecollate.logic.commands;

import static seedu.cakecollate.commons.core.Messages.MESSAGE_ORDERS_LISTED_OVERVIEW;
import static seedu.cakecollate.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.cakecollate.logic.commands.CommandTestUtil.showOrderAtIndex;
import static seedu.cakecollate.testutil.TypicalIndexes.INDEX_FIRST_ORDER;
import static seedu.cakecollate.testutil.TypicalOrders.getTypicalCakeCollate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.cakecollate.model.Model;
import seedu.cakecollate.model.ModelManager;
import seedu.cakecollate.model.UserPrefs;
import seedu.cakecollate.testutil.TypicalOrderItems;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalCakeCollate(), new UserPrefs(),
                TypicalOrderItems.getTypicalOrderItemsModel());
        expectedModel = new ModelManager(model.getCakeCollate(), new UserPrefs(),
                TypicalOrderItems.getTypicalOrderItemsModel());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        String expectedMessage = String.format(MESSAGE_ORDERS_LISTED_OVERVIEW, model.getFilteredOrderList().size());
        assertCommandSuccess(new ListCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        String expectedMessage = String.format(MESSAGE_ORDERS_LISTED_OVERVIEW, model.getFilteredOrderList().size());
        showOrderAtIndex(model, INDEX_FIRST_ORDER);
        assertCommandSuccess(new ListCommand(), model, expectedMessage, expectedModel);
    }
}
