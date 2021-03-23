package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_CLASH_PRANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_PRANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_STH;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalMeetings.MEETING_CLASH_PRANK;
import static seedu.address.testutil.TypicalMeetings.MEETING_PRANK;
import static seedu.address.testutil.TypicalMeetings.MEETING_STH;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class ScheduleCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_validIndexUnfilteredList_success() {

        Person personToSchedule = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        PersonBuilder personInList = new PersonBuilder(personToSchedule);
        Person editedPerson = personInList.withMeeting(VALID_MEETING_STH).build();

        ScheduleCommand scheduleCommand = new ScheduleCommand(INDEX_SECOND_PERSON, MEETING_STH);
        String expectedMessage =
                String.format(ScheduleCommand.MESSAGE_SCHEDULE_PERSON_SUCCESS, personToSchedule.getName(), MEETING_STH);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToSchedule, editedPerson);

        assertCommandSuccess(scheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ScheduleCommand scheduleCommand = new ScheduleCommand(outOfBoundIndex, MEETING_STH);

        assertCommandFailure(scheduleCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToSchedule = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        PersonBuilder personInList = new PersonBuilder(personToSchedule);
        Person editedPerson = personInList.withMeeting(VALID_MEETING_STH).build();

        ScheduleCommand scheduleCommand = new ScheduleCommand(INDEX_FIRST_PERSON, MEETING_STH);
        String expectedMessage =
                String.format(ScheduleCommand.MESSAGE_SCHEDULE_PERSON_SUCCESS, personToSchedule.getName(), MEETING_STH);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToSchedule, editedPerson);
        assertEquals(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(scheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        ScheduleCommand scheduleCommand = new ScheduleCommand(outOfBoundIndex, MEETING_STH);

        assertCommandFailure(scheduleCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_meetingClash_throwsCommandException() {
        ScheduleCommand scheduleCommand = new ScheduleCommand(INDEX_FIRST_PERSON, MEETING_PRANK);
        Person personToSchedule = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        PersonBuilder personInList = new PersonBuilder(personToSchedule);
        Person editedPerson = personInList.withMeeting(VALID_MEETING_PRANK).build();
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToSchedule, editedPerson);
        String expectedMessage =
                String.format(ScheduleCommand.MESSAGE_SCHEDULE_PERSON_SUCCESS,
                        personToSchedule.getName(), MEETING_PRANK);
        assertCommandSuccess(scheduleCommand, model, expectedMessage, expectedModel);


        ScheduleCommand scheduleCommandSecond = new ScheduleCommand(INDEX_SECOND_PERSON, MEETING_CLASH_PRANK);
        String expectedMessageSecond =
                String.format(ScheduleCommand.MESSAGE_SCHEDULE_CONFLICT_FAILURE, VALID_MEETING_CLASH_PRANK);
        assertCommandFailure(scheduleCommandSecond, model, expectedMessageSecond);
    }

    @Test
    void testEquals() {
        ScheduleCommand scheduleFirstSthCommand = new ScheduleCommand(INDEX_FIRST_PERSON, MEETING_STH);
        ScheduleCommand scheduleSecondSthCommand = new ScheduleCommand(INDEX_SECOND_PERSON, MEETING_STH);
        ScheduleCommand scheduleFirstPrankCommand = new ScheduleCommand(INDEX_FIRST_PERSON, MEETING_PRANK);

        // same object -> returns true
        assertTrue(scheduleFirstSthCommand.equals(scheduleFirstSthCommand));

        // same values -> returns true
        ScheduleCommand scheduleFirstSthCommandCopy = new ScheduleCommand(INDEX_FIRST_PERSON, MEETING_STH);
        assertTrue(scheduleFirstSthCommand.equals(scheduleFirstSthCommandCopy));

        // different types -> returns false
        assertFalse(scheduleFirstSthCommandCopy.equals(1));

        // null -> returns false
        assertFalse(scheduleFirstSthCommandCopy.equals(null));

        // different person -> returns false
        assertFalse(scheduleFirstSthCommandCopy.equals(scheduleSecondSthCommand));

        // different meeting -> returns false
        assertFalse(scheduleFirstSthCommandCopy.equals(scheduleFirstPrankCommand));
    }
}
