package seedu.dictionote.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dictionote.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.dictionote.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.dictionote.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.dictionote.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.dictionote.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.dictionote.logic.commands.CommandTestUtil.showContactAtIndex;
import static seedu.dictionote.testutil.TypicalContacts.getTypicalAddressBook;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_SECOND_CONTACT;
import static seedu.dictionote.testutil.TypicalNotes.getTypicalNoteBook;

import org.junit.jupiter.api.Test;

import seedu.dictionote.commons.core.Messages;
import seedu.dictionote.commons.core.index.Index;
import seedu.dictionote.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.dictionote.model.AddressBook;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.ModelManager;
import seedu.dictionote.model.UserPrefs;
import seedu.dictionote.model.contact.Contact;
import seedu.dictionote.testutil.ContactBuilder;
import seedu.dictionote.testutil.EditPersonDescriptorBuilder;


/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalNoteBook());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Contact editedContact = new ContactBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedContact).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_CONTACT, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedContact);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new UserPrefs(), model.getNoteBook());
        expectedModel.setPerson(model.getFilteredContactList().get(0), editedContact);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredContactList().size());
        Contact lastContact = model.getFilteredContactList().get(indexLastPerson.getZeroBased());

        ContactBuilder personInList = new ContactBuilder(lastContact);
        Contact editedContact = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedContact);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new UserPrefs(), model.getNoteBook());
        expectedModel.setPerson(lastContact, editedContact);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_CONTACT, new EditPersonDescriptor());
        Contact editedContact = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedContact);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new UserPrefs(), getTypicalNoteBook());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);

        Contact contactInFilteredList = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        Contact editedContact = new ContactBuilder(contactInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_CONTACT,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedContact);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new UserPrefs(), model.getNoteBook());
        expectedModel.setPerson(model.getFilteredContactList().get(0), editedContact);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Contact firstContact = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstContact).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_CONTACT, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);

        // edit person in filtered list into a duplicate in dictionote book
        Contact contactInList = model.getAddressBook().getContactList().get(INDEX_SECOND_CONTACT.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_CONTACT,
                new EditPersonDescriptorBuilder(contactInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredContactList().size() + 1);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of dictionote book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);
        Index outOfBoundIndex = INDEX_SECOND_CONTACT;
        // ensures that outOfBoundIndex is still in bounds of dictionote book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getContactList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_CONTACT, DESC_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
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
