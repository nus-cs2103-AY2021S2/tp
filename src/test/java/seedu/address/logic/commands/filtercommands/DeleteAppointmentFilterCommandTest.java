package seedu.address.logic.commands.filtercommands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.address.testutil.TypicalGrades.getTypicalGradeBook;
import static seedu.address.testutil.TypicalReminders.getTypicalReminderTracker;
import static seedu.address.testutil.TypicalSchedules.getTypicalScheduleTracker;
import static seedu.address.testutil.TypicalTutors.getTypicalTutorBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.BudgetBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.filter.AppointmentFilter;
import seedu.address.testutil.TypicalFilters;

public class DeleteAppointmentFilterCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTutorBook(), new UserPrefs(),
                getTypicalAppointmentBook(), new BudgetBook(), getTypicalGradeBook(),
                getTypicalScheduleTracker(), getTypicalReminderTracker());

        expectedModel = new ModelManager(getTypicalTutorBook(), new UserPrefs(),
                getTypicalAppointmentBook(), new BudgetBook(), getTypicalGradeBook(),
                getTypicalScheduleTracker(), getTypicalReminderTracker());

        AppointmentFilter appointmentFilter = TypicalFilters.getMathFilter();
        model.addAppointmentFilter(appointmentFilter);
        expectedModel.addAppointmentFilter(appointmentFilter);
    }

    @Test
    public void execute_allFieldsSpecified_success() {
        AppointmentFilter appointmentFilter = TypicalFilters.getMathFilter();
        DeleteAppointmentFilterCommand deleteAppointmentFilterCommand = new DeleteAppointmentFilterCommand(
                appointmentFilter);

        expectedModel.removeAppointmentFilter(appointmentFilter);
        String expectedMessage = String.format(
                DeleteAppointmentFilterCommand.MESSAGE_SUCCESS, appointmentFilter);

        assertCommandSuccess(deleteAppointmentFilterCommand, model, expectedMessage, expectedModel);
    }
}
