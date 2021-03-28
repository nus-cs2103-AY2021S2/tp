package seedu.address.testutil;

import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.address.testutil.TypicalBudgets.getTypicalBudgetBook;
import static seedu.address.testutil.TypicalGrades.getTypicalGradeBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalSchedules.getTypicalScheduleTracker;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * A utility class to create new ModelManager.
 */
public class ModelManagerBuilder {

    /**
     * Returns an {@code ModelManager} with all the typical books/trackers.
     */
    public static ModelManager getModelManager() {
        return new ModelManager(getTypicalAddressBook(), new UserPrefs(),
                getTypicalAppointmentBook(), getTypicalBudgetBook(), getTypicalGradeBook(),
                getTypicalScheduleTracker());
    }

    /**
     * Returns an {@code ModelManager} with values copied from another Model.
     */
    public static ModelManager getModelManager(Model model) {
        return new ModelManager(model.getAddressBook(), new UserPrefs(),
                model.getAppointmentBook(), model.getBudgetBook(), model.getGradeBook(),
                model.getScheduleTracker());
    }
}
