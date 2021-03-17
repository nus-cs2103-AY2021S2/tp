package seedu.address.logic.commands.appointment;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showAppointmentAtIndex;
import static seedu.address.testutil.TypicalAppObjects.getTypicalAddressBook;
import static seedu.address.testutil.TypicalAppObjects.getTypicalAppointmentSchedule;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_IN_LIST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListAppointmentCommand.
 */
public class ListAppointmentCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAppointmentSchedule(), getTypicalAddressBook(),
                new UserPrefs());
        expectedModel = new ModelManager(model.getAppointmentSchedule(), model.getAddressBook(),
                new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListAppointmentCommand(), model,
                ListAppointmentCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showAppointmentAtIndex(model, INDEX_FIRST_IN_LIST);
        assertCommandSuccess(new ListAppointmentCommand(), model,
                ListAppointmentCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
