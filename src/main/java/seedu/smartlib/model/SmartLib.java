package seedu.smartlib.model;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.model.book.Book;
import seedu.smartlib.model.book.UniqueBookList;
import seedu.smartlib.model.reader.Reader;
import seedu.smartlib.model.reader.UniqueReaderList;
import seedu.smartlib.model.record.DateBorrowed;
import seedu.smartlib.model.record.Record;
import seedu.smartlib.model.record.UniqueRecordList;

/**
 * Wraps all data at the SmartLib level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class SmartLib implements ReadOnlySmartLib {

    public static final int QUOTA = 4;
    public static final long DURATION = 14L;

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
        return getReaderByName(readerName) != null;
    }

    /**
     * Returns the Reader object whose name is specified by readerName
     * @param readerName readerName
     * @return Reader Object, null if does not exist
     */
    public Reader getReaderByName(Name readerName) {
        requireNonNull(readerName);
        for (Reader reader : readers) {
            if (reader.getName().equals(readerName)) {
                return reader;
            }
        }
        return null;
    }

    /**
     * Returns true if the reader has already borrowed a book
     * @param readerName must exist in reader base
     * @return true if the reader has neither borrowed all quota of books
     * nor has overdue books. false by default
     */
    public boolean canReaderBorrow(Name readerName) {
        requireNonNull(readerName);
        Reader reader = getReaderByName(readerName);
        if (reader == null) {
            return false;
        } else {
            return !hasReaderUsedUpQuota(reader) && !hasReaderOverdueBooks(reader);
        }
    }

    /**
     * Checks if a reader has used up his borrowing quota
     * @param reader reader to check, non null
     * @return true if the reader has borrowed QUOTA number of books
     */
    public boolean hasReaderUsedUpQuota(Reader reader) {
        return reader.getBorrows().size() == QUOTA;
    }

    /**
     * Checks if a reader has overdue books
     * @param reader reader to check, non null
     * @return true if the reader has overdue books
     */
    public boolean hasReaderOverdueBooks(Reader reader) {
        return reader.hasOverdueBooks();
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
     * Replaces the given book {@code target} in the list with {@code editedBook}.
     * {@code target} must exist in SmartLib.
     * The book identity of {@code editedBook} must not be the same as another existing book in SmartLib.
     */
    public void setBook(Book target, Book editedBook) {
        requireNonNull(editedBook);

        books.setBook(target, editedBook);
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
        return getBookByName(bookName) != null;
    }


    /**
     * Retrieve the Book object whose name is specified by bookName
     * @param bookName bookName
     * @return Book Object, null if does not exist
     */
    public Book getBookByName(Name bookName) {
        requireNonNull(bookName);
        for (Book book: books) {
            if (book.getName().equals(bookName)) {
                return book;
            }
        }
        return null;
    }

    /**
     * Returns true if a book with the same name as {@code bookName} is Already borrowed.
     * @param bookName name of book, must exist in book base
     * @return true if book is borrowed, if book not found, return true as well
     */
    public boolean isBookBorrowed(Name bookName) {
        requireNonNull(bookName);
        Book book = getBookByName(bookName);
        if (book == null) {
            return true;
        } else {
            return book.isBorrowed();
        }
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

    /**
     * Update reader and book's status after a borrowing activity
     * @param readerName readerName, must exist in reader base and
     *                   must satisfy requirement for borrowing
     * @param bookName bookName, must exist in book base
     */
    public boolean borrowBook(Name readerName, Name bookName) {
        Reader reader = getReaderByName(readerName);
        Book book = getBookByName(bookName);
        if (reader == null || book == null) {
            return false;
        }
        reader.getBorrows().put(bookName, new DateBorrowed(LocalDate.now()));
        Reader editedReader = new Reader(reader.getName(), reader.getPhone(), reader.getEmail(),
                reader.getAddress(), reader.getTags(), reader.getBorrows());
        Book editedBook = new Book(book.getName(), book.getAuthor(), book.getPublisher(),
                book.getIsbn(), book.getGenre(), readerName);
        setReader(reader, editedReader);
        setBook(book, editedBook);
        return true;
    }

    /**
     * Update reader and book's status after a returning activity
     * @param readerName readerName, must exist in reader base
     * @param bookName bookName, must exist in book base
     */
    public boolean returnBook(Name readerName, Name bookName) {
        Reader reader = getReaderByName(readerName);
        Book book = getBookByName(bookName);
        if (reader == null || book == null) {
            return false;
        }
        if (!reader.getBorrows().containsKey(bookName) || !book.getBorrowerName().equals(readerName)) {
            System.out.println(reader.getBorrows());
            System.out.println(book.getBorrowerName());
            System.out.println("Reader not containing bookname key or Book not containing readername");
            return false;
        }
        reader.getBorrows().remove(bookName);
        Reader editedReader = new Reader(reader.getName(), reader.getPhone(), reader.getEmail(),
                reader.getAddress(), reader.getTags(), reader.getBorrows());
        Book editedBook = new Book(book.getName(), book.getAuthor(), book.getPublisher(), book.getIsbn(), book.getGenre());
        setReader(reader, editedReader);
        setBook(book, editedBook);
        return true;
    }
}
