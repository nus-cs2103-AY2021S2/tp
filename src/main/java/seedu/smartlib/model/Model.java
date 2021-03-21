package seedu.smartlib.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.smartlib.commons.core.GuiSettings;
import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.model.book.Book;
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
     * Returns the user prefs' smartlib file path.
     */
    Path getSmartLibFilePath();

    /**
     * Sets the user prefs' smartlib file path.
     */
    void setSmartLibFilePath(Path smartLibFilePath);

    /**
     * Replaces smartlib's data with the data in {@code smartLib}.
     */
    void setSmartLib(ReadOnlySmartLib smartLib);

    /** Returns the SmartLib */
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
     * Returns true if a book with the same name as {@code bookName} is already borrowed in the registered book base.
     */
    boolean isBookBorrowed(Name bookName);

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
     * has already borrowed a book.
     */
    boolean hasReaderBorrowed(Name readerName);

    /**
     * Returns true if a record with the same identity as {@code record} exists in the registered record base.
     */
    boolean hasRecord(Record record);

    /**
     * Update reader and book's status
     * @param readerName reader must exist in reader base
     * @param bookName book must exist in book base
     */
    boolean borrowBook(Name readerName, Name bookName);

    /**
     * Deletes the given book.
     * The book must exist in the registered book base.
     */
    void deleteBook(Book target);

    /**
     * Deletes the given reader.
     * The reader must exist in the registered reader base.
     */
    void deleteReader(Reader target);

    /**
     * Adds the given book.
     * {@code book} must not already exist in the registered book base.
     */
    void addBook(Book book);

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
     * Search for the latest given record and mark it as returned
     */
    void markRecordAsReturned(Record record);

    /**
     * Replaces the given reader {@code target} with {@code editedReader}.
     * {@code target} must exist in smartlib.
     * The reader identity of {@code editedReader} must not be the same as another existing reader in smartlib.
     */
    void setReader(Reader target, Reader editedReader);

    /** Returns an unmodifiable view of the filtered reader list */
    ObservableList<Reader> getFilteredReaderList();

    /** Returns an unmodifiable view of the filtered book list */
    ObservableList<Book> getFilteredBookList();

    /**
     * Updates the filter of the filtered book list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredBookList(Predicate<Book> predicate);

    /**
     * Updates the filter of the filtered reader list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredReaderList(Predicate<Reader> predicate);

    void updateFilteredRecordList(Predicate<Record> predicate);
}
