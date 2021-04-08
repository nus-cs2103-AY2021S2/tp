package seedu.address.testutil;

import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.address.testutil.TypicalBudgets.getTypicalBudgetBook;
import static seedu.address.testutil.TypicalGrades.getTypicalGradeBook;
import static seedu.address.testutil.TypicalReminders.getTypicalReminderTracker;
import static seedu.address.testutil.TypicalSchedules.getTypicalScheduleTracker;
import static seedu.address.testutil.TypicalTutors.getTypicalTutorBook;

import seedu.address.model.BudgetBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAppointmentBook;
import seedu.address.model.ReadOnlyGradeBook;
import seedu.address.model.ReadOnlyTutorBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.reminder.ReadOnlyReminderTracker;
import seedu.address.model.schedule.ReadOnlyScheduleTracker;

public class TypicalModel {
    public enum ModelType {
        ADDRESSBOOK,
        APPOINTMENTBOOK,
        BUDGETBOOK,
        GRADEBOOK,
        SCHEDULETRACKER,
        REMINDERTRACKER
    }

    public static Model getTypicalModel() {
        return new ModelManager(getTypicalTutorBook(), new UserPrefs(),
                getTypicalAppointmentBook(), getTypicalBudgetBook(), getTypicalGradeBook(),
                getTypicalScheduleTracker(), getTypicalReminderTracker());
    }

    /**
     * Returns an {@code ModelManager} with values copied from another Model.
     */
    public static ModelManager getTypicalModel(Model model, ModelType modelType) {
        ReadOnlyTutorBook ab = getTypicalTutorBook();
        ReadOnlyAppointmentBook apb = getTypicalAppointmentBook();
        BudgetBook bb = getTypicalBudgetBook();
        ReadOnlyGradeBook gb = getTypicalGradeBook();
        ReadOnlyScheduleTracker st = getTypicalScheduleTracker();
        ReadOnlyReminderTracker rt = getTypicalReminderTracker();

        switch (modelType) {
        case ADDRESSBOOK:
            ab = model.getTutorBook();
            break;
        case APPOINTMENTBOOK:
            apb = model.getAppointmentBook();
            break;
        case BUDGETBOOK:
            bb = model.getBudgetBook();
            break;
        case GRADEBOOK:
            gb = model.getGradeBook();
            break;
        case SCHEDULETRACKER:
            st = model.getScheduleTracker();
            break;
        case REMINDERTRACKER:
            rt = model.getReminderTracker();
            break;
        default:
            break;
        }

        return new ModelManager(ab, new UserPrefs(), apb, bb, gb, st, rt);
    }
}
