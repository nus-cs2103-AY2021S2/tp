package seedu.smartlib.logic.commands;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.smartlib.commons.core.GuiSettings;
import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.model.Model;
import seedu.smartlib.model.ReadOnlySmartLib;
import seedu.smartlib.model.ReadOnlyUserPrefs;
import seedu.smartlib.model.book.Barcode;
import seedu.smartlib.model.book.Book;
import seedu.smartlib.model.book.Isbn;
import seedu.smartlib.model.reader.Reader;
import seedu.smartlib.model.record.Record;

/**
 * A default model stub that have all of the methods failing.
 */
class ModelStub implements Model {

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     *
     * @param userPrefs new userPref data.
     */
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    /**
     * Returns the user prefs.
     *
     * @return the user's preferences.
     */
    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    /**
     * Returns the user prefs' GUI settings.
     *
     * @return the user prefs' GUI settings.
     */
    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    /**
     * Sets the user prefs' GUI settings.
     *
     * @param guiSettings the new GUI settings.
     */
    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    /**
     * Returns the user prefs' SmartLib file path.
     *
     * @return the user prefs' SmartLib file path.
     */
    @Override
    public Path getSmartLibFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    /**
     * Sets the user prefs' SmartLib file path.
     *
     * @param smartLibFilePath the new SmartLib file path.
     */
    @Override
    public void setSmartLibFilePath(Path smartLibFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    /**
     * Adds the given reader.
     * {@code reader} must not already exist in the registered reader base.
     *
     * @param reader reader to be added.
     */
    @Override
    public void addReader(Reader reader) {
        throw new AssertionError("This method should not be called.");
    }

    /**
     * Adds the given record.
     * {@code record} must not already exist in the registered record base.
     *
     * @param record record to be added.
     */
    @Override
    public void addRecord(Record record) {
        throw new AssertionError("This method should not be called.");
    }

    /**
     * Searches for the latest given record and marks it as returned.
     *
     * @param record record containing the book to be returned.
     */
    @Override
    public Record markRecordAsReturned(Record record) {
        throw new AssertionError("This method should not be called.");
    }

    /**
     * Returns the barcode of the first available (i.e. not borrowed) copy of the book in SmartLib.
     *
     * @param bookName name of the book to be borrowed
     * @return the barcode of the first available copy of the book in SmartLib
     */
    @Override
    public Barcode getBookBarcode(Name bookName) {
        return null;
    }

    @Override
    public Book getBookByBarcode(Barcode barcode) {
        return null;
    }

    /**
     * Returns the Book with the specified ISBN.
     *
     * @param isbn
     */
    @Override
    public ArrayList<Book> getBooksByIsbn(Isbn isbn) {
        return null;
    }

    @Override
    public Barcode getFirstAvailableBookBarcode(Name bookName) {
        return null;
    }

    /**
     * Returns the book name of the book with the corresponding barcode borrowed by the reader in SmartLib.
     *
     * @param barcode barcode of the book to be returned
     * @return the book name of the book with the corresponding barcode in SmartLib
     */
    @Override
    public Name getBookNameForReturn(Barcode barcode) {
        return null;
    }

    /**
     * Returns the reader name of the reader who has borrowed the book with the corresponding barcode in SmartLib.
     *
     * @param barcode barcode of the book to be returned
     * @return the reader name of the reader who has borrowed the book with the corresponding barcode in SmartLib
     */
    @Override
    public Name getReaderNameForReturn(Barcode barcode) {
        return null;
    }

    /**
     * Replaces SmartLib's data with the data in {@code smartLib}.
     *
     * @param newData the new SmartLib.
     */
    @Override
    public void setSmartLib(ReadOnlySmartLib newData) {
        throw new AssertionError("This method should not be called.");
    }

    /**
     * Returns an immutable copy of SmartLib.
     *
     * @return an immutable copy of SmartLib.
     */
    @Override
    public ReadOnlySmartLib getSmartLib() {
        throw new AssertionError("This method should not be called.");
    }

    /**
     * Returns true if a book with the same identity as {@code book} exists in the registered book base.
     *
     * @param book book to be checked.
     * @return true if a book with the same identity as {@code book} exists in the registered book base, and false
     * otherwise.
     */
    @Override
    public boolean hasBook(Book book) {
        throw new AssertionError("This method should not be called.");
    }

    /**
     * Returns true if a book with the same name as {@code bookName} exists in the registered book base.
     *
     * @param bookName name of the book to be checked.
     * @return true if a book with the same name as {@code bookName} exists in the registered book base, and false
     * otherwise.
     */
    @Override
    public boolean hasBook(Name bookName) {
        return false;
    }

    /**
     * Returns true if a book with the same isbn as {@code isbn} exists in the registered book base.
     *
     * @param isbn
     */
    @Override
    public boolean hasBook(Isbn isbn) {
        return false;
    }

    /**
     * Returns true if a book with the same barcode as {@code barcode} exists in the registered book base.
     *
     * @param barcode barcode of the book to be checked.
     * @return true if a book with the same barcode as {@code barcode} exists in the registered book base, and false
     * otherwise.
     */
    @Override
    public boolean hasBookWithBarcode(Barcode barcode) {
        return false;
    }

    /**
     * Returns true if a book with the same barcode as {@code barcode} is already borrowed in the registered book base.
     *
     * @param barcode barcode of the book to be checked.
     * @return true if a book with the same barcode as {@code barcode} is already borrowed in the registered book base,
     * and false otherwise.
     */
    @Override
    public boolean isBookWithBarcodeBorrowed(Barcode barcode) {
        return false;
    }

    @Override
    public boolean canDeleteReader(Reader reader) {
        return false;
    }

    /**
     * Returns true if a reader with the same identity as {@code reader} exists in the registered reader base.
     *
     * @param reader reader to be checked.
     * @return true if a reader with the same identity as {@code reader} exists in the registered reader base, and
     * false otherwise.
     */
    @Override
    public boolean hasReader(Reader reader) {
        throw new AssertionError("This method should not be called.");
    }

    /**
     * Returns true if a reader with the same name as {@code readerName} exists in the registered reader base.
     *
     * @param readerName name of the reader to be checked.
     * @return true if a reader with the same name as {@code readerName} exists in the registered reader base, and
     * false otherwise.
     */
    @Override
    public boolean hasReader(Name readerName) {
        return false;
    }

    /**
     * Returns true if a reader with the same name as {@code readerName} in the registered reader base
     * has already reached his borrow quota.
     *
     * @param readerName name of the reader to be checked.
     * @return true if a reader with the same name as {@code readerName} in the registered reader base
     * has already reached his borrow quota, and false otherwise.
     */
    @Override
    public boolean canReaderBorrow(Name readerName) {
        return false;
    }

    /**
     * Returns true if a record with the same identity as {@code record} exists in the registered record base.
     *
     * @param record the record to be checked.
     * @return true if a record with the same identity as {@code record} exists in the registered record base, and
     * false otherwise.
     */
    @Override
    public boolean hasRecord(Record record) {
        return false;
    }

    /**
     * Updates the reader's and book's statuses after borrowing.
     *
     * @param readerName name of the reader borrowing the book.
     * @param barcode barcode of the book to be borrowed.
     * @return true if the reader has successfully borrowed the book, and false otherwise.
     */
    @Override
    public boolean borrowBook(Name readerName, Barcode barcode) {
        return false;
    }

    /**
     * Updates the reader's and book's statuses after returning.
     *
     * @param readerName name of the reader returning the book.
     * @param barcode barcode of the book to be returned.
     * @return true if the reader has successfully returned the book, and false otherwise.
     */
    @Override
    public boolean returnBook(Name readerName, Barcode barcode) {
        return false;
    }

    /**
     * Deletes the given book.
     * {@code target} must exist in the registered book base.
     *
     * @param target book to be deleted.
     */
    @Override
    public void deleteBook(Book target) {
        throw new AssertionError("This method should not be called.");
    }

    /**
     * Deletes the given reader.
     * {@code target} must exist in the registered reader base.
     *
     * @param target reader to be deleted.
     */
    @Override
    public void deleteReader(Reader target) {
        throw new AssertionError("This method should not be called.");
    }

    /**
     * Adds the given book.
     * {@code book} must not already exist in the registered book base.
     *
     * @param book book to be added.
     */
    @Override
    public void addBook(Book book) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ArrayList<Book> getBooksByName(Name bookName) {
        return null;
    }

    /**
     * Replaces the given reader {@code target} with {@code editedReader}.
     * {@code target} must exist in SmartLib.
     * The reader identity of {@code editedReader} must not be the same as another existing reader in SmartLib.
     *
     * @param target reader to be replaced.
     * @param editedReader the new reader.
     */
    @Override
    public void setReader(Reader target, Reader editedReader) {
        throw new AssertionError("This method should not be called.");
    }

    /**
     * Returns an unmodifiable view of the filtered reader list.
     *
     * @return an unmodifiable view of the filtered reader list.
     */
    @Override
    public ObservableList<Reader> getFilteredReaderList() {
        throw new AssertionError("This method should not be called.");
    }

    /**
     * Returns an unmodifiable view of the filtered book list.
     *
     * @return an unmodifiable view of the filtered book list.
     */
    @Override
    public ObservableList<Book> getFilteredBookList() {
        throw new AssertionError("This method should not be called.");
    }

    /**
     * Returns an unmodifiable view of the filtered record list.
     *
     * @return an unmodifiable view of the filtered record list.
     */
    @Override
    public ObservableList<Record> getFilteredRecordList() {
        throw new AssertionError("This method should not be called.");
    }

    /**
     * Updates the filter of the filtered book list to filter by the given {@code predicate}.
     *
     * @param predicate new filter of the book list.
     */
    @Override
    public void updateFilteredBookList(Predicate<Book> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    /**
     * Updates the filter of the filtered reader list to filter by the given {@code predicate}.
     *
     * @param predicate new filter of the reader list.
     */
    @Override
    public void updateFilteredReaderList(Predicate<Reader> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    /**
     * Updates the filter of the filtered record list to filter by the given {@code predicate}.
     *
     * @param predicate new filter of the record list.
     */
    @Override
    public void updateFilteredRecordList(Predicate<Record> predicate) {
        throw new AssertionError("This method should not be called.");
    }

}
