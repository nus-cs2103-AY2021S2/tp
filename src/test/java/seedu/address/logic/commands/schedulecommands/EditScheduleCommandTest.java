package seedu.address.logic.commands.schedulecommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_SCHEDULE_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_UNABLE_TO_EDIT_PAST_SCHEDULE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SCHEDULE_MATHS;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SCHEDULE_SCIENCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_DATE_TIME_FROM_THREE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_DATE_TIME_TO_THREE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_DESCRIPTION_THREE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_TITLE_THREE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showScheduleAtIndex;
import static seedu.address.logic.commands.schedulecommands.EditScheduleCommand.MESSAGE_DUPLICATE_SCHEDULE;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.address.testutil.TypicalBudgets.getTypicalBudgetBook;
import static seedu.address.testutil.TypicalGrades.getTypicalGradeBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalModel.ModelType.SCHEDULETRACKER;
import static seedu.address.testutil.TypicalReminders.getTypicalReminderTracker;
import static seedu.address.testutil.TypicalSchedules.getTypicalPastSchedules;
import static seedu.address.testutil.TypicalSchedules.getTypicalSchedules;
import static seedu.address.testutil.TypicalTutors.getTypicalTutorBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.schedulecommands.EditScheduleCommand.EditScheduleDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.ScheduleTracker;
import seedu.address.testutil.EditScheduleDescriptorBuilder;
import seedu.address.testutil.ScheduleBuilder;
import seedu.address.testutil.TypicalModel;

public class EditScheduleCommandTest {

    private final Model model = TypicalModel.getTypicalModel();

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Schedule editedSchedule = new ScheduleBuilder().build();
        EditScheduleDescriptor descriptor = new EditScheduleDescriptorBuilder(editedSchedule).build();
        EditScheduleCommand editCommand = new EditScheduleCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditScheduleCommand.MESSAGE_EDIT_SCHEDULE_SUCCESS, editedSchedule);

        Model expectedModel = TypicalModel.getTypicalModel(model, SCHEDULETRACKER);
        expectedModel.setSchedule(model.getFilteredScheduleList().get(0), editedSchedule);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredScheduleList().size());
        Schedule lastSchedule = model.getFilteredScheduleList().get(indexLastPerson.getZeroBased());

        ScheduleBuilder scheduleInList = new ScheduleBuilder(lastSchedule);
        Schedule editedSchedule = scheduleInList.withTitle(VALID_SCHEDULE_TITLE_THREE)
                .withDescription(VALID_SCHEDULE_DESCRIPTION_THREE)
                .withTimeFrom(VALID_SCHEDULE_DATE_TIME_FROM_THREE)
                .withTimeTo(VALID_SCHEDULE_DATE_TIME_TO_THREE).build();

        EditScheduleDescriptor descriptor = new EditScheduleDescriptorBuilder().withTitle(VALID_SCHEDULE_TITLE_THREE)
                .withDescription(VALID_SCHEDULE_DESCRIPTION_THREE).withTimeFrom(VALID_SCHEDULE_DATE_TIME_FROM_THREE)
                .withTimeTo(VALID_SCHEDULE_DATE_TIME_TO_THREE).build();
        EditScheduleCommand editCommand = new EditScheduleCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(EditScheduleCommand.MESSAGE_EDIT_SCHEDULE_SUCCESS, editedSchedule);

        Model expectedModel = TypicalModel.getTypicalModel(model, SCHEDULETRACKER);
        expectedModel.setSchedule(lastSchedule, editedSchedule);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditScheduleCommand editCommand = new EditScheduleCommand(INDEX_FIRST_PERSON, new EditScheduleDescriptor());
        Schedule editedSchedule = model.getFilteredScheduleList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditScheduleCommand.MESSAGE_EDIT_SCHEDULE_SUCCESS, editedSchedule);

        Model expectedModel = TypicalModel.getTypicalModel(model, SCHEDULETRACKER);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showScheduleAtIndex(model, INDEX_FIRST_PERSON);

        Schedule scheduleInFilteredList = model.getFilteredScheduleList().get(INDEX_FIRST_PERSON.getZeroBased());
        Schedule editedSchedule = new ScheduleBuilder(scheduleInFilteredList).withTitle(VALID_SCHEDULE_TITLE_THREE)
                .withTimeFrom(VALID_SCHEDULE_DATE_TIME_FROM_THREE).withTimeTo(VALID_SCHEDULE_DATE_TIME_TO_THREE)
                .build();
        EditScheduleCommand editCommand = new EditScheduleCommand(INDEX_FIRST_PERSON,
                new EditScheduleDescriptorBuilder().withTitle(VALID_SCHEDULE_TITLE_THREE)
                        .withTimeFrom(VALID_SCHEDULE_DATE_TIME_FROM_THREE).withTimeTo(VALID_SCHEDULE_DATE_TIME_TO_THREE)
                        .build());

        String expectedMessage = String.format(EditScheduleCommand.MESSAGE_EDIT_SCHEDULE_SUCCESS, editedSchedule);

        Model expectedModel = TypicalModel.getTypicalModel(model, SCHEDULETRACKER);
        expectedModel.setSchedule(model.getFilteredScheduleList().get(0), editedSchedule);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateScheduleFilteredList_failure() {
        showScheduleAtIndex(model, INDEX_FIRST_PERSON);

        // edit schedule in filtered list into a duplicate in schedule tracker
        Schedule scheduleInList = model.getScheduleTracker().getScheduleList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditScheduleCommand editCommand = new EditScheduleCommand(INDEX_FIRST_PERSON,
                new EditScheduleDescriptorBuilder(scheduleInList).build());

        assertCommandFailure(editCommand, model, MESSAGE_DUPLICATE_SCHEDULE);
    }

    @Test
    public void execute_editedPastSchedule_failure() {
        ScheduleTracker scheduleTracker = new ScheduleTracker();
        scheduleTracker.setSchedules(getTypicalPastSchedules());
        Model pastModel = new ModelManager(getTypicalTutorBook(), new UserPrefs(),
                getTypicalAppointmentBook(), getTypicalBudgetBook(), getTypicalGradeBook(),
                scheduleTracker, getTypicalReminderTracker());

        Schedule scheduleInList = pastModel.getScheduleTracker().getScheduleList()
                .get(INDEX_SECOND_PERSON.getZeroBased());
        EditScheduleCommand editCommand = new EditScheduleCommand(INDEX_FIRST_PERSON,
                new EditScheduleDescriptorBuilder(scheduleInList).build());

        assertCommandFailure(editCommand, pastModel, MESSAGE_UNABLE_TO_EDIT_PAST_SCHEDULE);
    }

    @Test
    public void execute_invalidIndexOutOfBounds_failure() {
        List<Schedule> scheduleList = getTypicalSchedules();
        Schedule schedule1 = scheduleList.get(0);
        EditScheduleDescriptor editAppointmentDescriptor = new EditScheduleDescriptor();
        editAppointmentDescriptor.setTitle(schedule1.getTitle());
        editAppointmentDescriptor.setTimeTo(schedule1.getTimeTo());
        editAppointmentDescriptor.setTimeFrom(schedule1.getTimeFrom());
        editAppointmentDescriptor.setDescription(schedule1.getDescription());

        EditScheduleCommand editScheduleCommand = new EditScheduleCommand(Index.fromOneBased(1000000),
                editAppointmentDescriptor);

        assertCommandFailure(editScheduleCommand, model, MESSAGE_INVALID_SCHEDULE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditScheduleCommand standardCommand = new EditScheduleCommand(INDEX_FIRST_PERSON, DESC_SCHEDULE_MATHS);

        // same values -> returns true
        EditScheduleDescriptor copyDescriptor = new EditScheduleDescriptor(DESC_SCHEDULE_MATHS);
        EditScheduleCommand commandWithSameValues = new EditScheduleCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ListScheduleCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditScheduleCommand(INDEX_SECOND_PERSON, DESC_SCHEDULE_MATHS)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditScheduleCommand(INDEX_FIRST_PERSON, DESC_SCHEDULE_SCIENCE)));
    }
}
