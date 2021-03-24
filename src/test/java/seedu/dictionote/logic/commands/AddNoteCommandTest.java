package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dictionote.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.dictionote.commons.core.GuiSettings;
import seedu.dictionote.logic.commands.exceptions.CommandException;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.NoteBook;
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
import seedu.dictionote.model.tag.Tag;
import seedu.dictionote.testutil.NoteBuilder;
import seedu.dictionote.ui.DictionaryContentConfig;
import seedu.dictionote.ui.NoteContentConfig;

public class AddNoteCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddNoteCommand(null));
    }

    @Test
    public void execute_noteAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingNoteAdded modelStub = new ModelStubAcceptingNoteAdded();
        Note validNote = new NoteBuilder().build();

        CommandResult commandResult = new AddNoteCommand(validNote).execute(modelStub);

        assertEquals(Arrays.asList(validNote), modelStub.noteAdded);
    }

    @Test
    public void execute_duplicateNote_throwsCommandException() {
        Note validNote = new NoteBuilder().build();
        AddNoteCommand addNoteCommand = new AddNoteCommand(validNote);
        AddNoteCommandTest.ModelStub modelStub = new AddNoteCommandTest.ModelStubWithNote(validNote);

        assertThrows(CommandException.class,
                AddNoteCommand.MESSAGE_DUPLICATE_NOTE, () -> addNoteCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Set<Tag> tags = new HashSet<>();
        Note note = new NoteBuilder(new Note("2103", tags)).build();
        Note otherNote = new NoteBuilder(new Note("cs2103T", tags)).build();
        AddNoteCommand addNoteCommand = new AddNoteCommand(note);
        AddNoteCommand addOtherNoteCommand = new AddNoteCommand(otherNote);

        // same object -> returns true
        assertTrue(addNoteCommand.equals(addNoteCommand));

        // same values -> returns true
        AddNoteCommand addNoteCommandCopy = new AddNoteCommand(note);
        assertTrue(addNoteCommand.equals(addNoteCommandCopy));

        // different types -> returns false
        assertFalse(addNoteCommand.equals(1));

        // null -> returns false
        assertFalse(addNoteCommand.equals(null));

        // different person -> returns false
        assertFalse(addNoteCommand.equals(addOtherNoteCommand));
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
            return false;
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
     * A Model stub that contains a single person.
     */
    private class ModelStubWithNote extends ModelStub {
        private final Note note;

        ModelStubWithNote(Note note) {
            requireNonNull(note);
            this.note = note;
        }

        @Override
        public boolean hasNote(Note note) {
            requireNonNull(note);
            return this.note.isSameNote(note);
        }
    }

    /**
     * A Model stub that always accept the note being added.
     */
    private class ModelStubAcceptingNoteAdded extends ModelStub {
        final ArrayList<Note> noteAdded = new ArrayList<>();

        @Override
        public boolean hasNote(Note note) {
            requireNonNull(note);
            return noteAdded.stream().anyMatch(note::isSameNote);
        }

        @Override
        public void addNote(Note note) {
            requireNonNull(note);
            noteAdded.add(note);
        }

        @Override
        public ReadOnlyNoteBook getNoteBook() {
            return new NoteBook();
        }
    }

}
