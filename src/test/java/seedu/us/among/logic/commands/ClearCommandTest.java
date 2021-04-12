package seedu.us.among.logic.commands;

import static seedu.us.among.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.us.among.model.EndpointList;
import seedu.us.among.model.Model;
import seedu.us.among.model.ModelManager;
import seedu.us.among.model.UserPrefs;
import seedu.us.among.testutil.TypicalEndpoints;

public class ClearCommandTest {

    @Test
    public void execute_emptyEndpointList_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyEndpointList_success() {
        Model model = new ModelManager(TypicalEndpoints.getTypicalEndpointList(), new UserPrefs());
        Model expectedModel = new ModelManager(TypicalEndpoints.getTypicalEndpointList(), new UserPrefs());
        expectedModel.setEndpointList(new EndpointList());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
