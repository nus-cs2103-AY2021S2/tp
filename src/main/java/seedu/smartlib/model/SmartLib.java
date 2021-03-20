package seedu.smartlib.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.model.book.Book;
import seedu.smartlib.model.book.UniqueBookList;
import seedu.smartlib.model.reader.Reader;
import seedu.smartlib.model.reader.UniqueReaderList;
import seedu.smartlib.model.record.Record;
import seedu.smartlib.model.record.UniqueRecordList;

/**
 * Wraps all data at the SmartLib level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class SmartLib implements ReadOnlySmartLib {

    private final UniqueBookList books;
    private final UniqueReaderList readers;
    private final UniqueRecordList records;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        books = new UniqueBookList();
        readers = new UniqueReaderList();
        records = new UniqueRecordList();
    }

    public SmartLib() {}

    /**
     * Creates a SmartLib using the Readers in the {@code toBeCopied}
     */
    public SmartLib(ReadOnlySmartLib toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the book list with {@code books}.
     * {@code books} must not contain duplicate books.
     */
    public void setBooks(List<Book> books) {
        this.books.setBooks(books);
    }

    /**
     * Replaces the contents of the reader list with {@code readers}.
     * {@code readers} must not contain duplicate readers.
     */
    public void setReaders(List<Reader> readers) {
        this.readers.setReaders(readers);
    }

    /**
     * Replaces the contents of the record list with {@code records}.
     * {@code records} must not contain duplicate records.
     */
    public void setRecords(List<Record> records) {
        this.records.setRecords(records);
    }

    /**
     * Resets the existing data of this {@code SmartLib} with {@code newData}.
     */
    public void resetData(ReadOnlySmartLib newData) {
        requireNonNull(newData);

        setBooks(newData.getBookList());
        setReaders(newData.getReaderList());
        setRecords(newData.getRecordList());
    }

    //// reader-level operations

    /**
     * Returns true if a reader with the same identity as {@code reader} exists in the registered reader base.
     */
    public boolean hasReader(Reader reader) {
        requireNonNull(reader);
        return readers.contains(reader);
    }

    /**
     * Returns true if a reader with the same identity as {@code reader} exists in the registered reader base.
     */
    public boolean hasReader(Name readerName) {
        requireNonNull(readerName);
        for (Reader reader : readers) {
            if (reader.getName().equals(readerName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if a record with the same identity as {@code record} exists in the registered record base.
     */
    public boolean hasRecord(Record record) {
        requireNonNull(record);
        return records.contains(record);
    }

    /**
     * Adds a reader to the registered reader base.
     * The reader must not already exist in the registered reader base.
     */
    public void addReader(Reader p) {
        readers.addReader(p);
    }

    /**
     * Adds a record to the registered record base.
     * The record must not already exist in the registered record base.
     */
    public void addRecord(Record r) {
        records.addRecord(r);
    }

    /**
     * Replaces the given reader {@code target} in the list with {@code editedReader}.
     * {@code target} must exist in SmartLib.
     * The reader identity of {@code editedReader} must not be the same as another existing reader in SmartLib.
     */
    public void setReader(Reader target, Reader editedReader) {
        requireNonNull(editedReader);

        readers.setReader(target, editedReader);
    }

    /**
     * Removes {@code key} from this {@code SmartLib}.
     * {@code key} must exist in the SmartLib registered reader base.
     */
    public void removeReader(Reader key) {
        readers.remove(key);
    }

    //// book-level operations

    /**
     * Returns true if a book with the same identity as {@code book} exists in the registered book base.
     */
    public boolean hasBook(Book book) {
        requireNonNull(book);
        return books.contains(book);
    }

    /**
     * Returns true if a book with the same identity as {@code book} exists in the registered book base.
     */
    public boolean hasBook(Name bookName) {
        requireNonNull(bookName);
        for (Book book: books) {
            if (book.getName().equals(bookName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a book to the registered book base.
     * The book must not already exist in the registered book base.
     */
    public void addBook(Book toAdd) {
        books.addBook(toAdd);
    }

    /**
     * Removes {@code book} from this {@code SmartLib}.
     * {@code book} must exist in the SmartLib registered book base.
     */
    public void removeBook(Book book) {
        books.remove(book);
    }

    //// util methods

    @Override
    public String toString() {
        return readers.asUnmodifiableObservableList().size() + " readers"
                + "\n" + records.asUnmodifiableObservableList().size() + " records";
        // TODO: refine later
    }

    @Override
    public ObservableList<Book> getBookList() {
        return books.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Reader> getReaderList() {
        return readers.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Record> getRecordList() {
        return records.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SmartLib // instanceof handles nulls
                && readers.equals(((SmartLib) other).readers));
    }

    @Override
    public int hashCode() {
        return readers.hashCode();
    }
}
