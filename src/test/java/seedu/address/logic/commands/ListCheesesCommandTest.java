package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModels.getTypicalChim;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCheesesCommand.
 */
public class ListCheesesCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalChim(), new UserPrefs());
        expectedModel = new ModelManager(model.getChim(), new UserPrefs());
        expectedModel.setPanelToCheeseList();
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        int unassignedCount = expectedModel.getFilteredCheeseListUnassignedCount();
        int totalCheese = expectedModel.getFilteredCheeseList().size();
        String expectedMessage = String.format(
                ListCheesesCommand.SUMMARY_MESSAGE,
                totalCheese,
                totalCheese - unassignedCount,
                unassignedCount
        );
        assertCommandSuccess(new ListCheesesCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_listIsNotFiltered_showsEverything() {
        int unassignedCount = expectedModel.getFilteredCheeseListUnassignedCount();
        int totalCheese = expectedModel.getFilteredCheeseList().size();
        String expectedMessage = String.format(
                ListCheesesCommand.SUMMARY_MESSAGE,
                totalCheese,
                totalCheese - unassignedCount,
                unassignedCount
        );
        assertCommandSuccess(new ListCheesesCommand(), model, expectedMessage, expectedModel);
    }
}
