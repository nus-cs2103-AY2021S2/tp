package seedu.address.logic.commands.gradecommands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.tutorcommands.ListCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.address.testutil.TypicalBudgets.getTypicalBudgetBook;
import static seedu.address.testutil.TypicalGrades.getTypicalGradeBook;
import static seedu.address.testutil.TypicalReminders.getTypicalReminderTracker;
import static seedu.address.testutil.TypicalSchedules.getTypicalScheduleTracker;
import static seedu.address.testutil.TypicalTutors.getTypicalTutorBook;

public class ListGradeCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {

        model = new ModelManager(getTypicalTutorBook(), new UserPrefs(),
                getTypicalAppointmentBook(), getTypicalBudgetBook(), getTypicalGradeBook(),
                getTypicalScheduleTracker(), getTypicalReminderTracker());

        expectedModel = new ModelManager(getTypicalTutorBook(), new UserPrefs(),
                getTypicalAppointmentBook(), getTypicalBudgetBook(), model.getGradeBook(),
                getTypicalScheduleTracker(), getTypicalReminderTracker());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListGradeCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
