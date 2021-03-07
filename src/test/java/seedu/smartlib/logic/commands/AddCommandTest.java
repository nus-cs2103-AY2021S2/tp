package seedu.smartlib.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.smartlib.commons.core.GuiSettings;
import seedu.smartlib.logic.commands.exceptions.CommandException;
import seedu.smartlib.model.Model;
import seedu.smartlib.model.ReadOnlySmartLib;
import seedu.smartlib.model.ReadOnlyUserPrefs;
import seedu.smartlib.model.SmartLib;
import seedu.smartlib.model.reader.Reader;
import seedu.smartlib.testutil.ReaderBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddReaderCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Reader validReader = new ReaderBuilder().build();

        CommandResult commandResult = new AddReaderCommand(validReader).execute(modelStub);

        assertEquals(String.format(AddReaderCommand.MESSAGE_SUCCESS, validReader), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validReader), modelStub.readersAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Reader validReader = new ReaderBuilder().build();
        AddReaderCommand addCommand = new AddReaderCommand(validReader);
        ModelStub modelStub = new ModelStubWithPerson(validReader);

        assertThrows(CommandException.class, AddReaderCommand.MESSAGE_DUPLICATE_READER, () ->
            addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Reader alice = new ReaderBuilder().withName("Alice").build();
        Reader bob = new ReaderBuilder().withName("Bob").build();
        AddReaderCommand addAliceCommand = new AddReaderCommand(alice);
        AddReaderCommand addBobCommand = new AddReaderCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddReaderCommand addAliceCommandCopy = new AddReaderCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
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
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addReader(Reader reader) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSmartLib(ReadOnlySmartLib newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlySmartLib getSmartLib() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasReader(Reader reader) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteReader(Reader target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Reader target, Reader editedReader) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Reader> getFilteredReaderList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Reader> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Reader reader;

        ModelStubWithPerson(Reader reader) {
            requireNonNull(reader);
            this.reader = reader;
        }

        @Override
        public boolean hasReader(Reader reader) {
            requireNonNull(reader);
            return this.reader.isSameReader(reader);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Reader> readersAdded = new ArrayList<>();

        @Override
        public boolean hasReader(Reader reader) {
            requireNonNull(reader);
            return readersAdded.stream().anyMatch(reader::isSameReader);
        }

        @Override
        public void addReader(Reader reader) {
            requireNonNull(reader);
            readersAdded.add(reader);
        }

        @Override
        public ReadOnlySmartLib getSmartLib() {
            return new SmartLib();
        }
    }

}
