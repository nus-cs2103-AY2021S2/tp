package seedu.dictionote.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.dictionote.logic.commands.CommandTestUtil.showContactAtIndex;
import static seedu.dictionote.testutil.TypicalContacts.getTypicalContactsList;
import static seedu.dictionote.testutil.TypicalContent.getTypicalDictionary;
import static seedu.dictionote.testutil.TypicalDefinition.getTypicalDefinitionBook;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_SECOND_CONTACT;
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
    public void execute_validIndexUnfilteredList_success() {
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

        expectedModel.emailContact(contactToEmail);

        assertCommandSuccess(emailContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredContactList().size() + 1);
        EmailContactCommand emailContactCommand = new EmailContactCommand(outOfBoundIndex);

        assertCommandFailure(emailContactCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
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

        expectedModel.emailContact(contactToEmail);

        showContactAtIndex(expectedModel, INDEX_FIRST_CONTACT);

        assertCommandSuccess(emailContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);

        Index outOfBoundIndex = INDEX_SECOND_CONTACT;
        // ensures that outOfBoundIndex is still in bounds of the contacts list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getContactsList().getContactList().size());

        EmailContactCommand emailContactCommand = new EmailContactCommand(outOfBoundIndex);

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
        public void emailContact(Contact contact) {
            // for the sake of unit testing, do not invoke the OS's mail client.
            return;
        }
    }
}
