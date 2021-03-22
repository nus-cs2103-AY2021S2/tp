package seedu.address.logic.commands.medical;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.medical.Appointment;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class AddAppointmentCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAppointmentCommand(null, null));
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        LocalDateTime dateTime = LocalDateTime.of(2021, 12, 12, 18, 00);
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(outOfBoundIndex, dateTime);

        assertCommandFailure(addAppointmentCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        LocalDateTime dateTime = LocalDateTime.of(2021, 12, 12, 18, 00);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person personWithAppointment = new PersonBuilder(personInFilteredList).build();
        Appointment appointment = new Appointment(personWithAppointment, dateTime);
        personWithAppointment.addAppointment(appointment);
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(INDEX_FIRST_PERSON, dateTime);

        String expectedMessage = String.format(AddAppointmentCommand.MESSAGE_SUCCESS, appointment);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), personWithAppointment);

        assertCommandSuccess(addAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        LocalDateTime dateTime = LocalDateTime.of(2021, 12, 12, 18, 00);
        LocalDateTime otherDateTime = LocalDateTime.of(2021, 11, 11, 18, 00);
        final AddAppointmentCommand standardCommand = new AddAppointmentCommand(INDEX_FIRST_PERSON, dateTime);

        // same values -> returns true
        LocalDateTime copyDateTime = LocalDateTime.of(2021, 12, 12, 18, 00);
        AddAppointmentCommand commandWithSameValues = new AddAppointmentCommand(INDEX_FIRST_PERSON, copyDateTime);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddAppointmentCommand(INDEX_SECOND_PERSON, dateTime)));

        // different dateTime -> returns false
        assertFalse(standardCommand.equals(new AddAppointmentCommand(INDEX_FIRST_PERSON, otherDateTime)));
    }
}
