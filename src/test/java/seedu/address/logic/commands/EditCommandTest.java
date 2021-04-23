package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showContactAtIndex;
import static seedu.address.testutil.TypicalContacts.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CONTACT;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditContactDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.AppointmentBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Name;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.predicate.NameContainsKeywordsPredicate;
import seedu.address.testutil.AppointmentBookBuilder;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.ContactBuilder;
import seedu.address.testutil.EditContactDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new AppointmentBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Contact editedContact = new ContactBuilder().build();
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder(editedContact).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_CONTACT, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_CONTACT_SUCCESS, editedContact);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new AppointmentBook(),
                new UserPrefs());
        expectedModel.setContact(model.getFilteredContactList().get(0), editedContact);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastContact = Index.fromOneBased(model.getFilteredContactList().size());
        Contact lastContact = model.getFilteredContactList().get(indexLastContact.getZeroBased());

        ContactBuilder contactInList = new ContactBuilder(lastContact);
        Contact editedContact = contactInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditContactDescriptor descriptor = new EditContactDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastContact, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_CONTACT_SUCCESS, editedContact);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new AppointmentBook(),
                new UserPrefs());
        expectedModel.setContact(lastContact, editedContact);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_CONTACT, new EditCommand.EditContactDescriptor());
        Contact editedContact = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_CONTACT_SUCCESS, editedContact);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new AppointmentBook(),
                new UserPrefs());

        expectedModel.orderContacts();

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        String firstContactName = model.getFilteredContactList().get(0).getName().toString();
        String firstContactFirstName = firstContactName.split(" ")[0]; // get first name of first contact
        ArrayList<String> findTerms = new ArrayList<>();

        findTerms.add(firstContactFirstName);
        model.updateFilteredContactList(new NameContainsKeywordsPredicate(findTerms)); // find contacts w findTerms

        Contact contactInFilteredList = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        Contact editedContact = new ContactBuilder(contactInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_CONTACT,
                new EditContactDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_CONTACT_SUCCESS, editedContact);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new AppointmentBook(),
                new UserPrefs());
        expectedModel.setContact(model.getFilteredContactList().get(0), editedContact);
        expectedModel.updateFilteredContactList(new NameContainsKeywordsPredicate(findTerms));

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateContactUnfilteredList_failure() {
        Contact firstContact = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        EditCommand.EditContactDescriptor descriptor = new EditContactDescriptorBuilder(firstContact).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_CONTACT, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_CONTACT);
    }

    @Test
    public void execute_duplicateContactFilteredList_failure() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);

        // edit contact in filtered list into a duplicate in address book
        Contact contactInList = model.getAddressBook().getContactList().get(INDEX_SECOND_CONTACT.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_CONTACT,
                new EditContactDescriptorBuilder(contactInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_CONTACT);
    }

    @Test
    public void execute_invalidContactIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredContactList().size() + 1);
        EditCommand.EditContactDescriptor descriptor = new EditContactDescriptorBuilder()
                .withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidContactIndexFilteredList_failure() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);
        Index outOfBoundIndex = INDEX_SECOND_CONTACT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getContactList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditContactDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    /**
     * Edit a contact that is also a contact of an appointment object.
     */
    @Test
    public void execute_changeContactBookAndAppointmentBook_success() {
        //initial set up
        Set<Contact> contacts = new HashSet<>();
        Contact contact = new ContactBuilder().build();
        contacts.add(contact);

        Appointment sampleAppointment = new AppointmentBuilder().build();
        Appointment appointment = new Appointment(sampleAppointment.getName(), sampleAppointment.getAddress(),
                sampleAppointment.getDateTime(), contacts, sampleAppointment.getTags());

        model.setAppointmentBook(new AppointmentBookBuilder().withAppointment(appointment).build());
        model.setAddressBook(new AddressBook());
        model.addContact(new ContactBuilder().build());

        //expected result
        Contact editedContact = new ContactBuilder().withName("Emily").build();
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder(editedContact).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_CONTACT, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_CONTACT_SUCCESS, editedContact);

        Set<Contact> editedContacts = new HashSet<>();
        editedContacts.add(editedContact);
        Appointment editedAppointment = new Appointment(appointment.getName(), appointment.getAddress(),
                appointment.getDateTime(), editedContacts, appointment.getTags());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new AppointmentBook(model.getAppointmentBook()), new UserPrefs());
        expectedModel.setContact(model.getFilteredContactList().get(0), editedContact);
        expectedModel.setAppointment(model.getFilteredAppointmentList().get(0), editedAppointment);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void createEditedContact_descriptorSameAsContact_success() {
        Contact contactToEdit = new ContactBuilder().build();
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder(contactToEdit).build();
        assertTrue(contactToEdit.equals(EditCommand.createEditedContact(contactToEdit, descriptor)));
    }

    @Test
    public void createEditedContact_descriptorDifferentFromContact_success() {
        Contact contactToEdit = new ContactBuilder().build();
        Contact newContact = new ContactBuilder().withName("Emily").build();
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder(newContact).build();
        assertFalse(contactToEdit.equals(EditCommand.createEditedContact(contactToEdit, descriptor)));
    }

    @Test
    public void checkAppointment_multipleAppointmentsChangeTogetherWithContact_success() {
        Contact contactToEdit = model.getFilteredContactList().get(0);
        Set<Contact> contacts = new HashSet<>();
        contacts.add(contactToEdit);
        Appointment sampleAppointment1 = new AppointmentBuilder().build();
        Appointment sampleAppointment2 = new AppointmentBuilder().withName("Family Outing").build();
        Appointment appointment1 = new Appointment(sampleAppointment1.getName(), sampleAppointment1.getAddress(),
                sampleAppointment1.getDateTime(), contacts, sampleAppointment1.getTags());
        Appointment appointment2 = new Appointment(sampleAppointment2.getName(), sampleAppointment2.getAddress(),
                sampleAppointment2.getDateTime(), contacts, sampleAppointment2.getTags());
        model.setAppointmentBook(new AppointmentBookBuilder().withAppointment(appointment1)
                .withAppointment(appointment2).build());
        Contact newContact = new Contact(new Name("Emily"), contactToEdit.getPhone(), contactToEdit.getEmail(),
                contactToEdit.getAddress(), contactToEdit.getTags(), contactToEdit.getTimeAdded(),
                contactToEdit.getFavourite());
        EditCommand.checkAppointment(model, contactToEdit, newContact);
        assertTrue(model.getFilteredAppointmentList().get(0).getContacts().toArray()[0].equals(newContact));
        assertTrue(model.getFilteredAppointmentList().get(1).getContacts().toArray()[0].equals(newContact));
    }

    @Test
    public void editAppointment_singleAppointmentChangeWithContact_success() {
        Contact contactToEdit = model.getFilteredContactList().get(0);
        Set<Contact> contacts = new HashSet<>();
        contacts.add(contactToEdit);
        Appointment sampleAppointment = new AppointmentBuilder().build();
        Appointment appointment = new Appointment(sampleAppointment.getName(), sampleAppointment.getAddress(),
                sampleAppointment.getDateTime(), contacts, sampleAppointment.getTags());
        model.setAppointmentBook(new AppointmentBookBuilder().withAppointment(appointment).build());
        Contact newContact = new Contact(new Name("Emily"), contactToEdit.getPhone(), contactToEdit.getEmail(),
                contactToEdit.getAddress(), contactToEdit.getTags(), contactToEdit.getTimeAdded(),
                contactToEdit.getFavourite());
        EditCommand.editAppointment(model, appointment, contactToEdit, newContact);
        assertTrue(model.getFilteredAppointmentList().get(0).getContacts().toArray()[0].equals(newContact));
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_CONTACT, DESC_AMY);

        // same values -> returns true
        EditCommand.EditContactDescriptor copyDescriptor = new EditCommand.EditContactDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_CONTACT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_CONTACT, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_CONTACT, DESC_BOB)));
    }

}
