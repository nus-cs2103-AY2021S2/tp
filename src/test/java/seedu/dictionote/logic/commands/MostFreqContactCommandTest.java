package seedu.dictionote.logic.commands;

import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.dictionote.testutil.TypicalContacts.ALICE;
import static seedu.dictionote.testutil.TypicalContacts.BENSON;
import static seedu.dictionote.testutil.TypicalContacts.CARL;
import static seedu.dictionote.testutil.TypicalContacts.DANIEL;
import static seedu.dictionote.testutil.TypicalContacts.ELLE;
import static seedu.dictionote.testutil.TypicalContacts.FIONA;
import static seedu.dictionote.testutil.TypicalContacts.GEORGE;
import static seedu.dictionote.testutil.TypicalContacts.getTypicalContactsList;
import static seedu.dictionote.testutil.TypicalContent.getTypicalDictionary;
import static seedu.dictionote.testutil.TypicalDefinition.getTypicalDefinitionBook;
import static seedu.dictionote.testutil.TypicalNotes.getTypicalNoteBook;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.enums.UiActionOption;
import seedu.dictionote.model.ContactsList;
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
import seedu.dictionote.model.contact.exceptions.InvalidContactMailtoLinkException;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class MostFreqContactCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManagerStub(getTypicalContactsList(), new UserPrefs(),
                getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());
    }

    @Test
    public void execute_zeroFrequencies_showsSameListAsListContactCommand() {
        expectedModel = new ModelManagerStub(model.getContactsList(), new UserPrefs(),
                getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());

        assertCommandSuccess(new MostFreqContactCommand(), model,
                MostFreqContactCommand.MESSAGE_SUCCESS, UiAction.OPEN, UiActionOption.CONTACT, expectedModel);
    }

    @Test
    public void execute_differentFrequencies_showsSortedList() {
        ContactsList expectedContactsList = new ContactsListStub();
        expectedContactsList.setContacts(Arrays.asList(ELLE, DANIEL, ALICE, FIONA, BENSON, CARL, GEORGE));

        expectedModel = new ModelManagerStub(expectedContactsList, new UserPrefs(),
                getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());

        emailContactManyTimes(model, 4, 6); // ELLE
        emailContactManyTimes(model, 3, 5); // DANIEL
        emailContactManyTimes(model, 0, 4); // ALICE
        emailContactManyTimes(model, 5, 3); // FIONA
        emailContactManyTimes(model, 1, 2); // BENSON
        emailContactManyTimes(model, 2, 1); // CARL

        assertCommandSuccess(new MostFreqContactCommand(), model,
                MostFreqContactCommand.MESSAGE_SUCCESS, UiAction.OPEN, UiActionOption.CONTACT, expectedModel);
    }

    /**
     * Attempts to email a given contact for a specific number of times using the given model.
     */
    private void emailContactManyTimes(Model m, int contactIndexNumber, int numEmails) {
        Contact contact;

        for (int i = 0; i < numEmails; i++) {
            contact = m.getFilteredContactList().get(contactIndexNumber);
            m.emailContactUsingLink(new MailtoLink(contact));
        }
    }

    /**
     * A stub ModelManager that contains a stub ContactsList.
     */
    private static class ModelManagerStub extends ModelManager {
        private final ContactsListStub contactsListStub;
        private final FilteredList<Contact> filteredContacts;

        /**
         * This constructor is present for compatibility reasons.
         */
        public ModelManagerStub(ReadOnlyContactsList contactsList, ReadOnlyUserPrefs userPrefs,
                                ReadOnlyNoteBook noteBook, ReadOnlyDictionary dictionary,
                                ReadOnlyDefinitionBook definitionBook) {
            this(contactsList);
        }

        public ModelManagerStub(ReadOnlyContactsList contactsList) {
            this.contactsListStub = new ContactsListStub(contactsList);
            filteredContacts = new FilteredList<>(this.contactsListStub.getContactList());
        }

        @Override
        public ObservableList<Contact> getFilteredContactList() {
            return filteredContacts;
        }

        @Override
        public void emailContactUsingLink(MailtoLink link) {
            contactsListStub.emailContactUsingLink(link);
        }

        @Override
        public void sortContactsByFrequencyCounter() {
            contactsListStub.sortByFrequencyCounter();
        }
    }

    /**
     * A stub ContactsList that does not invoke the OS's mail client.
     */
    private static class ContactsListStub extends ContactsList {
        public ContactsListStub() {
            super();
        }

        public ContactsListStub(ReadOnlyContactsList toBeCopied) {
            super(toBeCopied);
        }

        @Override
        public void emailContactUsingLink(MailtoLink link) throws InvalidContactMailtoLinkException {
            Contact receivingContact = link.getTo();
            setContact(receivingContact, incrementContactFrequency(receivingContact));
        }
    }

}
