package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.getTypicalSochedule;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.EventComparator;

public class SortEventCommandTest {
    private Model model = new ModelManager(getTypicalSochedule(), new UserPrefs());

    @Test
    public void execute_valid_success() {
        for (String comparingVar : EventComparator.getAcceptedVar()) {
            SortEventCommand sortEventCommand = new SortEventCommand(comparingVar);

            String expectedMessage = String.format(SortEventCommand.MESSAGE_SORT_TASK_SUCCESS);

            ModelManager expectedModel = new ModelManager(model.getSochedule(), new UserPrefs());
            expectedModel.sortEvents(comparingVar);

            assertCommandSuccess(sortEventCommand, model, expectedMessage, expectedModel);
        }
    }

    // invalid inputs already handled on parsing sort_event command
}
