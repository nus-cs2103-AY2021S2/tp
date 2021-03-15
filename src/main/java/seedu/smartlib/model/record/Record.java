package seedu.smartlib.model.record;

import seedu.smartlib.commons.core.name.Name;

/**
 * Record of borrowing activity
 */
public class Record {


    private final Name bookName;
    private final Name readerName;
    // null when creating an returning record to mark existing record as returned
    private final DateBorrowed dateBorrowed;
    // null when creating an borrowing record, is a returned record when dateReturned non-null
    private DateReturned dateReturned;

    /**
     * Creates a borrowing record
     * @param bookName book that is borrowed
     * @param readerName reader who borrowed the book
     */
    public Record(Name bookName, Name readerName, DateReturned dateReturned) {
        this.bookName = bookName;
        this.readerName = readerName;
        this.dateBorrowed = null;
        this.dateReturned = dateReturned;
    }

    /**
     * Creates a borrowing record
     * @param bookName book that is borrowed
     * @param readerName reader who borrowed the book
     */
    public Record(Name bookName, Name readerName, DateBorrowed dateBorrowed) {
        this.bookName = bookName;
        this.readerName = readerName;
        this.dateBorrowed = dateBorrowed;
        this.dateReturned = null;
    }

    /**
     * Creates a borrowing record
     * @param bookName book that is borrowed
     * @param readerName reader who borrowed the book
     */
    public Record(Name bookName, Name readerName, DateBorrowed dateBorrowed, DateReturned dateReturned) {
        this.bookName = bookName;
        this.readerName = readerName;
        this.dateBorrowed = dateBorrowed;
        this.dateReturned = dateReturned;
    }

    /**
     * Mark the record as returned
     * @param dateReturned date that the book is returned
     */
    public void returnRecord(DateReturned dateReturned) {
        this.dateReturned = dateReturned;
    }

    public Name getBookName() {
        return bookName;
    }

    public Name getReaderName() {
        return readerName;
    }

    public DateBorrowed getDateBorrowed() {
        return dateBorrowed;
    }

    public DateReturned getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(DateReturned dateReturned) {
        this.dateReturned = dateReturned;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two records.
     * To facilitate checking when recording record
     * Whenever getDateReturned not equal or getDateBorrowed not equal,
     * they are not equal,
     * if any field missing,
     * then they are equal
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
        if (!otherRecord.getBookName().equals(this.getBookName())) {
            return false;
        }
        if (otherRecord.getDateReturned() != null && this.getDateReturned() != null) {
            return otherRecord.getDateReturned() == this.getDateReturned();
        }
        if (otherRecord.getDateBorrowed() != null && this.getDateBorrowed() != null) {
            return otherRecord.getDateBorrowed() == this.getDateBorrowed();
        }

        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other.getClass() != getClass()) {
            return false;
        }
        Record otherRecord = (Record) other;
        return isSameRecord(otherRecord);

    }

    @Override
    public String toString() {
        return this.getBookName() + " : " + this.getReaderName() + " : "
                + this.getDateBorrowed() + " : " + this.getDateReturned();
    }
}
