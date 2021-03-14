package seedu.address.logic.commands.statscommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.statscommands.StatsCommandResidence.MESSAGE_STATS_FAILURE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.SchoolResidence;

public class StatsCommandResidenceTest {
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        StatsCommandResidence firstCommand = new StatsCommandResidence(new SchoolResidence("RVRC"));
        StatsCommandResidence secondCommand = new StatsCommandResidence(new SchoolResidence("RVRC"));
        assertTrue(firstCommand.equals(secondCommand));

        StatsCommandResidence thirdCommand = new StatsCommandResidence(new SchoolResidence("CAPT"));
        StatsCommandResidence fourthCommand = new StatsCommandResidence(new SchoolResidence("KRH"));
        assertFalse(thirdCommand.equals(fourthCommand));
    }

    // test no avaliable data
    @Test
    public void execute_typicalPersons_noAvailableData() {
        StatsCommandResidence statsCommandResidence = new StatsCommandResidence(new SchoolResidence("UTR"));
        assertCommandFailure(statsCommandResidence, expectedModel, MESSAGE_STATS_FAILURE);
    }

    // test success
    @Test
    public void execute_typicalPersons_success() {
        String expectedOutput = "Percentage RVRC vaccinated: 33.33%";
        StatsCommandResidence statsCommandResidence = new StatsCommandResidence(new SchoolResidence("RVRC"));
        assertCommandSuccess(statsCommandResidence, expectedModel, expectedOutput, expectedModel);
    }
}
