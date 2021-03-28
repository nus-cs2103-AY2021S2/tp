package seedu.timeforwheels.logic.commands;

import static seedu.timeforwheels.logic.commands.CommandTestUtil.assertCommandModelSuccess;
import static seedu.timeforwheels.testutil.TypicalCustomers.getTypicalDeliveryList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.timeforwheels.model.Model;
import seedu.timeforwheels.model.ModelManager;
import seedu.timeforwheels.model.UserPrefs;

public class StatsCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalDeliveryList(), new UserPrefs());
        expectedModel = new ModelManager(model.getDeliveryList(), new UserPrefs());
    }

    @Test
    public void execute_listStats_showsSameList() {
        assertCommandModelSuccess(new StatsCommand(), model, expectedModel);
    }

}