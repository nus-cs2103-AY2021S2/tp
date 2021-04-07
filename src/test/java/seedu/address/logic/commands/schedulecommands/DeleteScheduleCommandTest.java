package seedu.address.logic.commands.schedulecommands;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_SCHEDULE_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.testutil.TypicalModel;

public class DeleteScheduleCommandTest {

    private final Model model = TypicalModel.getTypicalModel();

    @Test
    public void execute_invalidDeleteIndex() {
        DeleteScheduleCommand deleteScheduleCommand = new DeleteScheduleCommand(Index.fromOneBased(1000000));

        assertCommandFailure(deleteScheduleCommand, model, MESSAGE_INVALID_SCHEDULE_DISPLAYED_INDEX);
    }
}
