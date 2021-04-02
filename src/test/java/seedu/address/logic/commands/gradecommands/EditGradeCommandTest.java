package seedu.address.logic.commands.gradecommands;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.tutorcommands.ClearCommand;
import seedu.address.model.*;
import seedu.address.model.grade.Grade;
import seedu.address.testutil.EditGradeDescriptorBuilder;
import seedu.address.testutil.GradeBuilder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT_NAME_PHYSICS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADED_ITEM_PHYSICS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADE_PHYSICS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.address.testutil.TypicalBudgets.getTypicalBudgetBook;
import static seedu.address.testutil.TypicalGrades.getTypicalGradeBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalReminders.getTypicalReminderTracker;
import static seedu.address.testutil.TypicalSchedules.getTypicalScheduleTracker;
import static seedu.address.testutil.TypicalTutors.getTypicalTutorBook;


/**
 * Contains integration tests (interaction with the Model) and unit tests for EditGradeCommand.
 */
public class EditGradeCommandTest {

    private Model model = new ModelManager(getTypicalTutorBook(), new UserPrefs(),
            getTypicalAppointmentBook(), getTypicalBudgetBook(), getTypicalGradeBook(),
            getTypicalScheduleTracker(), getTypicalReminderTracker());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Grade editedGrade = new GradeBuilder().build();
        EditGradeCommand.EditGradeDescriptor descriptor = new EditGradeDescriptorBuilder(editedGrade).build();
        EditGradeCommand editGradeCommand = new EditGradeCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditGradeCommand.MESSAGE_EDIT_GRADE_SUCCESS, editedGrade);

        Model expectedModel = new ModelManager(model.getTutorBook(), new UserPrefs(),
                model.getAppointmentBook(), model.getBudgetBook(), new GradeBook(model.getGradeBook()), model.getScheduleTracker(),
                model.getReminderTracker());
        expectedModel.setGrade(model.getFilteredGradeList().get(0), editedGrade);

        assertCommandSuccess(editGradeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastGrade = Index.fromOneBased(model.getFilteredGradeList().size());
        Grade lastGrade = model.getFilteredGradeList().get(indexLastGrade.getZeroBased());
        System.out.println(lastGrade);

        GradeBuilder gradeInList = new GradeBuilder(lastGrade);
        Grade editedGrade = gradeInList.withSubject(VALID_SUBJECT_NAME_PHYSICS)
                .withGradedItem(VALID_GRADED_ITEM_PHYSICS)
                .withGrade(VALID_GRADE_PHYSICS).build();

        EditGradeCommand.EditGradeDescriptor descriptor = new EditGradeDescriptorBuilder()
                .withSubject(VALID_SUBJECT_NAME_PHYSICS)
                .withGradedItem(VALID_GRADED_ITEM_PHYSICS)
                .withGrade(VALID_GRADE_PHYSICS).build();
        EditGradeCommand editGradeCommand = new EditGradeCommand(indexLastGrade, descriptor);

        String expectedMessage = String.format(EditGradeCommand.MESSAGE_EDIT_GRADE_SUCCESS
                , editedGrade);

        Model expectedModel = new ModelManager(model.getTutorBook(), new UserPrefs(),
                model.getAppointmentBook(), model.getBudgetBook(), new GradeBook(model.getGradeBook()), model.getScheduleTracker(),
                model.getReminderTracker());
        expectedModel.setGrade(lastGrade, editedGrade);

        assertCommandSuccess(editGradeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditGradeCommand editGradeCommand =
                new EditGradeCommand(INDEX_FIRST_PERSON, new EditGradeCommand.EditGradeDescriptor());
        Grade editedGrade = model.getFilteredGradeList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditGradeCommand.MESSAGE_EDIT_GRADE_SUCCESS, editedGrade);

        Model expectedModel = new ModelManager(model.getTutorBook(), new UserPrefs(),
                model.getAppointmentBook(), model.getBudgetBook(), new GradeBook(model.getGradeBook()), model.getScheduleTracker(),
                model.getReminderTracker());

        assertCommandSuccess(editGradeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_throwsCommandException() {
        Grade firstGrade = model.getFilteredGradeList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditGradeCommand.EditGradeDescriptor descriptor =
                new EditGradeDescriptorBuilder(firstGrade).build();
        EditGradeCommand editGradeCommand = new EditGradeCommand(INDEX_FIRST_PERSON, descriptor);
        assertThrows(CommandException.class, EditGradeCommand.MESSAGE_DUPLICATE_GRADE,
                () -> editGradeCommand.execute(model));
    }

    @Test
    public void execute_invalidGradeIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredGradeList().size() + 1);
        EditGradeCommand.EditGradeDescriptor descriptor =
                new EditGradeDescriptorBuilder()
                        .withSubject(VALID_SUBJECT_NAME_SCIENCE)
                        .withGradedItem(VALID_GRADED_ITEM_SCIENCE).build();
        EditGradeCommand editGradeCommand = new EditGradeCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editGradeCommand, model, Messages.MESSAGE_INVALID_GRADE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditGradeCommand standardGradeCommand =
                new EditGradeCommand(INDEX_FIRST_PERSON, DESC_MATHS);

        // same values -> returns true
        EditGradeCommand.EditGradeDescriptor copyDescriptor =
                new EditGradeCommand.EditGradeDescriptor(DESC_MATHS);
        EditGradeCommand commandWithSameValues = new EditGradeCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardGradeCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardGradeCommand.equals(standardGradeCommand));

        // null -> returns false
        assertFalse(standardGradeCommand.equals(null));

        // different types -> returns false
        assertFalse(standardGradeCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardGradeCommand.equals(new EditGradeCommand(INDEX_SECOND_PERSON, DESC_MATHS)));

        // different descriptor -> returns false
        assertFalse(standardGradeCommand.equals(new EditGradeCommand(INDEX_FIRST_PERSON, DESC_SCIENCE)));
    }
}
