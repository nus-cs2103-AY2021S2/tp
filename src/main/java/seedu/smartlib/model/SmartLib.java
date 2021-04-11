package seedu.smartlib.model;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.model.book.Barcode;
import seedu.smartlib.model.book.Book;
import seedu.smartlib.model.book.Isbn;
import seedu.smartlib.model.book.UniqueBookList;
import seedu.smartlib.model.reader.Reader;
import seedu.smartlib.model.reader.UniqueReaderList;
import seedu.smartlib.model.record.DateBorrowed;
import seedu.smartlib.model.record.Record;
import seedu.smartlib.model.record.UniqueRecordList;

/**
 * Wraps all data at the SmartLib level.
 * Duplicates are not allowed (by .isSamePerson comparison).
 */
public class SmartLib implements ReadOnlySmartLib {

    public static final int QUOTA = 4;
    public static final long DAYS_BORROW_ALLOWED = 14L;
    public static final int HOURS_BORROW_ALLOWED = (int) DAYS_BORROW_ALLOWED * 24;

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

    /**
     * An empty constructor for SmartLib.
     */
    public SmartLib() {}

    /**
     * Creates a SmartLib using the SmartLib data in the {@code toBeCopied}
     *
     * @param toBeCopied data of a SmartLib.
     */
    public SmartLib(ReadOnlySmartLib toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the book list with {@code books}.
     * {@code books} must not contain duplicate books.
     *
     * @param books new list of books.
     */
    public void setBooks(List<Book> books) {
        this.books.setBooks(books);
    }

    /**
     * Replaces the contents of the reader list with {@code readers}.
     * {@code readers} must not contain duplicate readers.
     *
     * @param readers new list of readers.
     */
    public void setReaders(List<Reader> readers) {
        this.readers.setReaders(readers);
    }

    /**
     * Replaces the contents of the record list with {@code records}.
     * {@code records} must not contain duplicate records.
     *
     * @param records new list of records.
     */
    public void setRecords(List<Record> records) {
        this.records.setRecords(records);
    }

    /**
     * Resets the existing data of this {@code SmartLib} with {@code newData}.
     *
     * @param newData an empty SmartLib.
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
     *
     * @param reader reader to be checked.
     * @return true if a reader with the same identity as {@code reader} exists in the registered reader base, and
     * false otherwise.
     */
    public boolean hasReader(Reader reader) {
        requireNonNull(reader);
        return readers.contains(reader);
    }

    /**
     * Returns true if a reader with the same name as {@code readerName} exists in the registered reader base.
     *
     * @param readerName name of the reader to be checked.
     * @return true if a reader with the same name as {@code readerName} exists in the registered reader base, and
     * false otherwise.
     */
    public boolean hasReader(Name readerName) {
        requireNonNull(readerName);
        return getReaderByName(readerName) != null;
    }

    /**
     * Returns the reader whose name is specified by readerName.
     *
     * @param readerName name of the specified reader.
     * @return reader whose name is specified by readerName.
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
     * Returns true if the reader has already borrowed a book.
     *
     * @param readerName must exist in reader base.
     * @return true if the reader has neither borrowed all quota of books nor has overdue books, and false otherwise.
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
     * Checks if a reader has used up his borrowing quota.
     *
     * @param reader reader to check, non null.
     * @return true if the reader has reached his or her borrowed QUOTA.
     */
    public boolean hasReaderUsedUpQuota(Reader reader) {
        return reader.getBorrows().size() == QUOTA;
    }

    /**
     * Checks if a reader has overdue books.
     *
     * @param reader reader to check, non null.
     * @return true if the reader has overdue books, and false otherwise.
     */
    public boolean hasReaderOverdueBooks(Reader reader) {
        return reader.hasOverdueBooks();
    }

    /**
     * Checks if a reader has borrowed any books.
     *
     * @param reader reader to check, non null.
     * @return true if the reader has borrowed at least one books, and false otherwise.
     */
    public boolean hasReaderBorrowedBooks(Reader reader) {
        return reader.hasBorrowedBooks();
    }

    /**
     * Returns true if a record with the same identity as {@code record} exists in the registered record base.
     *
     * @param record record to be checked.
     * @return true if a record with the same identity as {@code record} exists in the registered record base, and
     * false otherwise.
     */
    public boolean hasRecord(Record record) {
        requireNonNull(record);
        return records.contains(record);
    }

    /**
     * Adds a reader to the registered reader base.
     * The reader must not already exist in the registered reader base.
     *
     * @param p reader to be added.
     */
    public void addReader(Reader p) {
        readers.addReader(p);
    }

    /**
     * Adds a record to the registered record base.
     * The record must not already exist in the registered record base.
     *
     * @param r record to be added.
     */
    public void addRecord(Record r) {
        records.addRecord(r);
    }

    /**
     * Replaces the given reader {@code target} in the list with {@code editedReader}.
     * {@code target} must exist in SmartLib.
     * The reader identity of {@code editedReader} must not be the same as another existing reader in SmartLib.
     *
     * @param target reader to be replaced.
     * @param editedReader the new reader.
     */
    public void setReader(Reader target, Reader editedReader) {
        requireNonNull(editedReader);

        readers.setReader(target, editedReader);
    }

    /**
     * Replaces the given book {@code target} in the list with {@code editedBook}.
     * {@code target} must exist in SmartLib.
     * The book identity of {@code editedBook} must not be the same as another existing book in SmartLib.
     *
     * @param target book to be replaced.
     * @param editedBook the new book.
     */
    public void setBook(Book target, Book editedBook) {
        requireNonNull(editedBook);

        books.setBook(target, editedBook);
    }

    /**
     * Replaces the given record {@code target} in the list with {@code editedRecord}.
     * {@code target} must exist in SmartLib.
     * The record identity of {@code editedBook} must not be the same as another existing record in SmartLib.
     *
     * @param target record to be replaced.
     * @param editedRecord the new book.
     */
    public void setRecord(Record target, Record editedRecord) {
        requireNonNull(editedRecord);

        records.setRecord(target, editedRecord);
    }

    /**
     * Removes {@code key} from this {@code SmartLib}.
     * {@code key} must exist in the SmartLib registered reader base.
     *
     * @param key reader to be deleted.
     */
    public void removeReader(Reader key) {
        readers.remove(key);
    }

    //// book-level operations

    /**
     * Returns true if a book with the same identity as {@code book} exists in the registered book base.
     *
     * @param book book to be checked.
     * @return true if a book with the same identity as {@code book} exists in the registered book base, and false
     * otherwise.
     */
    public boolean hasBook(Book book) {
        requireNonNull(book);
        return books.contains(book);
    }

    /**
     * Returns true if a book with the same name as {@code bookName} exists in the registered book base.
     *
     * @param bookName name of the book to be checked.
     * @return true if a book with the same name as {@code bookName} exists in the registered book base, and false
     * otherwise.
     */
    public boolean hasBook(Name bookName) {
        requireNonNull(bookName);

        ArrayList<Book> booksWithName = getBooksByName(bookName);
        requireNonNull(booksWithName);

        return booksWithName.size() > 0;
    }

    /**
     * Returns true if a book with the same isbn as {@code isbn} exists in the registered book base.
     *
     * @param isbn Isbn of the book to be checked.
     * @return true if a book with the same name as {@code bookName} exists in the registered book base, and false
     * otherwise.
     */
    public boolean hasBook(Isbn isbn) {
        requireNonNull(isbn);

        ArrayList<Book> booksWithIsbn = getBooksByIsbn(isbn);
        requireNonNull(booksWithIsbn);

        return booksWithIsbn.size() > 0;
    }

    /**
     * Returns true if a book with the same barcode as {@code book} exists in the registered book base.
     *
     * @param barcode barcode of the book to be checked.
     * @return true if a book with the same barcode as {@code book} exists in the registered book base, and false
     * otherwise.
     */
    public boolean hasBookWithBarcode(Barcode barcode) {
        requireNonNull(barcode);
        assert(Barcode.isValidBarcode(barcode.getValue()));
        return getBookByBarcode(barcode) != null;
    }

    /**
     * Retrieves the Book object whose barcode is specified by the given input.
     *
     * @param barcode Book's barcode.
     * @return Book Object, null if does not exist.
     */
    public Book getBookByBarcode(Barcode barcode) {
        requireNonNull(barcode);
        assert(Barcode.isValidBarcode(barcode.getValue()));
        for (Book book : books) {
            if (book.getBarcode().equals(barcode)) {
                return book;
            }
        }
        return null;
    }

    /**
     * Retrieves the Reader object whose name is paired with the given barcode in the record.
     *
     * @param barcode Book's barcode borrowed by the reader.
     * @return Reader Object, null if does not exist.
     */
    public Reader getReaderByBarcode(Barcode barcode) {
        requireNonNull(barcode);
        assert(Barcode.isValidBarcode(barcode.getValue()));
        for (Reader reader : readers) {
            if (reader.getBorrows().containsKey(getBookByBarcode(barcode))) {
                return reader;
            }
        }
        return null;
    }

    /**
     * Retrieves a list of Book objects whose name is specified by bookName.
     *
     * @param bookName Book's name.
     * @return list of Book objects, an empty list if there is no such book.
     */
    public ArrayList<Book> getBooksByName(Name bookName) {
        requireNonNull(bookName);
        ArrayList<Book> booksWithName = new ArrayList<>();
        for (Book book : books) {
            if (book.getName().equals(bookName)) {
                booksWithName.add(book);
            }
        }
        return booksWithName;
    }

    /**
     * Retrieves a list of Book objects whose name is specified by its ISBN.
     *
     * @param isbn Book's Isbn.
     * @return list of Book objects, an empty list if there is no such book.
     */
    public ArrayList<Book> getBooksByIsbn(Isbn isbn) {
        requireNonNull(isbn);
        ArrayList<Book> booksWithIsbn = new ArrayList<>();
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                booksWithIsbn.add(book);
            }
        }
        return booksWithIsbn;
    }

    /**
     * Returns true if a book with the same barcode as {@code barcode} is already borrowed.
     *
     * @param barcode barcode of book, which must exist in the book base.
     * @return true if the book is borrowed, and false otherwise.
     */
    public boolean isBookWithBarcodeBorrowed(Barcode barcode) {
        requireNonNull(barcode);

        for (Book book : books) {
            if (book.getBarcode().equals(barcode)) {
                if (book.isBorrowed()) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Adds a book to the registered book base.
     * The book must not already exist in the registered book base.
     *
     * @param toAdd book to be added.
     */
    public void addBook(Book toAdd) {
        books.addBook(toAdd);
    }

    /**
     * Removes {@code book} from this {@code SmartLib}.
     * {@code book} must exist in the SmartLib registered book base.
     *
     * @param book book to be deleted.
     */
    public void removeBook(Book book) {
        books.remove(book);
    }

    //// util methods

    /**
     * Returns the current size of this SmartLib in String format.
     *
     * @return the current size of this SmartLib in String format.
     */
    @Override
    public String toString() {
        return readers.asUnmodifiableObservableList().size() + " readers"
                + "\n" + records.asUnmodifiableObservableList().size() + " records";
    }

    /**
     * Returns the list of books in SmartLib.
     *
     * @return the list of books in SmartLib.
     */
    @Override
    public ObservableList<Book> getBookList() {
        return books.asUnmodifiableObservableList();
    }

    /**
     * Returns the list of readers in SmartLib.
     *
     * @return the list of readers in SmartLib.
     */
    @Override
    public ObservableList<Reader> getReaderList() {
        return readers.asUnmodifiableObservableList();
    }

    /**
     * Returns the list of records in SmartLib.
     *
     * @return the list of records in SmartLib.
     */
    @Override
    public ObservableList<Record> getRecordList() {
        return records.asUnmodifiableObservableList();
    }

    /**
     * Checks if this SmartLib is equal to another SmartLib.
     *
     * @param other the other SmartLib to be compared.
     * @return true if this SmartLib is equal to the other SmartLib, and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SmartLib // instanceof handles nulls
                && readers.equals(((SmartLib) other).readers));
    }

    /**
     * Generates a hashcode for this SmartLib.
     *
     * @return the hashcode for this SmartLib.
     */
    @Override
    public int hashCode() {
        ArrayList<Integer> listOfHashCodes = new ArrayList<>();
        listOfHashCodes.add(books.hashCode());
        listOfHashCodes.add(readers.hashCode());
        listOfHashCodes.add(records.hashCode());
        return listOfHashCodes.hashCode();
    }

    /**
     * Updates the reader's and book's statuses after borrowing.
     *
     * @param readerName readerName, must exist in reader base and must satisfy requirement for borrowing
     * @param barcode barcode of book, must exist in book base
     * @return true if borrow is successful, and false otherwise
     */
    public boolean isBookBorrowed(Name readerName, Barcode barcode) {
        Reader reader = getReaderByName(readerName);
        if (reader == null) {
            return false;
        }

        Book book = getBookByBarcode(barcode);
        if (book == null) {
            return false;
        }

        if (canReaderBorrow(readerName) && !book.isBorrowed()) {
            reader.getBorrows().put(book, new DateBorrowed(LocalDateTime.now()));
            Reader editedReader = new Reader(reader.getName(), reader.getPhone(), reader.getEmail(),
                    reader.getAddress(), reader.getTags(), reader.getBorrows());
            Book editedBook = new Book(book.getName(), book.getAuthor(), book.getPublisher(),
                    book.getIsbn(), book.getBarcode(), book.getGenre(),
                    readerName, new DateBorrowed(LocalDateTime.now()));
            setReader(reader, editedReader);
            setBook(book, editedBook);
            return true;
        }

        return false;
    }

    /**
     * Updates the reader's and book's statuses after returning.
     *
     * @param readerName readerName, must exist in reader base
     * @param barcode barcode of book, must exist in book base
     * @return true if return is successful, and false otherwise
     */
    public boolean isBookReturned(Name readerName, Barcode barcode) {
        Reader reader = getReaderByName(readerName);
        if (reader == null) {
            return false;
        }

        Book book = getBookByBarcode(barcode);
        if (book == null) {
            return false;
        }

        if (book.getBorrowerName() == null) {
            return false;
        }

        if (!reader.getBorrows().containsKey(book) || !book.getBorrowerName().equals(readerName)) {
            return false;
        }

        reader.getBorrows().remove(book);
        Reader editedReader = new Reader(reader.getName(), reader.getPhone(), reader.getEmail(),
                reader.getAddress(), reader.getTags(), reader.getBorrows());
        Book editedBook = new Book(book.getName(), book.getAuthor(), book.getPublisher(), book.getIsbn(),
                book.getBarcode(), book.getGenre());
        setReader(reader, editedReader);
        setBook(book, editedBook);

        return true;
    }

    /**
     * Mark the particular record returned in code base
     * Set Record to a new Record object with the dateReturned field filled
     * @param record new record carrying dateReturned field
     * @return the complete record
     */
    public Record markRecordAsReturned(Record record) {
        assert record != null;
        Record foundRecord = null;
        for (Record r : this.getRecordList()) {
            if (r.getBookBarcode().equals(record.getBookBarcode()) && !r.isReturned()) {
                foundRecord = r;
                break;
            }
        }
        assert foundRecord != null;
        Record updatedRecord = new Record(foundRecord.getBookName(), foundRecord.getBookBarcode(),
                foundRecord.getReaderName(), foundRecord.getDateBorrowed(),
                record.getDateReturned());
        setRecord(foundRecord, updatedRecord);

        return updatedRecord;
    }

}
