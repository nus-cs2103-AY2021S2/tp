package seedu.address.logic.commands.appointment;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showAppointmentAtIndex;
import static seedu.address.testutil.TypicalAppObjects.getTypicalAppointmentSchedule;
import static seedu.address.testutil.TypicalAppObjects.getTypicalDoctorRecords;
import static seedu.address.testutil.TypicalAppObjects.getTypicalPatientRecords;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_IN_LIST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.AppointmentSchedule;
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
        model = new ModelManager(getTypicalPatientRecords(), getTypicalDoctorRecords(),
                getTypicalAppointmentSchedule(), new UserPrefs());
        expectedModel = new ModelManager(
                new AddressBook<>(model.getPatientRecords()),
                new AddressBook<>(model.getDoctorRecords()),
                new AppointmentSchedule(model.getAppointmentSchedule()),
                new UserPrefs(model.getUserPrefs())
        );
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListAppointmentCommand(), model,
                Messages.MESSAGE_LIST_APPOINTMENT_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showAppointmentAtIndex(model, INDEX_FIRST_IN_LIST);
        assertCommandSuccess(new ListAppointmentCommand(), model,
                Messages.MESSAGE_LIST_APPOINTMENT_SUCCESS, expectedModel);
    }
}
