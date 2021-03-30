package seedu.dictionote.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.dictionote.logic.commands.CommandTestUtil.showContactAtIndex;
import static seedu.dictionote.logic.commands.CommandTestUtil.showNoteAtIndex;
import static seedu.dictionote.testutil.TypicalContacts.getTypicalContactsList;
import static seedu.dictionote.testutil.TypicalContent.getTypicalDictionary;
import static seedu.dictionote.testutil.TypicalDefinition.getTypicalDefinitionBook;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_FIRST_NOTE;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_SECOND_CONTACT;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_SECOND_NOTE;
import static seedu.dictionote.testutil.TypicalNotes.getTypicalNoteBook;

import org.junit.jupiter.api.Test;

import seedu.dictionote.commons.core.Messages;
import seedu.dictionote.commons.core.index.Index;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.ModelManager;
import seedu.dictionote.model.ReadOnlyContactsList;
import seedu.dictionote.model.ReadOnlyDefinitionBook;
import seedu.dictionote.model.ReadOnlyDictionary;
import seedu.dictionote.model.ReadOnlyNoteBook;
import seedu.dictionote.model.ReadOnlyUserPrefs;
import seedu.dictionote.model.UserPrefs;
import seedu.dictionote.model.contact.Contact;
import seedu.dictionote.model.contact.MailtoLink;
import seedu.dictionote.model.note.Note;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code EmailContactCommand}.
 */
public class EmailContactCommandTest {

    private Model model = new ModelManagerStub(
            getTypicalContactsList(),
            new UserPrefs(),
            getTypicalNoteBook(),
            getTypicalDictionary(),
            getTypicalDefinitionBook()
    );

    @Test
    public void executeWithoutNote_validIndexUnfilteredList_success() {
        Contact contactToEmail = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        EmailContactCommand emailContactCommand = new EmailContactCommand(INDEX_FIRST_CONTACT);

        String expectedMessage = String.format(EmailContactCommand.MESSAGE_EMAIL_CONTACT_SUCCESS, contactToEmail);

        ModelManager expectedModel = new ModelManagerStub(
                model.getContactsList(),
                new UserPrefs(),
                getTypicalNoteBook(),
                getTypicalDictionary(),
                getTypicalDefinitionBook()
        );

        expectedModel.emailContactUsingLink(new MailtoLink(contactToEmail));

        assertCommandSuccess(emailContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void executeWithoutNote_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredContactList().size() + 1);
        EmailContactCommand emailContactCommand = new EmailContactCommand(outOfBoundIndex);

        assertCommandFailure(emailContactCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void executeWithoutNote_validIndexFilteredContactList_success() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);

        Contact contactToEmail = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        EmailContactCommand emailContactCommand = new EmailContactCommand(INDEX_FIRST_CONTACT);

        String expectedMessage = String.format(EmailContactCommand.MESSAGE_EMAIL_CONTACT_SUCCESS, contactToEmail);

        ModelManager expectedModel = new ModelManagerStub(
                model.getContactsList(),
                new UserPrefs(),
                getTypicalNoteBook(),
                getTypicalDictionary(),
                getTypicalDefinitionBook()
        );

        expectedModel.emailContactUsingLink(new MailtoLink(contactToEmail));

        showContactAtIndex(expectedModel, INDEX_FIRST_CONTACT);

        assertCommandSuccess(emailContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void executeWithoutNote_invalidIndexFilteredContactList_throwsCommandException() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);

        Index outOfBoundIndex = INDEX_SECOND_CONTACT;
        // ensures that outOfBoundIndex is still in bounds of the contacts list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getContactsList().getContactList().size());

        EmailContactCommand emailContactCommand = new EmailContactCommand(outOfBoundIndex);

        assertCommandFailure(emailContactCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void executeWithNote_validIndexFilteredContactListAndFilteredNoteList_success() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);
        showNoteAtIndex(model, INDEX_FIRST_NOTE);

        Contact contactToEmail = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        Note noteToEmail = model.getFilteredNoteList().get(INDEX_FIRST_NOTE.getZeroBased());

        EmailContactCommand emailContactCommand = new EmailContactCommand(INDEX_FIRST_CONTACT, INDEX_FIRST_NOTE);

        String expectedMessage = String.format(EmailContactCommand.MESSAGE_EMAIL_CONTACT_SUCCESS, contactToEmail);

        ModelManager expectedModel = new ModelManagerStub(
                model.getContactsList(),
                new UserPrefs(),
                model.getNoteBook(),
                getTypicalDictionary(),
                getTypicalDefinitionBook()
        );

        expectedModel.emailContactUsingLink(new MailtoLink(contactToEmail).setBody(noteToEmail));

        showContactAtIndex(expectedModel, INDEX_FIRST_CONTACT);
        showNoteAtIndex(expectedModel, INDEX_FIRST_NOTE);

        assertCommandSuccess(emailContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void executeWithNote_invalidIndexFilteredContactListAndValidIndexFilteredNoteList_throwsCommandException() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);
        showNoteAtIndex(model, INDEX_FIRST_NOTE);

        Index outOfBoundContactIndex = INDEX_SECOND_CONTACT;
        Index validNoteIndex = INDEX_FIRST_NOTE;
        // ensures that outOfBoundContactIndex is still in bounds of the contacts list
        assertTrue(outOfBoundContactIndex.getZeroBased() < model.getContactsList().getContactList().size());

        EmailContactCommand emailContactCommand = new EmailContactCommand(outOfBoundContactIndex, validNoteIndex);

        assertCommandFailure(emailContactCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void executeWithNote_validIndexFilteredContactListAndInvalidIndexFilteredNoteList_throwsCommandException() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);
        showNoteAtIndex(model, INDEX_FIRST_NOTE);

        Index validContactIndex = INDEX_FIRST_CONTACT;
        Index outOfBoundNoteIndex = INDEX_SECOND_NOTE;
        // ensures that outOfBoundNoteIndex is still in bounds of the notes list
        assertTrue(outOfBoundNoteIndex.getZeroBased() < model.getNoteBook().getNoteList().size());

        EmailContactCommand emailContactCommand = new EmailContactCommand(validContactIndex, outOfBoundNoteIndex);

        assertCommandFailure(emailContactCommand, model, Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
    }

    @Test
    public void executeWithNote_invalidIndexFilteredContactListAndFilteredNoteList_throwsCommandException() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);
        showNoteAtIndex(model, INDEX_FIRST_NOTE);

        Index outOfBoundContactIndex = INDEX_SECOND_CONTACT;
        Index outOfBoundNoteIndex = INDEX_SECOND_NOTE;

        // ensures that outOfBoundContactIndex is still in bounds of the contacts list
        assertTrue(outOfBoundContactIndex.getZeroBased() < model.getNoteBook().getNoteList().size());
        // ensures that outOfBoundNoteIndex is still in bounds of the notes list
        assertTrue(outOfBoundNoteIndex.getZeroBased() < model.getNoteBook().getNoteList().size());

        EmailContactCommand emailContactCommand = new EmailContactCommand(outOfBoundContactIndex, outOfBoundNoteIndex);

        // Invalid contact index takes precedence in terms of thrown exceptions.
        // Reason: contact index is required by the EmailContactCommand, while the note index
        // is optional.
        assertCommandFailure(emailContactCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        EmailContactCommand emailFirstCommand = new EmailContactCommand(INDEX_FIRST_CONTACT);
        EmailContactCommand emailSecondCommand = new EmailContactCommand(INDEX_SECOND_CONTACT);

        // same object -> returns true
        assertTrue(emailFirstCommand.equals(emailFirstCommand));

        // same values -> returns true
        EmailContactCommand emailFirstCommandCopy = new EmailContactCommand(INDEX_FIRST_CONTACT);
        assertTrue(emailFirstCommand.equals(emailFirstCommandCopy));

        // different types -> returns false
        assertFalse(emailFirstCommand.equals(1));

        // null -> returns false
        assertFalse(emailFirstCommand.equals(null));

        // different contact -> returns false
        assertFalse(emailFirstCommand.equals(emailSecondCommand));
    }

    /**
     * A stub ModelManager that does not invoke the OS's mail client.
     */
    private static class ModelManagerStub extends ModelManager {
        public ModelManagerStub(ReadOnlyContactsList addressBook, ReadOnlyUserPrefs userPrefs,
                                ReadOnlyNoteBook noteBook, ReadOnlyDictionary dictionary,
                                ReadOnlyDefinitionBook definitionBook) {
            super(addressBook, userPrefs, noteBook, dictionary, definitionBook);
        }

        @Override
        public void emailContactUsingLink(MailtoLink link) {
            // for the sake of unit testing, do not invoke the OS's mail client.
            return;
        }
    }
}
