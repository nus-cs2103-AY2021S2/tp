package seedu.address.logic.commands.statscommands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.statscommands.StatsCommandNus.MESSAGE_STATS_FAILURE;
import static seedu.address.testutil.TypicalStudents.getTypicalStudentBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.StudentBook;
import seedu.address.model.UserPrefs;

public class StatsCommandNusTest {
    private Model expectedModel = new ModelManager(getTypicalStudentBook(), new UserPrefs());

    @Test
    public void equals() {
        StatsCommandNus firstCommand = new StatsCommandNus();
        StatsCommandNus secondCommand = new StatsCommandNus();
        assertTrue(firstCommand.equals(secondCommand));
    }

    @Test
    public void execute_typicalStudents_successful() {
        String expectedOutput = "Percentage NUS vaccinated: 42.86%";
        StatsCommandNus statsCommandNus = new StatsCommandNus();
        assertCommandSuccess(statsCommandNus, expectedModel, expectedOutput, expectedModel);
    }

    @Test
    public void execute_typicalStudents_noAvailableData() {
        StatsCommandNus statsCommandNus = new StatsCommandNus();
        Model emptyModel = new ModelManager(new StudentBook(), new UserPrefs());
        assertCommandFailure(statsCommandNus, emptyModel, MESSAGE_STATS_FAILURE);
    }

}
