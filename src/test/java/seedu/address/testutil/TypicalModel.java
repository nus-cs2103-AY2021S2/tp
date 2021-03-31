package seedu.address.testutil;

import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.address.testutil.TypicalBudgets.getTypicalBudgetBook;
import static seedu.address.testutil.TypicalGrades.getTypicalGradeBook;
import static seedu.address.testutil.TypicalSchedules.getTypicalScheduleTracker;
import static seedu.address.testutil.TypicalTutors.getTypicalTutorBook;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class TypicalModel {

    public static Model getTypicalModel() {
        return new ModelManager(getTypicalTutorBook(), new UserPrefs(),
                getTypicalAppointmentBook(), getTypicalBudgetBook(), getTypicalGradeBook(),
                getTypicalScheduleTracker());
    }

}
