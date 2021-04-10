package seedu.smartlib.model.record;

import java.time.Duration;
import java.time.LocalDateTime;

import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.model.book.Barcode;

/**
 * The Record class represents a record of a reader's borrowing activity.
 */
public class Record {

    protected final Name bookName;
    protected final Barcode barcode;
    protected final Name readerName;
    // null when creating an returning record to mark existing record as returned
    protected final DateBorrowed dateBorrowed;
    // null when creating an borrowing record, is a returned record when dateReturned non-null
    protected final DateReturned dateReturned;

    /**
     * Creates a borrowing record.
     *
     * @param bookName bookname of the book that is borrowed.
     * @param barcode barcode of the book that is borrowed.
     * @param readerName reader who borrowed the book.
     * @param dateBorrowed borrow date of the book.
     */
    public Record(Name bookName, Barcode barcode, Name readerName, DateBorrowed dateBorrowed) {
        this.bookName = bookName;
        this.barcode = barcode;
        this.readerName = readerName;
        this.dateBorrowed = dateBorrowed;
        this.dateReturned = null;
    }

    /**
     * Creates a returning record.
     *
     * @param bookName bookname of the book that is borrowed.
     * @param barcode barcode of the book that is borrowed.
     * @param readerName reader who borrowed the book.
     * @param dateReturned return date of the book.
     */
    public Record(Name bookName, Barcode barcode, Name readerName, DateReturned dateReturned) {
        this.bookName = bookName;
        this.barcode = barcode;
        this.readerName = readerName;
        this.dateBorrowed = null;
        this.dateReturned = dateReturned;
    }

    /**
     * Creates a borrowing record.
     *
     * @param bookName bookname of the book that is borrowed.
     * @param barcode barcode of the book that is borrowed.
     * @param readerName reader who borrowed the book.
     * @param dateBorrowed borrow date of the book.
     * @param dateReturned return date of the book.
     */
    public Record(Name bookName, Barcode barcode, Name readerName,
                  DateBorrowed dateBorrowed, DateReturned dateReturned) {
        this.bookName = bookName;
        this.barcode = barcode;
        this.readerName = readerName;
        this.dateBorrowed = dateBorrowed;
        this.dateReturned = dateReturned;
    }
    /**
     * Indicates whether this book is returned to SmartLib.
     *
     * @return true if this book is returned to SmartLib, and false otherwise.
     */
    public boolean isReturned() {
        return this.dateReturned != null;
    }

    /**
     * Retrieves the bookName of the book associated with this record.
     *
     * @return the bookName of the book associated with this record.
     */
    public Name getBookName() {
        return bookName;
    }

    /**
     * Retrieves the barcode of the book associated with this record.
     *
     * @return the barcode of the book associated with this record.
     */
    public Barcode getBookBarcode() {
        return barcode;
    }

    /**
     * Retrieves the name of the reader associated with this record.
     *
     * @return the name of the reader associated with this record.
     */
    public Name getReaderName() {
        return readerName;
    }

    /**
     * Retrieves the borrow date noted in this record.
     *
     * @return the borrow date noted in this record.
     */
    public DateBorrowed getDateBorrowed() {
        return dateBorrowed;
    }

    /**
     * Retrieves the return date noted in this record.
     *
     * @return the return date noted in this record.
     */
    public DateReturned getDateReturned() {
        return dateReturned;
    }

    /**
     * Returns true if both records have the same details.
     * This defines a weaker notion of equality between two records, to facilitate checking when recording a record.
     * Whenever getDateReturned or getDateBorrowed are not equal, or if any field is missing, then they are equal.
     *
     * @param otherRecord the record to be compared with this record.
     * @return true if both records have the same details, and false otherwise.
     */
    public boolean isSameRecord(Record otherRecord) {
        if (otherRecord == this) {
            return true;
        }

        if (otherRecord == null) {
            return false;
        }

        if (!otherRecord.getReaderName().equals(this.getReaderName())) {
            return false;
        }

        if (!otherRecord.getBookBarcode().equals(this.getBookBarcode())) {
            return false;
        }

        if (otherRecord.getDateReturned() != null && this.getDateReturned() != null) {
            return otherRecord.getDateReturned().equals(this.getDateReturned());
        }

        if (otherRecord.getDateBorrowed() != null && this.getDateBorrowed() != null) {
            return otherRecord.getDateBorrowed().equals(this.getDateBorrowed());
        }

        return true;
    }

    /**
     * Gets the duration of the book being borrowed.
     *
     * @return A Duration class that represent the duration the book is borrowed.
     */
    public Duration getBorrowDuration() {
        assert dateBorrowed != null && dateReturned != null : "Date cannot be null";

        LocalDateTime startDate = LocalDateTime.parse(dateBorrowed.toString());
        LocalDateTime endDate = LocalDateTime.parse(dateReturned.toString());

        return Duration.between(startDate, endDate);
    }

    /**
     * Returns true if both records have the same identity and data fields.
     * This defines a stronger notion of equality between two records.
     *
     * @param other the record to be compared with this record.
     * @return true if both records have the same identity and data fields, and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Record)) {
            return false;
        }

        Record otherRecord = (Record) other;
        return isSameRecord(otherRecord);
    }

    /**
     * Returns this Record in String format.
     *
     * @return this Record in String format.
     */
    @Override
    public String toString() {
        return this.getBookBarcode() + " : " + this.getReaderName() + " : "
                + this.getDateBorrowed() + " : " + this.getDateReturned();
    }

}
