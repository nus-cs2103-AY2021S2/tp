package seedu.address.logic.commands.statscommands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.getTypicalStudentBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class StatsCommandAllTest {
    private Model expectedModel = new ModelManager(getTypicalStudentBook(), new UserPrefs());

    @Test
    public void equals() {
        StatsCommandAll firstCommand = new StatsCommandAll();
        StatsCommandAll secondCommand = new StatsCommandAll();
        assertTrue(firstCommand.equals(secondCommand));
    }

    @Test
    public void execute_typicalStudents_successful() {
        String expectedOutput = "Percentage Vaccinated:\n"
                + "   PGPH: No available data\n"
                + "   PGPR: No available data\n"
                + "   KE7H: No available data\n"
                + "   SH: No available data\n"
                + "   KRH: No available data\n"
                + "   TH: No available data\n"
                + "   EH: No available data\n"
                + "   RH: No available data\n"
                + "   RVRC: 33.33%\n"
                + "   YNC: No available data\n"
                + "   TC: No available data\n"
                + "   CAPT: No available data\n"
                + "   RC4: No available data\n"
                + "   USP: 100.00%\n"
                + "   UTR: No available data\n"
                + "   DOES NOT LIVE ON CAMPUS: No available data\n"
                + "   FASS: No available data\n"
                + "   BIZ: 0.00%\n"
                + "   COM: 0.00%\n"
                + "   SCALE: 100.00%\n"
                + "   DEN: No available data\n"
                + "   SDE: No available data\n"
                + "   DNUS: 0.00%\n"
                + "   ENG: 100.00%\n"
                + "   ISEP: No available data\n"
                + "   LAW: No available data\n"
                + "   MED: 0.00%\n"
                + "   MUSIC: No available data\n"
                + "   SPH: No available data\n"
                + "   SPP: No available data\n"
                + "   SCI: No available data\n"
                + "   USP: 100.00%\n"
                + "   YNC: No available data\n";
        StatsCommandAll statsCommandAll = new StatsCommandAll();
        assertCommandSuccess(statsCommandAll, expectedModel, expectedOutput, expectedModel);
    }

}
