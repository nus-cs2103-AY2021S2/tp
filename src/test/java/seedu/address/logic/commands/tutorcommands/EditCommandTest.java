package seedu.address.logic.commands.tutorcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.address.testutil.TypicalBudgets.getTypicalBudgetBook;
import static seedu.address.testutil.TypicalGrades.getTypicalGradeBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalReminders.getTypicalReminderTracker;
import static seedu.address.testutil.TypicalSchedules.getTypicalScheduleTracker;
import static seedu.address.testutil.TypicalTutors.getTypicalTutorBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TutorBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.tutor.Tutor;
import seedu.address.testutil.EditTutorDescriptorBuilder;
import seedu.address.testutil.TutorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalTutorBook(), new UserPrefs(),
            getTypicalAppointmentBook(), getTypicalBudgetBook(), getTypicalGradeBook(),
            getTypicalScheduleTracker(), getTypicalReminderTracker());

    @Test
    public void execute_editTutorName_objectNotEquals() {
        Tutor editedTutor = new TutorBuilder().build();
        EditCommand.EditTutorDescriptor descriptor = new EditTutorDescriptorBuilder(editedTutor).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TUTOR_SUCCESS, editedTutor);

        Model expectedModel = new ModelManager(new TutorBook(model.getTutorBook()), new UserPrefs(),
                model.getAppointmentBook(), model.getBudgetBook(), model.getGradeBook(), model.getScheduleTracker(),
                model.getReminderTracker());
        expectedModel.setTutor(model.getFilteredTutorList().get(0), editedTutor);

        assertNotEquals(model, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredTutorList().size());
        Tutor lastTutor = model.getFilteredTutorList().get(indexLastPerson.getZeroBased());

        TutorBuilder personInList = new TutorBuilder(lastTutor);
        Tutor editedTutor = personInList.withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditCommand.EditTutorDescriptor descriptor = new EditTutorDescriptorBuilder()
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TUTOR_SUCCESS, editedTutor);

        Model expectedModel = new ModelManager(new TutorBook(model.getTutorBook()), new UserPrefs(),
                model.getAppointmentBook(), model.getBudgetBook(), model.getGradeBook(), model.getScheduleTracker(),
                model.getReminderTracker());
        expectedModel.setTutor(lastTutor, editedTutor);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, new EditCommand.EditTutorDescriptor());
        Tutor editedTutor = model.getFilteredTutorList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TUTOR_SUCCESS, editedTutor);

        Model expectedModel = new ModelManager(new TutorBook(model.getTutorBook()), new UserPrefs(),
                model.getAppointmentBook(), model.getBudgetBook(), model.getGradeBook(), model.getScheduleTracker(),
                model.getReminderTracker());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Tutor tutorInFilteredList = model.getFilteredTutorList().get(INDEX_FIRST_PERSON.getZeroBased());
        Tutor editedTutor =
                new TutorBuilder(tutorInFilteredList).withPhone(VALID_PHONE_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditTutorDescriptorBuilder().withPhone(VALID_PHONE_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TUTOR_SUCCESS, editedTutor);

        Model expectedModel = new ModelManager(new TutorBook(model.getTutorBook()), new UserPrefs(),
                model.getAppointmentBook(), model.getBudgetBook(), model.getGradeBook(), model.getScheduleTracker(),
                model.getReminderTracker());
        expectedModel.setTutor(model.getFilteredTutorList().get(0), editedTutor);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Tutor firstTutor = model.getFilteredTutorList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditCommand.EditTutorDescriptor descriptor = new EditTutorDescriptorBuilder(firstTutor).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);
        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_TUTOR);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in address book
        Tutor tutorInList = model.getTutorBook().getTutorList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditTutorDescriptorBuilder(tutorInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_TUTOR);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTutorList().size() + 1);
        EditCommand.EditTutorDescriptor descriptor = new EditTutorDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_TUTOR_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTutorBook().getTutorList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditTutorDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_TUTOR_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_AMY);

        // same values -> returns true
        EditCommand.EditTutorDescriptor copyDescriptor = new EditCommand.EditTutorDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PERSON, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PERSON, DESC_BOB)));
    }

}
