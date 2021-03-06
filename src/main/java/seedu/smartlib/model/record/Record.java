package seedu.smartlib.model.record;

import java.time.LocalDate;

import seedu.smartlib.model.book.Book;
import seedu.smartlib.model.reader.Reader;

/**
 * Record of borrowing activity
 */
public class Record {
    //Needs to update when reading internal memory,
    // read the largest record id and set overallId = largest record id + 1;
    private static int overallId = 0;

    private int recordId;
    private Book book;
    private Reader reader;
    private LocalDate dateBorrowed;

    /**
     * Creates a borrowing record
     * @param book book that is borrowed
     * @param reader reader who borrowed the book
     */
    public Record(Book book, Reader reader) {
        this.book = book;
        this.reader = reader;
        this.dateBorrowed = LocalDate.now();
    }
}
