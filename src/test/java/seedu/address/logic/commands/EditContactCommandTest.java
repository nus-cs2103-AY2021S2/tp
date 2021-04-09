package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_CONTACT_NAME_DOES_NOT_EXIST;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_CONTACT;
import static seedu.address.commons.core.Messages.MESSAGE_EDIT_CONTACT_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.CONTACT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.CONTACT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.FIRST_CONTACT_NAME_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CONTACT_NAME_JOHN;
import static seedu.address.logic.commands.CommandTestUtil.LAST_CONTACT_NAME_GEORGE;
import static seedu.address.logic.commands.CommandTestUtil.SECOND_CONTACT_NAME_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showContactAtIndex;
import static seedu.address.testutil.TypicalContacts.getTypicalContactsAddressBook;
import static seedu.address.testutil.TypicalIndices.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndices.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditContactCommand.EditContactDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.testutil.ContactBuilder;
import seedu.address.testutil.EditContactDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */

public class EditContactCommandTest {

    private Model model = new ModelManager(getTypicalContactsAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Contact editedContact = new ContactBuilder().build();
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder(editedContact).build();
        EditContactCommand editContactCommand = new EditContactCommand(FIRST_CONTACT_NAME_ALICE, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_CONTACT_SUCCESS, editedContact);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setContact(model.getFilteredContactList().get(0), editedContact);

        assertCommandSuccess(editContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastContact = Index.fromOneBased(model.getFilteredContactList().size());
        Contact lastContact = model.getFilteredContactList().get(indexLastContact.getZeroBased());

        ContactBuilder contactInList = new ContactBuilder(lastContact);
        Contact editedContact = contactInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditContactDescriptor descriptor = new EditContactDescriptorBuilder().withContactName(VALID_NAME_BOB)
                .withContactPhone(VALID_PHONE_BOB).withContactTags(VALID_TAG_HUSBAND).build();
        EditContactCommand editContactCommand = new EditContactCommand(LAST_CONTACT_NAME_GEORGE, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_CONTACT_SUCCESS, editedContact);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setContact(lastContact, editedContact);

        assertCommandSuccess(editContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditContactCommand editContactCommand =
                new EditContactCommand(FIRST_CONTACT_NAME_ALICE, new EditContactDescriptor());
        Contact editedContact = model.getFilteredContactList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(MESSAGE_EDIT_CONTACT_SUCCESS, editedContact);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showContactAtIndex(model, INDEX_FIRST);

        Contact contactInFilteredList = model.getFilteredContactList().get(INDEX_FIRST.getZeroBased());
        Contact editedContact = new ContactBuilder(contactInFilteredList).withName(VALID_NAME_BOB).build();
        EditContactCommand editContactCommand = new EditContactCommand(FIRST_CONTACT_NAME_ALICE,
                new EditContactDescriptorBuilder().withContactName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(MESSAGE_EDIT_CONTACT_SUCCESS, editedContact);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setContact(model.getFilteredContactList().get(0), editedContact);

        assertCommandSuccess(editContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Contact firstContact = model.getFilteredContactList().get(INDEX_FIRST.getZeroBased());
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder(firstContact).build();
        EditContactCommand editContactCommand = new EditContactCommand(SECOND_CONTACT_NAME_BENSON, descriptor);

        assertCommandFailure(editContactCommand, model, MESSAGE_DUPLICATE_CONTACT);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showContactAtIndex(model, INDEX_FIRST);

        // edit person in filtered list into a duplicate in address book
        Contact contactInList = model.getAddressBook().getContactList().get(INDEX_SECOND.getZeroBased());
        EditContactCommand editContactCommand = new EditContactCommand(FIRST_CONTACT_NAME_ALICE,
                new EditContactDescriptorBuilder(contactInList).build());

        assertCommandFailure(editContactCommand, model, MESSAGE_DUPLICATE_CONTACT);
    }

    @Test
    public void execute_invalidPersonName_failure() {
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder().withContactName(VALID_NAME_BOB).build();
        EditContactCommand editContactCommand = new EditContactCommand(INVALID_CONTACT_NAME_JOHN, descriptor);

        assertCommandFailure(editContactCommand, model, MESSAGE_CONTACT_NAME_DOES_NOT_EXIST);
    }

    @Test
    public void equals() {
        final EditContactCommand standardCommand = new EditContactCommand(FIRST_CONTACT_NAME_ALICE, CONTACT_DESC_AMY);

        // same values -> returns true
        EditContactDescriptor copyDescriptor = new EditContactDescriptor(CONTACT_DESC_AMY);
        EditContactCommand commandWithSameValues = new EditContactCommand(FIRST_CONTACT_NAME_ALICE, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditContactCommand(SECOND_CONTACT_NAME_BENSON, CONTACT_DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditContactCommand(FIRST_CONTACT_NAME_ALICE, CONTACT_DESC_BOB)));
    }
}
