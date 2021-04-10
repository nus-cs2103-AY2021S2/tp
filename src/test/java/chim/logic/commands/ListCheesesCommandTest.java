package chim.logic.commands;

import static chim.logic.commands.CommandTestUtil.assertCommandSuccess;
import static chim.testutil.TypicalModels.getTypicalChim;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chim.model.Model;
import chim.model.ModelManager;
import chim.model.UserPrefs;

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
