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
import seedu.address.model.filter.TutorFilter;
import seedu.address.testutil.TypicalFilters;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddPersonFilterCommand.
 */
public class AddPersonFilterCommandTest {
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
    }

    @Test
    public void execute_allFieldsSpecified_success() {
        TutorFilter tutorFilter = TypicalFilters.getBensonTwoSubjectsFilter();
        AddPersonFilterCommand addPersonFilterCommand = new AddPersonFilterCommand(tutorFilter);

        expectedModel.addTutorFilter(tutorFilter);
        String expectedMessage = String.format(AddPersonFilterCommand.MESSAGE_SUCCESS, tutorFilter);

        assertCommandSuccess(addPersonFilterCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecified_success() {
        TutorFilter tutorFilter = TypicalFilters.getBensonNoSubjectsFilter();
        AddPersonFilterCommand addPersonFilterCommand = new AddPersonFilterCommand(tutorFilter);

        expectedModel.addTutorFilter(tutorFilter);
        String expectedMessage = String.format(AddPersonFilterCommand.MESSAGE_SUCCESS, tutorFilter);

        assertCommandSuccess(addPersonFilterCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecified_success() {
        TutorFilter tutorFilter = new TutorFilter();
        AddPersonFilterCommand addPersonFilterCommand = new AddPersonFilterCommand(tutorFilter);

        String expectedMessage = String.format(AddPersonFilterCommand.MESSAGE_SUCCESS, tutorFilter);

        assertCommandSuccess(addPersonFilterCommand, model, expectedMessage, expectedModel);
    }
}
