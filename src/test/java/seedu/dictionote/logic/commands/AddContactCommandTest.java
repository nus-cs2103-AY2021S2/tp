package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dictionote.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.dictionote.commons.core.GuiSettings;
import seedu.dictionote.logic.commands.exceptions.CommandException;
import seedu.dictionote.model.AddressBook;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.ReadOnlyAddressBook;
import seedu.dictionote.model.ReadOnlyDefinitionBook;
import seedu.dictionote.model.ReadOnlyDictionary;
import seedu.dictionote.model.ReadOnlyNoteBook;
import seedu.dictionote.model.ReadOnlyUserPrefs;
import seedu.dictionote.model.contact.Contact;
import seedu.dictionote.model.dictionary.Content;
import seedu.dictionote.model.dictionary.Definition;
import seedu.dictionote.model.dictionary.DisplayableContent;
import seedu.dictionote.model.note.Note;
import seedu.dictionote.testutil.ContactBuilder;
import seedu.dictionote.ui.DictionaryContentConfig;
import seedu.dictionote.ui.NoteContentConfig;

public class AddContactCommandTest {

    @Test
    public void constructor_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddContactCommand(null));
    }

    @Test
    public void execute_contactAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingContactAdded modelStub = new ModelStubAcceptingContactAdded();
        Contact validContact = new ContactBuilder().build();

        CommandResult commandResult = new AddContactCommand(validContact).execute(modelStub);

        assertEquals(String.format(AddContactCommand.MESSAGE_SUCCESS, validContact), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validContact), modelStub.contactsAdded);
    }

    @Test
    public void execute_duplicateContact_throwsCommandException() {
        Contact validContact = new ContactBuilder().build();
        AddContactCommand addContactCommand = new AddContactCommand(validContact);
        ModelStub modelStub = new ModelStubWithContact(validContact);

        assertThrows(CommandException.class,
                AddContactCommand.MESSAGE_DUPLICATE_CONTACT, () -> addContactCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Contact alice = new ContactBuilder().withName("Alice").build();
        Contact bob = new ContactBuilder().withName("Bob").build();
        AddContactCommand addAliceCommand = new AddContactCommand(alice);
        AddContactCommand addBobCommand = new AddContactCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddContactCommand addAliceCommandCopy = new AddContactCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different contact -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setNote(Note oldNote, Note newNote) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setNoteContentConfig(NoteContentConfig noteContentConfig) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addContact(Contact contact) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void emailContact(Contact contact) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getNoteBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getDictionaryFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getDefinitionBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasContact(Contact contact) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteContact(Contact target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setContact(Contact target, Contact editedContact) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setNoteBookFilePath(Path path) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDictionaryFilePath(Path path) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDefinitionBookFilePath(Path path) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Contact> getFilteredContactList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredContactList(Predicate<Contact> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredNoteList(Predicate<Note> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredContentList(Predicate<Content> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredDefinitionList(Predicate<Definition> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyDictionary getDictionary() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasContent(Content content) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addContent(Content content) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Content> getFilteredContentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyDefinitionBook getDefinitionBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasDefinition(Definition definition) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addDefinition(Definition definition) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void showDictionaryContent(DisplayableContent content) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDictionaryContentConfig(DictionaryContentConfig dictionaryContentConfig) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Definition> getFilteredDefinitionList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<? extends DisplayableContent> getFilteredCurrentDictionaryList() {
            return null;
        }

        @Override
        public ReadOnlyNoteBook getNoteBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasNote(Note note) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addNote(Note note) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteNote(Note note) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void showNote(Note target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasNoteShown() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetNoteShown() {
            throw new AssertionError("This method should not be called.");

        }

        @Override
        public Note getNoteShown() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String getEditedNoteShownContent() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean onEditModeNote() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Note> getFilteredNoteList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortNote() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single contact.
     */
    private class ModelStubWithContact extends ModelStub {
        private final Contact contact;

        ModelStubWithContact(Contact contact) {
            requireNonNull(contact);
            this.contact = contact;
        }

        @Override
        public boolean hasContact(Contact contact) {
            requireNonNull(contact);
            return this.contact.isSameContact(contact);
        }
    }

    /**
     * A Model stub that always accept the contact being added.
     */
    private class ModelStubAcceptingContactAdded extends ModelStub {
        final ArrayList<Contact> contactsAdded = new ArrayList<>();

        @Override
        public boolean hasContact(Contact contact) {
            requireNonNull(contact);
            return contactsAdded.stream().anyMatch(contact::isSameContact);
        }

        @Override
        public void addContact(Contact contact) {
            requireNonNull(contact);
            contactsAdded.add(contact);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }

        @Override
        public void sortNote() {
            throw new AssertionError("This method should not be called.");
        }
    }

}
