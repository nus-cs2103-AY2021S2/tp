package seedu.smartlib.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.smartlib.commons.core.GuiSettings;
import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.model.book.Barcode;
import seedu.smartlib.model.book.Book;
import seedu.smartlib.model.book.Isbn;
import seedu.smartlib.model.reader.Reader;
import seedu.smartlib.model.record.Record;

/**
 * The API of the Model component.
 */
public interface Model {

    /** {@code Predicate} that always evaluate to true */
    Predicate<Book> PREDICATE_SHOW_ALL_BOOKS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Reader> PREDICATE_SHOW_ALL_READERS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Record> PREDICATE_SHOW_ALL_RECORDS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' SmartLib file path.
     */
    Path getSmartLibFilePath();

    /**
     * Sets the user prefs' SmartLib file path.
     */
    void setSmartLibFilePath(Path smartLibFilePath);

    /**
     * Replaces SmartLib's data with the data in {@code smartLib}.
     */
    void setSmartLib(ReadOnlySmartLib smartLib);

    /**
     * Returns an immutable copy of the SmartLib.
     */
    ReadOnlySmartLib getSmartLib();

    /**
     * Returns true if a book with the same identity as {@code book} exists in the registered book base.
     */
    boolean hasBook(Book book);

    /**
     * Returns true if a book with the same name as {@code bookName} exists in the registered book base.
     */
    boolean hasBook(Name bookName);

    /**
     * Returns true if a book with the same isbn as {@code isbn} exists in the registered book base.
     */
    boolean hasBook(Isbn isbn);

    /**
     * Returns true if a book with the same barcode as {@code barcode} exists in the registered book base.
     */
    boolean hasBookWithBarcode(Barcode barcode);

    /**
     * Returns true if a book with the same barcode as {@code barcode} is already borrowed in the registered book base.
     */
    boolean isBookWithBarcodeBorrowed(Barcode barcode);

    /**
     * Returns true if the reader can be delete.
     * Condition: currently does not borrow any books
     */
    boolean canDeleteReader(Reader reader);

    /**
     * Returns true if a reader with the same identity as {@code reader} exists in the registered reader base.
     */
    boolean hasReader(Reader reader);

    /**
     * Returns true if a reader with the same name as {@code readerName} exists in the registered reader base.
     */
    boolean hasReader(Name readerName);

    /**
     * Returns true if a reader with the same name as {@code readerName} in the registered reader base
     * has already reached his borrow quota.
     */
    boolean canReaderBorrow(Name readerName);

    /**
     * Returns true if a record with the same identity as {@code record} exists in the registered record base.
     */
    boolean hasRecord(Record record);

    /**
     * Updates the reader's and book's statuses after borrowing.
     */
    boolean borrowBook(Name readerName, Barcode barcode);

    /**
     * Updates the reader's and book's statuses after returning.
     */
    boolean returnBook(Name readerName, Barcode barcode);

    /**
     * Deletes the given book.
     * {@code target} must exist in the registered book base.
     */
    void deleteBook(Book target);

    /**
     * Deletes the given reader.
     * {@code target} must exist in the registered reader base.
     */
    void deleteReader(Reader target);

    /**
     * Adds the given book.
     * {@code book} must not already exist in the registered book base.
     */
    void addBook(Book book);

    /**
     * Gets the list of books with the given bookName
     * @return the list of books
     */
    ArrayList<Book> getBooksByName(Name bookName);

    /**
     * Adds the given reader.
     * {@code reader} must not already exist in the registered reader base.
     */
    void addReader(Reader reader);

    /**
     * Adds the given record.
     * {@code record} must not already exist in the registered record base.
     */
    void addRecord(Record record);

    /**
     * Searches for the latest given record and marks it as returned.
     */
    Record markRecordAsReturned(Record record);

    /**
     * Returns the barcode of the first available (i.e. not borrowed) copy of the book in SmartLib.
     */
    Barcode getBookBarcode(Name bookName);

    /**
     * Returns the Book with the specified barcode.
     */
    Book getBookByBarcode(Barcode barcode);

    /**
     * Returns the Book with the specified ISBN.
     */
    ArrayList<Book> getBooksByIsbn(Isbn isbn);

    /**
     * Returns the barcode of the first available (i.e. not borrowed) copy of the book in SmartLib.
     */
    Barcode getFirstAvailableBookBarcode(Name bookName);

    /**
     * Returns the book name of the book with the corresponding barcode borrowed by the reader in SmartLib.
     */
    Name getBookNameForReturn(Barcode barcode);

    /**
     * Returns the reader name of the reader who has borrowed the book with the corresponding barcode in SmartLib.
     */
    Name getReaderNameForReturn(Barcode barcode);

    /**
     * Replaces the given reader {@code target} with {@code editedReader}.
     * {@code target} must exist in SmartLib.
     * The reader identity of {@code editedReader} must not be the same as another existing reader in SmartLib.
     */
    void setReader(Reader target, Reader editedReader);

    /**
     * Returns an unmodifiable view of the filtered reader list.
     */
    ObservableList<Reader> getFilteredReaderList();

    /**
     * Returns an unmodifiable view of the filtered book list.
     */
    ObservableList<Book> getFilteredBookList();

    /**
     * Returns an unmodifiable view of the filtered record list.
     */
    ObservableList<Record> getFilteredRecordList();

    /**
     * Updates the filter of the filtered book list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredBookList(Predicate<Book> predicate);

    /**
     * Updates the filter of the filtered reader list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredReaderList(Predicate<Reader> predicate);

    /**
     * Updates the filter of the filtered record list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredRecordList(Predicate<Record> predicate);

}
