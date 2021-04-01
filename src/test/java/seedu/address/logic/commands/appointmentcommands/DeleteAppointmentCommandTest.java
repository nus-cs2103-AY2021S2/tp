package seedu.address.logic.commands.appointmentcommands;

import static seedu.address.commons.core.Messages.MESSAGE_DELETE_APPOINTMENT_FAILURE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.address.testutil.TypicalBudgets.getTypicalBudgetBook;
import static seedu.address.testutil.TypicalGrades.getTypicalGradeBook;
import static seedu.address.testutil.TypicalReminders.getTypicalReminderTracker;
import static seedu.address.testutil.TypicalSchedules.getTypicalScheduleTracker;
import static seedu.address.testutil.TypicalTutors.getTypicalTutorBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


public class DeleteAppointmentCommandTest {

    private Model model = new ModelManager(getTypicalTutorBook(), new UserPrefs(), getTypicalAppointmentBook(),
            getTypicalBudgetBook(), getTypicalGradeBook(), getTypicalScheduleTracker(), getTypicalReminderTracker());


    @Test
    public void execute_invalidDeleteIndex() {
        DeleteAppointmentCommand deleteAppointmentCommand =
                new DeleteAppointmentCommand(Index.fromOneBased(1000000));

        assertCommandFailure(deleteAppointmentCommand, model,
                MESSAGE_DELETE_APPOINTMENT_FAILURE);

    }


}
