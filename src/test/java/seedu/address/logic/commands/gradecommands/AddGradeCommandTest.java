package seedu.address.logic.commands.gradecommands;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.grade.Grade;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.address.testutil.TypicalBudgets.getTypicalBudgetBook;
import static seedu.address.testutil.TypicalGrades.MATHS_GRADE;
import static seedu.address.testutil.TypicalGrades.SCIENCE_GRADE;
import static seedu.address.testutil.TypicalGrades.PHYSICS_GRADE;
import static seedu.address.testutil.TypicalGrades.getTypicalGradeBook;
import static seedu.address.testutil.TypicalReminders.getTypicalReminderTracker;
import static seedu.address.testutil.TypicalSchedules.getTypicalScheduleTracker;
import static seedu.address.testutil.TypicalTutors.getTypicalTutorBook;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddGradeCommand.
 */
public class AddGradeCommandTest {

    private final Model model = new ModelManager(getTypicalTutorBook(),
            new UserPrefs(), getTypicalAppointmentBook(), getTypicalBudgetBook(),
            getTypicalGradeBook(), getTypicalScheduleTracker(), getTypicalReminderTracker());

    @Test
    public void constructor_nullGrade_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddGradeCommand(null));
    }

    @Test
    void execute_addValidGrade_commandSuccess() throws CommandException {

        AddGradeCommand addGradeCommand = new AddGradeCommand(PHYSICS_GRADE);
        CommandResult commandResult = addGradeCommand.execute(model);

        assertEquals(String.format(AddGradeCommand.MESSAGE_SUCCESS, PHYSICS_GRADE.toString()),
                commandResult.getFeedbackToUser());
    }

    @Test
    void execute_duplicateGrade_throwsCommandException() {
        Grade firstGrade=
                model.getGradeBook().getGradeList().get(0);
        AddGradeCommand addGradeCommand = new AddGradeCommand(firstGrade);
        assertThrows(CommandException.class, AddGradeCommand.MESSAGE_DUPLICATE_GRADE,
                () -> addGradeCommand.execute(model));
    }

    @Test
    public void equals() {
        AddGradeCommand addMathsCommand = new AddGradeCommand(MATHS_GRADE);
        AddGradeCommand addScienceCommand = new AddGradeCommand(SCIENCE_GRADE);

        // same object -> returns true
        assertEquals(addMathsCommand, addMathsCommand);

        // same values -> returns true
        AddGradeCommand addMathsCommandCopy = new AddGradeCommand(MATHS_GRADE);
        assertEquals(addMathsCommandCopy, addMathsCommand);

        // different types -> returns false
        assertNotEquals(addMathsCommand, 1);

        // null -> returns false
        assertNotEquals(addMathsCommand, null);

        // different Grade -> returns false
        assertNotEquals(addScienceCommand, addMathsCommand);
    }
}
