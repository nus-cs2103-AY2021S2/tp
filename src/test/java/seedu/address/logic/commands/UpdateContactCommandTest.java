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
import static seedu.address.testutil.TypicalColabFolder.getTypicalColabFolder;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UpdateContactCommand.UpdateContactDescriptor;
import seedu.address.logic.uicommands.ShowContactsUiCommand;
import seedu.address.model.ColabFolder;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.testutil.ContactBuilder;
import seedu.address.testutil.UpdateContactDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for UpdateContactCommand.
 */
public class UpdateContactCommandTest {

    private Model model = new ModelManager(getTypicalColabFolder(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Contact editedContact = new ContactBuilder().build();
        UpdateContactDescriptor descriptor = new UpdateContactDescriptorBuilder(editedContact).build();
        UpdateContactCommand updateContactCommand = new UpdateContactCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(UpdateContactCommand.MESSAGE_EDIT_CONTACT_SUCCESS, editedContact);

        Model expectedModel = new ModelManager(new ColabFolder(model.getColabFolder()), new UserPrefs());
        expectedModel.setContact(model.getFilteredContactList().get(0), editedContact);

        assertCommandSuccess(updateContactCommand, model, expectedMessage, new ShowContactsUiCommand(), expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastContact = Index.fromOneBased(model.getFilteredContactList().size());
        Contact lastContact = model.getFilteredContactList().get(indexLastContact.getZeroBased());

        ContactBuilder contactInList = new ContactBuilder(lastContact);
        Contact editedContact = contactInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        UpdateContactDescriptor descriptor = new UpdateContactDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        UpdateContactCommand updateContactCommand = new UpdateContactCommand(indexLastContact, descriptor);

        String expectedMessage = String.format(UpdateContactCommand.MESSAGE_EDIT_CONTACT_SUCCESS, editedContact);

        Model expectedModel = new ModelManager(new ColabFolder(model.getColabFolder()), new UserPrefs());
        expectedModel.setContact(lastContact, editedContact);

        assertCommandSuccess(updateContactCommand, model, expectedMessage, new ShowContactsUiCommand(), expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_failure() {
        UpdateContactCommand updateContactCommand = new UpdateContactCommand(INDEX_FIRST,
                new UpdateContactDescriptor());

        String expectedMessage = String.format(UpdateContactCommand.MESSAGE_UNCHANGED_CONTACT);

        assertCommandFailure(updateContactCommand, model, expectedMessage);
    }

    @Test
    public void execute_filteredList_success() {
        showContactAtIndex(model, INDEX_FIRST);

        Contact contactInFilteredList = model.getFilteredContactList().get(INDEX_FIRST.getZeroBased());
        Contact editedContact = new ContactBuilder(contactInFilteredList).withName(VALID_NAME_BOB).build();
        UpdateContactCommand updateContactCommand = new UpdateContactCommand(INDEX_FIRST,
                new UpdateContactDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(UpdateContactCommand.MESSAGE_EDIT_CONTACT_SUCCESS, editedContact);

        Model expectedModel = new ModelManager(new ColabFolder(model.getColabFolder()), new UserPrefs());
        expectedModel.setContact(model.getFilteredContactList().get(0), editedContact);

        assertCommandSuccess(updateContactCommand, model, expectedMessage, new ShowContactsUiCommand(), expectedModel);
    }

    @Test
    public void execute_duplicateContactUnfilteredList_failure() {
        Contact firstContact = model.getFilteredContactList().get(INDEX_FIRST.getZeroBased());
        UpdateContactCommand.UpdateContactDescriptor descriptor = new UpdateContactDescriptorBuilder(firstContact)
                .build();
        UpdateContactCommand updateContactCommand = new UpdateContactCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(updateContactCommand, model, UpdateContactCommand.MESSAGE_DUPLICATE_CONTACT);
    }

    @Test
    public void execute_duplicateContactFilteredList_failure() {
        showContactAtIndex(model, INDEX_FIRST);

        // edit contact in filtered list into a duplicate in contact list
        Contact contactInList = model.getColabFolder().getContactList().get(INDEX_SECOND.getZeroBased());
        UpdateContactCommand updateContactCommand = new UpdateContactCommand(INDEX_FIRST,
                new UpdateContactDescriptorBuilder(contactInList).build());

        assertCommandFailure(updateContactCommand, model, UpdateContactCommand.MESSAGE_DUPLICATE_CONTACT);
    }

    @Test
    public void execute_invalidContactIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredContactList().size() + 1);
        UpdateContactDescriptor descriptor = new UpdateContactDescriptorBuilder().withName(VALID_NAME_BOB).build();
        UpdateContactCommand updateContactCommand = new UpdateContactCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(updateContactCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of contact list
     */
    @Test
    public void execute_invalidContactIndexFilteredList_failure() {
        showContactAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of contact list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getColabFolder().getContactList().size());

        UpdateContactCommand updateContactCommand = new UpdateContactCommand(outOfBoundIndex,
                new UpdateContactDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(updateContactCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final UpdateContactCommand standardCommand = new UpdateContactCommand(INDEX_FIRST, DESC_AMY);

        // same values -> returns true
        UpdateContactDescriptor copyDescriptor = new UpdateContactDescriptor(DESC_AMY);
        UpdateContactCommand commandWithSameValues = new UpdateContactCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new UpdateContactCommand(INDEX_SECOND, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new UpdateContactCommand(INDEX_FIRST, DESC_BOB)));
    }

}
