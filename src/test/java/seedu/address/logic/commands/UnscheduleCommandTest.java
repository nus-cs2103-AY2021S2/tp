package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_PRANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_STH;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class UnscheduleCommandTest {

    @Test
    public void execute_nonEmptyAddressBook_clearAllMeeting() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        Person personToSchedule;
        PersonBuilder personInList;
        Person editedPerson;

        personToSchedule = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        personInList = new PersonBuilder(personToSchedule);
        editedPerson = personInList.withMeeting(VALID_MEETING_STH).build();
        model.setPerson(personToSchedule, editedPerson);

        personToSchedule = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        personInList = new PersonBuilder(personToSchedule);
        editedPerson = personInList.withMeeting(VALID_MEETING_PRANK).build();
        model.setPerson(personToSchedule, editedPerson);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        assertCommandSuccess(new UnscheduleCommand(null, true, false), model,
                UnscheduleCommand.MESSAGE_UNSCHEDULE_ALL_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_removeExpiredMeeting() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        Person personToSchedule;
        PersonBuilder personInList;
        Person editedPerson;

        String pastMeeting = "Past @ "
                + LocalDateTime.MIN.withYear(1900).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String futureMeeting = "Future @ "
                + LocalDateTime.MAX.withYear(9999).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        personToSchedule = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        personInList = new PersonBuilder(personToSchedule);
        editedPerson = personInList.withMeeting(pastMeeting).build();
        model.setPerson(personToSchedule, editedPerson);

        personToSchedule = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        personInList = new PersonBuilder(personToSchedule);
        editedPerson = personInList.withMeeting(futureMeeting).build();
        model.setPerson(personToSchedule, editedPerson);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToSchedule, editedPerson);

        assertCommandSuccess(new UnscheduleCommand(null, false, true), model,
                UnscheduleCommand.MESSAGE_UNSCHEDULE_EXPIRED_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_removeSelectedMeeting() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());


        Person personToScheduleA = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        PersonBuilder personInListA = new PersonBuilder(personToScheduleA);
        Person editedPersonA = personInListA.withMeeting(VALID_MEETING_PRANK).build();
        model.setPerson(personToScheduleA, editedPersonA);

        Person personToScheduleB = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        PersonBuilder personInListB = new PersonBuilder(personToScheduleB);
        Person editedPersonB = personInListB.withMeeting(VALID_MEETING_STH).build();
        model.setPerson(personToScheduleB, editedPersonB);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToScheduleB, editedPersonB);

        assertCommandSuccess(new UnscheduleCommand(INDEX_FIRST_PERSON, false, false), model,
                String.format(UnscheduleCommand.MESSAGE_UNSCHEDULE_ONE_SUCCESS, personToScheduleA.getName()),
                expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_throwCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandFailure(new UnscheduleCommand(INDEX_FIRST_PERSON, false, false), model,
                Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    void testEquals() {
        UnscheduleCommand unscheduleFirstCommand = new UnscheduleCommand(INDEX_FIRST_PERSON, false, false);
        UnscheduleCommand unscheduleSecondCommand = new UnscheduleCommand(INDEX_SECOND_PERSON, false, false);
        UnscheduleCommand unscheduleAllCommand = new UnscheduleCommand(null, true, false);
        UnscheduleCommand unscheduleExpiredCommand = new UnscheduleCommand(null, true, false);

        // same object -> returns true
        assertTrue(unscheduleFirstCommand.equals(unscheduleFirstCommand));

        // same values -> returns true
        UnscheduleCommand unscheduleFirstCommandCopy = new UnscheduleCommand(INDEX_FIRST_PERSON, false, false);
        assertTrue(unscheduleFirstCommand.equals(unscheduleFirstCommandCopy));

        // different types -> returns false
        assertFalse(unscheduleFirstCommand.equals(1));

        // null -> returns false
        assertFalse(unscheduleFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(unscheduleFirstCommand.equals(unscheduleSecondCommand));

        // different attributes -> returns false
        assertFalse(unscheduleSecondCommand.equals(unscheduleAllCommand));
        assertFalse(unscheduleSecondCommand.equals(unscheduleExpiredCommand));
    }
}
