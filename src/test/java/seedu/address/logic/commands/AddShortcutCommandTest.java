package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.attribute.Attribute;
import seedu.address.model.person.Person;
import seedu.address.model.shortcut.Shortcut;
import seedu.address.model.shortcut.ShortcutLibrary;
import seedu.address.storage.Authentication;

public class AddShortcutCommandTest {

    @Test
    public void constructor_emptyShortcutName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                new AddShortcutCommand("", "list"));
    }

    @Test
    public void constructor_emptyShortcutCommand_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                new AddShortcutCommand("ls", ""));
    }

    @Test
    public void constructor_invalidShortcutName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                new AddShortcutCommand("abc!", "list"));
    }

    @Test
    public void constructor_invalidShortcutCommand_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                new AddShortcutCommand("abc", "list all"));
    }

    @Test
    public void execute_shortcutAcceptedByModel_addSuccessful() throws Exception {
        AddShortcutCommandTest.ModelStubAcceptingShortcutAdded modelStub =
                new AddShortcutCommandTest.ModelStubAcceptingShortcutAdded();
        String validShortcutName = "ls";
        String validShortcutCommand = "list";
        Shortcut validShortcut = new Shortcut(validShortcutName, validShortcutCommand);
        CommandResult commandResult = new AddShortcutCommand(validShortcutName,
                validShortcutCommand).execute(modelStub);

        assertEquals(String.format(AddShortcutCommand.MESSAGE_SUCCESS, validShortcut),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validShortcut), modelStub.shortcutsAdded);
    }

    @Test
    public void execute_duplicateShortcut_throwsCommandException() {
        String validShortcutName = "ls";
        String validShortcutCommand = "list";
        Shortcut validShortcut = new Shortcut(validShortcutName, validShortcutCommand);
        AddShortcutCommand addShortcutCommand = new AddShortcutCommand(validShortcutName, validShortcutCommand);
        AddShortcutCommandTest.ModelStub modelStub = new AddShortcutCommandTest.ModelStubWithShortcut(validShortcut);

        assertThrows(CommandException.class, AddShortcutCommand.MESSAGE_DUPLICATE_SHORTCUT, () ->
                addShortcutCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Shortcut first = new Shortcut("first", "list");
        Shortcut second = new Shortcut("second", "help");
        AddShortcutCommand addFirstShortcutCommand =
                new AddShortcutCommand(first.getShortcutName(), first.getShortcutCommand());
        AddShortcutCommand addSecondShortcutCommand =
                new AddShortcutCommand(second.getShortcutName(), second.getShortcutCommand());

        // same object -> returns true
        assertTrue(addFirstShortcutCommand.equals(addFirstShortcutCommand));

        // same values -> returns true
        AddShortcutCommand addFirstShortcutCommandCopy =
                new AddShortcutCommand(first.getShortcutName(), first.getShortcutCommand());
        assertTrue(addFirstShortcutCommand.equals(addFirstShortcutCommandCopy));

        // different types -> returns false
        assertFalse(addFirstShortcutCommand.equals(1));

        // null -> returns false
        assertFalse(addFirstShortcutCommand.equals(null));

        // different shortcut -> returns false
        assertFalse(addFirstShortcutCommand.equals(addSecondShortcutCommand));
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
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getShortcutLibraryFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setShortcutLibraryFilePath(Path shortcutLibraryFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
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
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updatePersonListByAttribute(Set<Attribute> attributeTypes) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Authentication getAuthentication() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoListModification() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedPersonList(Comparator<Person> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getWholePersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setShortcutLibrary(ShortcutLibrary shortcutLibrary) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ShortcutLibrary getShortcutLibrary() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasShortcut(String shortcutName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteShortcut(String shortcutName) {

        }

        @Override
        public void addShortcut(String shortcutName, String shortcutCommand) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setShortcut(String target, String shortcutCommand) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single shortcut.
     */
    private class ModelStubWithShortcut extends AddShortcutCommandTest.ModelStub {
        private final Shortcut shortcut;

        ModelStubWithShortcut(Shortcut shortcut) {
            requireNonNull(shortcut);
            this.shortcut = shortcut;
        }

        @Override
        public boolean hasShortcut(String shortcutName) {
            requireNonNull(shortcutName);
            Shortcut shortcut = new Shortcut(shortcutName, "list");
            return this.shortcut.isSameShortcutName(shortcut);
        }
    }

    /**
     * A Model stub that always accept the shortcut being added.
     */
    private class ModelStubAcceptingShortcutAdded extends AddShortcutCommandTest.ModelStub {
        final ArrayList<Shortcut> shortcutsAdded = new ArrayList<>();

        @Override
        public boolean hasShortcut(String shortcutName) {
            requireNonNull(shortcutName);
            Shortcut shortcut = new Shortcut(shortcutName, "list");
            return shortcutsAdded.stream().anyMatch(shortcut::isSameShortcutName);
        }

        @Override
        public void addShortcut(String shortcutName, String shortcutCommand) {
            requireNonNull(shortcutName);
            requireNonNull(shortcutCommand);
            shortcutsAdded.add(new Shortcut(shortcutName, shortcutCommand));
        }

        @Override
        public ShortcutLibrary getShortcutLibrary() {
            return new ShortcutLibrary();
        }
    }

}
