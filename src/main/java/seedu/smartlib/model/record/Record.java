package seedu.smartlib.model.record;

import seedu.smartlib.commons.core.name.Name;

import java.time.LocalDate;
import java.util.Date;

/**
 * Record of borrowing activity
 */
public class Record {
    //Needs to update when reading internal memory,
    // read the largest record id and set overallId = largest record id + 1;
    private static int overallId = 0;

    private int recordId;
    private Name bookName;
    private Name readerName;
    private DateBorrowed dateBorrowed;

    /**
     * Creates a borrowing record
     * @param bookName book that is borrowed
     * @param readerName reader who borrowed the book
     */
    public Record(Name bookName, Name readerName, DateBorrowed dateBorrowed) {
        this.bookName = bookName;
        this.readerName = readerName;
        this.dateBorrowed = dateBorrowed;
    }


    public static int getOverallId() {
        return overallId;
    }

    public int getRecordId() {
        return recordId;
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

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameRecord(Record otherRecord) {
        if (otherRecord == this) {
            return true;
        }

        return otherRecord != null
                && otherRecord.getReaderName().equals(getReaderName())
                && otherRecord.getBookName().equals(getBookName())
                && otherRecord.getDateBorrowed().equals(getDateBorrowed());
    }
}
