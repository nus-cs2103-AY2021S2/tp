package seedu.smartlib.logic.commands;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.smartlib.commons.core.GuiSettings;
import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.model.Model;
import seedu.smartlib.model.ReadOnlySmartLib;
import seedu.smartlib.model.ReadOnlyUserPrefs;
import seedu.smartlib.model.book.Barcode;
import seedu.smartlib.model.book.Book;
import seedu.smartlib.model.reader.Reader;
import seedu.smartlib.model.record.Record;

/**
 * A default model stub that have all of the methods failing.
 */
class ModelStub implements Model {
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
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void markRecordAsReturned(Record record) {
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
    public boolean hasBook(Book book) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasBook(Name bookName) {
        return false;
    }

    @Override
    public boolean hasBookWithBarcode(Barcode barcode) {
        return false;
    }

    @Override
    public boolean isBookBorrowed(Name bookName) {
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
    public boolean canReaderBorrow(Name readerName) {
        return false;
    }

    @Override
    public boolean hasRecord(Record record) {
        return false;
    }

    @Override
    public boolean borrowBook(Name readerName, Name bookName) {
        return false;
    }

    @Override
    public boolean returnBook(Name readerName, Name bookName) {
        return false;
    }

    @Override
    public void deleteBook(Book target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteReader(Reader target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addBook(Book book) {
        throw new AssertionError("This method should not be called.");
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
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredBookList(Predicate<Book> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredReaderList(Predicate<Reader> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredRecordList(Predicate<Record> predicate) {
        throw new AssertionError("This method should not be called.");
    }
}
