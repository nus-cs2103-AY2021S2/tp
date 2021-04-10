package seedu.smartlib.model;

import static java.util.Objects.requireNonNull;
import static seedu.smartlib.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.smartlib.commons.core.GuiSettings;
import seedu.smartlib.commons.core.LogsCenter;
import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.model.book.Barcode;
import seedu.smartlib.model.book.Book;
import seedu.smartlib.model.book.Isbn;
import seedu.smartlib.model.reader.Reader;
import seedu.smartlib.model.record.Record;

/**
 * Represents the in-memory model of SmartLib's data.
 */
public class ModelManager implements Model {

    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final SmartLib smartLib;
    private final UserPrefs userPrefs;
    private final FilteredList<Book> filteredBooks;
    private final FilteredList<Reader> filteredReaders;
    private final FilteredList<Record> filteredRecords;

    /**
     * Initializes a ModelManager with the given SmartLib and userPrefs.
     *
     * @param smartLib the given SmartLib.
     * @param userPrefs the user's preferences.
     */
    public ModelManager(ReadOnlySmartLib smartLib, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(smartLib, userPrefs);

        logger.fine("Initializing with SmartLib: " + smartLib + " and user prefs " + userPrefs);

        this.smartLib = new SmartLib(smartLib);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredBooks = new FilteredList<>(this.smartLib.getBookList());
        filteredReaders = new FilteredList<>(this.smartLib.getReaderList());
        filteredRecords = new FilteredList<>(this.smartLib.getRecordList());
    }

    /**
     * Initializes a ModelManager with a new SmartLib and new userPrefs.
     */
    public ModelManager() {
        this(new SmartLib(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    /**
     * Returns the user prefs.
     *
     * @return the user's preferences.
     */
    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     *
     * @param userPrefs new userPref data.
     */
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    /**
     * Returns the user prefs' GUI settings.
     *
     * @return the user prefs' GUI settings.
     */
    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    /**
     * Sets the user prefs' GUI settings.
     *
     * @param guiSettings the new GUI settings.
     */
    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    /**
     * Returns the user prefs' SmartLib file path.
     *
     * @return the user prefs' SmartLib file path.
     */
    @Override
    public Path getSmartLibFilePath() {
        return userPrefs.getSmartLibFilePath();
    }

    /**
     * Sets the user prefs' SmartLib file path.
     *
     * @param smartLibFilePath the new SmartLib file path.
     */
    @Override
    public void setSmartLibFilePath(Path smartLibFilePath) {
        requireNonNull(smartLibFilePath);
        userPrefs.setSmartLibFilePath(smartLibFilePath);
    }

    //=========== SmartLib ================================================================================

    /**
     * Returns an immutable copy of SmartLib.
     *
     * @return an immutable copy of SmartLib.
     */
    @Override
    public ReadOnlySmartLib getSmartLib() {
        return smartLib;
    }

    /**
     * Replaces SmartLib's data with the data in {@code smartLib}.
     *
     * @param smartLib the new SmartLib.
     */
    @Override
    public void setSmartLib(ReadOnlySmartLib smartLib) {
        this.smartLib.resetData(smartLib);
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
        requireNonNull(book);
        return smartLib.hasBook(book);
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
        requireAllNonNull(bookName);
        return smartLib.hasBook(bookName);
    }

    /**
     * Returns true if a book with the same isbn as {@code isbn} exists in the registered book base.
     *
     * @param isbn
     */
    @Override
    public boolean hasBook(Isbn isbn) {
        requireAllNonNull(isbn);
        return smartLib.hasBook(isbn);
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
        requireNonNull(barcode);
        assert(Barcode.isValidBarcode(barcode.getValue()));
        return smartLib.hasBookWithBarcode(barcode);
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
        return smartLib.isBookWithBarcodeBorrowed(barcode);
    }

    /**
     * Returns true if the reader can be delete.
     * Condition: currently does not borrow any books
     */
    @Override
    public boolean canDeleteReader(Reader reader) {
        return !smartLib.hasReaderBorrowedBooks(reader);
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
        requireNonNull(reader);
        return smartLib.hasReader(reader);
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
        requireAllNonNull(readerName);
        return smartLib.hasReader(readerName);
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
        requireAllNonNull(readerName);
        return smartLib.canReaderBorrow(readerName);
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
        requireNonNull(record);
        return smartLib.hasRecord(record);
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
        requireAllNonNull(barcode, readerName);
        boolean status = smartLib.isBookBorrowed(readerName, barcode);
        updateFilteredReaderList(PREDICATE_SHOW_ALL_READERS);
        updateFilteredBookList(PREDICATE_SHOW_ALL_BOOKS);
        updateFilteredRecordList(PREDICATE_SHOW_ALL_RECORDS);
        return status;
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
        requireAllNonNull(barcode, readerName);
        boolean status = smartLib.isBookReturned(readerName, barcode);
        updateFilteredReaderList(PREDICATE_SHOW_ALL_READERS);
        updateFilteredBookList(PREDICATE_SHOW_ALL_BOOKS);
        updateFilteredRecordList(PREDICATE_SHOW_ALL_RECORDS);
        return status;
    }

    /**
     * Deletes the given book.
     * {@code target} must exist in the registered book base.
     *
     * @param target book to be deleted.
     */
    @Override
    public void deleteBook(Book target) {
        requireNonNull(target);
        smartLib.removeBook(target);
    }

    /**
     * Deletes the given reader.
     * {@code target} must exist in the registered reader base.
     *
     * @param target reader to be deleted.
     */
    @Override
    public void deleteReader(Reader target) {
        requireNonNull(target);
        smartLib.removeReader(target);
    }

    /**
     * Adds the given book.
     * {@code book} must not already exist in the registered book base.
     *
     * @param book book to be added.
     */
    @Override
    public void addBook(Book book) {
        requireNonNull(book);
        smartLib.addBook(book);
        updateFilteredBookList(PREDICATE_SHOW_ALL_BOOKS);
    }

    /**
     * Adds the given reader.
     * {@code reader} must not already exist in the registered reader base.
     *
     * @param reader reader to be added.
     */
    @Override
    public void addReader(Reader reader) {
        requireNonNull(reader);
        smartLib.addReader(reader);
        updateFilteredReaderList(PREDICATE_SHOW_ALL_READERS);
    }

    /**
     * Adds the given record.
     * {@code record} must not already exist in the registered record base.
     *
     * @param record record to be added.
     */
    @Override
    public void addRecord(Record record) {
        requireNonNull(record);
        smartLib.addRecord(record);
        updateFilteredRecordList(PREDICATE_SHOW_ALL_RECORDS);
    }

    /**
     * Searches for the latest given record and marks it as returned.
     *
     * @param record record containing the book to be returned.
     */
    @Override
    public Record markRecordAsReturned(Record record) {
        return smartLib.markRecordAsReturned(record);
    }

    /**
     * Returns the barcode of the first available (i.e. not borrowed) copy of the book in SmartLib.
     *
     * @param bookName name of the book to be borrowed
     * @return the barcode of the first available copy of the book in SmartLib
     */
    @Override
    public Barcode getBookBarcode(Name bookName) {
        requireNonNull(bookName);

        ArrayList<Book> books = smartLib.getBooksByName(bookName);

        for (Book b : books) {
            return b.getBarcode();
        }

        return null;
    }

    /**
     * Returns the book with the given barcode.
     *
     * @param barcode the specified barcode
     * @return the book with the given barcode
     */
    @Override
    public Book getBookByBarcode(Barcode barcode) {
        requireNonNull(barcode);
        return smartLib.getBookByBarcode(barcode);
    }

    /**
     * Retrieves the list of books that has the isbn.
     *
     * @param isbn Isbn to query
     * @return the list of books with given Isbn
     */
    @Override
    public ArrayList<Book> getBooksByIsbn(Isbn isbn) {
        requireNonNull(isbn);
        return smartLib.getBooksByIsbn(isbn);
    }

    /**
     * Retrieves the list of books that has the bookName.
     *
     * @param bookName bookName to query
     * @return the list of bookNames
     */
    @Override
    public ArrayList<Book> getBooksByName(Name bookName) {
        requireNonNull(bookName);
        return smartLib.getBooksByName(bookName);
    }
    /**
     * Returns the barcode of the first available (i.e. not borrowed) copy of the book in SmartLib.
     *
     * @param bookName name of the book to be borrowed
     * @return the barcode of the first available copy of the book in SmartLib
     */
    public Barcode getFirstAvailableBookBarcode(Name bookName) {
        requireNonNull(bookName);

        ArrayList<Book> books = smartLib.getBooksByName(bookName);

        for (Book b : books) {
            if (!b.isBorrowed()) {
                return b.getBarcode();
            }
        }
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
        requireNonNull(barcode);

        Book book = smartLib.getBookByBarcode(barcode);
        return book == null ? null : book.getName();
    }

    /**
     * Returns the reader name of the reader who has borrowed the book with the corresponding barcode in SmartLib.
     *
     * @param barcode barcode of the book to be returned
     * @return the reader name of the reader who has borrowed the book with the corresponding barcode in SmartLib
     */
    @Override
    public Name getReaderNameForReturn(Barcode barcode) {
        requireNonNull(barcode);

        Reader reader = smartLib.getReaderByBarcode(barcode);
        return reader == null ? null : reader.getName();
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
        requireAllNonNull(target, editedReader);

        smartLib.setReader(target, editedReader);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the filtered reader list.
     *
     * @return an unmodifiable view of the filtered reader list.
     */
    @Override
    public ObservableList<Reader> getFilteredReaderList() {
        return filteredReaders;
    }

    /**
     * Returns an unmodifiable view of the filtered book list.
     *
     * @return an unmodifiable view of the filtered book list.
     */
    @Override
    public ObservableList<Book> getFilteredBookList() {
        return filteredBooks;
    }

    /**
     * Returns an unmodifiable view of the filtered record list.
     *
     * @return an unmodifiable view of the filtered record list.
     */
    @Override
    public ObservableList<Record> getFilteredRecordList() {
        return filteredRecords;
    }

    /**
     * Updates the filter of the filtered book list to filter by the given {@code predicate}.
     *
     * @param predicate new filter of the book list.
     */
    @Override
    public void updateFilteredBookList(Predicate<Book> predicate) {
        requireNonNull(predicate);
        filteredBooks.setPredicate(predicate);
    }

    /**
     * Updates the filter of the filtered reader list to filter by the given {@code predicate}.
     *
     * @param predicate new filter of the reader list.
     */
    @Override
    public void updateFilteredReaderList(Predicate<Reader> predicate) {
        requireNonNull(predicate);
        filteredReaders.setPredicate(predicate);
    }

    /**
     * Updates the filter of the filtered record list to filter by the given {@code predicate}.
     *
     * @param predicate new filter of the record list.
     */
    @Override
    public void updateFilteredRecordList(Predicate<Record> predicate) {
        requireNonNull(predicate);
        filteredRecords.setPredicate(predicate);
    }

    /**
     * Checks if this ModelManager is equal to another ModelManager.
     *
     * @param obj the other ModelManager to be compared.
     * @return true if this ModelManager is equal to the other ModelManager, and false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return smartLib.equals(other.smartLib)
                && userPrefs.equals(other.userPrefs)
                && filteredReaders.equals(other.filteredReaders)
                && filteredBooks.equals(other.filteredBooks)
                && filteredRecords.equals(other.filteredRecords);
    }

}
