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
import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.logic.commands.exceptions.CommandException;
import seedu.smartlib.model.Model;
import seedu.smartlib.model.ReadOnlySmartLib;
import seedu.smartlib.model.ReadOnlyUserPrefs;
import seedu.smartlib.model.SmartLib;
import seedu.smartlib.model.book.Book;
import seedu.smartlib.model.reader.Reader;
import seedu.smartlib.model.record.Record;
import seedu.smartlib.testutil.ReaderBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullReader_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddReaderCommand(null));
    }

    @Test
    public void execute_readerAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingReaderAdded modelStub = new ModelStubAcceptingReaderAdded();
        Reader validReader = new ReaderBuilder().build();

        CommandResult commandResult = new AddReaderCommand(validReader).execute(modelStub);

        assertEquals(String.format(AddReaderCommand.MESSAGE_SUCCESS, validReader), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validReader), modelStub.readersAdded);
    }

    @Test
    public void execute_duplicateReader_throwsCommandException() {
        Reader validReader = new ReaderBuilder().build();
        AddReaderCommand addCommand = new AddReaderCommand(validReader);
        ModelStub modelStub = new ModelStubWithReader(validReader);

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

        // different reader -> returns false
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
        public Path getSmartLibFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSmartLibFilePath(Path smartLibFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addReader(Reader reader) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addRecord(Record record) {

        }

        @Override
        public void markRecordAsReturned(Record record) {

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
        public boolean hasBook(Book book) {
            return false;
        }

        @Override
        public boolean hasBook(Name bookName) {
            return false;
        }

        @Override
        public boolean hasReader(Reader reader) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasReader(Name readerName) {
            return false;
        }

        @Override
        public boolean hasRecord(Record record) {
            return false;
        }

        @Override
        public void deleteBook(Book target) {

        }

        @Override
        public void deleteReader(Reader target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addBook(Book book) {

        }

        @Override
        public void setReader(Reader target, Reader editedReader) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Reader> getFilteredReaderList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Book> getFilteredBookList() {
            return null;
        }

        @Override
        public void updateFilteredBookList(Predicate<Book> predicate) {

        }

        @Override
        public void updateFilteredReaderList(Predicate<Reader> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredRecordList(Predicate<Record> predicate) {

        }
    }

    /**
     * A Model stub that contains a single reader.
     */
    private class ModelStubWithReader extends ModelStub {
        private final Reader reader;

        ModelStubWithReader(Reader reader) {
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
     * A Model stub that always accept the reader being added.
     */
    private class ModelStubAcceptingReaderAdded extends ModelStub {
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
