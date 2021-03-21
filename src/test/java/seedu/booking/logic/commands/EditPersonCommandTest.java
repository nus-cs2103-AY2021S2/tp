package seedu.booking.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_EMAIL_AMY_GMAIL;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_EMAIL_BOB_GMAIL;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.booking.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.booking.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.booking.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.booking.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.booking.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.booking.testutil.TypicalPersons.getTypicalBookingSystem;

import org.junit.jupiter.api.Test;

import seedu.booking.commons.core.Messages;
import seedu.booking.logic.commands.EditPersonCommand.EditPersonDescriptor;
import seedu.booking.model.BookingSystem;
import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;
import seedu.booking.model.UserPrefs;
import seedu.booking.model.person.Email;
import seedu.booking.model.person.Person;
import seedu.booking.testutil.EditPersonCommandDescriptorBuilder;
import seedu.booking.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditPersonCommand.
 */
public class EditPersonCommandTest {

    private Model model = new ModelManager(getTypicalBookingSystem(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person editedPerson = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonCommandDescriptorBuilder(editedPerson).build();
        EditPersonCommand editPersonCommand = new EditPersonCommand(new Email(VALID_EMAIL_AMY_GMAIL), descriptor);

        String expectedMessage = String.format(EditPersonCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new BookingSystem(model.getBookingSystem()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editPersonCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditPersonCommand editPersonCommand =
                new EditPersonCommand(new Email(VALID_EMAIL_AMY_GMAIL), new EditPersonDescriptor());
        Person editedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditPersonCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new BookingSystem(model.getBookingSystem()), new UserPrefs());

        assertCommandSuccess(editPersonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();
        EditPersonCommand editPersonCommand = new EditPersonCommand(new Email(VALID_EMAIL_AMY_GMAIL),
                new EditPersonCommandDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditPersonCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new BookingSystem(model.getBookingSystem()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editPersonCommand, model, expectedMessage, expectedModel);
    }

    /*@Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditPersonDescriptor descriptor = new EditPersonCommandDescriptorBuilder(firstPerson).build();
        EditPersonCommand editPersonCommand = new EditPersonCommand(new Email("amy@gmail.com"), descriptor);

        assertCommandFailure(editPersonCommand, model, EditPersonCommand.MESSAGE_DUPLICATE_PERSON);
    }*/

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in address book
        Person personInList = model.getBookingSystem().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditPersonCommand editPersonCommand = new EditPersonCommand(new Email(VALID_EMAIL_AMY_GMAIL),
                new EditPersonCommandDescriptorBuilder(personInList).build());

        assertCommandFailure(editPersonCommand, model, EditPersonCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonEmailUnfilteredList_failure() {
        Email unknownEmail = new Email("unknown@email.com");
        EditPersonDescriptor descriptor = new EditPersonCommandDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditPersonCommand editPersonCommand = new EditPersonCommand(unknownEmail, descriptor);

        assertCommandFailure(editPersonCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_EMAIL);
    }


    @Test
    public void equals() {
        final EditPersonCommand standardCommand = new EditPersonCommand(new Email(VALID_EMAIL_AMY_GMAIL),
                new EditPersonCommandDescriptorBuilder().withName(VALID_NAME_AMY).build());

        // same values -> returns true
        EditPersonDescriptor copyDescriptor =
                new EditPersonDescriptor(new EditPersonCommandDescriptorBuilder().withName(VALID_NAME_AMY).build());
        EditPersonCommand commandWithSameValues =
                new EditPersonCommand(new Email(VALID_EMAIL_AMY_GMAIL), copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different email -> returns false
        assertFalse(standardCommand.equals(new EditPersonCommand(new Email(VALID_EMAIL_BOB_GMAIL),
                new EditPersonCommandDescriptorBuilder().withName(VALID_NAME_AMY).build())));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditPersonCommand(new Email(VALID_EMAIL_BOB_GMAIL),
                new EditPersonCommandDescriptorBuilder().withName(VALID_NAME_BOB).build())));
    }

}
