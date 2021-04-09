package seedu.student.logic.commands.statscommands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.student.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.student.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.student.logic.commands.statscommands.StatsCommandNus.MESSAGE_STATS_FAILURE;
import static seedu.student.testutil.TypicalStudents.getTypicalStudentBook;

import org.junit.jupiter.api.Test;

import seedu.student.model.Model;
import seedu.student.model.ModelManager;
import seedu.student.model.StudentBook;
import seedu.student.model.UserPrefs;

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
