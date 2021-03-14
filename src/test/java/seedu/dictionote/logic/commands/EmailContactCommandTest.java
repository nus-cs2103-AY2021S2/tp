package seedu.dictionote.logic.commands;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import seedu.dictionote.commons.core.Messages;
import seedu.dictionote.commons.core.index.Index;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.ModelManager;
import seedu.dictionote.model.UserPrefs;
import seedu.dictionote.model.contact.Contact;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dictionote.logic.commands.CommandTestUtil.*;
import static seedu.dictionote.testutil.TypicalContacts.getTypicalAddressBook;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_SECOND_CONTACT;
import static seedu.dictionote.testutil.TypicalNotes.getTypicalNoteBook;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code EmailContactCommand}.
 */
public class EmailContactCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalNoteBook());

    @Test
    @Disabled
    public void execute_validIndexUnfilteredList_success() throws IOException {
        Contact contactToEmail = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        EmailContactCommand emailContactCommand = new EmailContactCommand(INDEX_FIRST_CONTACT);

        String expectedMessage = String.format(EmailContactCommand.MESSAGE_EMAIL_CONTACT_SUCCESS, contactToEmail);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), getTypicalNoteBook());
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
    @Disabled
    public void execute_validIndexFilteredList_success() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);

        Contact contactToEmail = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        EmailContactCommand emailContactCommand = new EmailContactCommand(INDEX_FIRST_CONTACT);

        String expectedMessage = String.format(EmailContactCommand.MESSAGE_EMAIL_CONTACT_SUCCESS, contactToEmail);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), getTypicalNoteBook());
        expectedModel.emailContact(contactToEmail);

        showContactAtIndex(expectedModel, INDEX_FIRST_CONTACT);

        assertCommandSuccess(emailContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);

        Index outOfBoundIndex = INDEX_SECOND_CONTACT;
        // ensures that outOfBoundIndex is still in bounds of the contacts list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getContactList().size());

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
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoContact(Model model) {
        model.updateFilteredContactList(p -> false);

        assertTrue(model.getFilteredContactList().isEmpty());
    }
}
