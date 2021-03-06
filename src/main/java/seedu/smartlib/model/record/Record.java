package seedu.smartlib.model.record;

import seedu.smartlib.model.book.Book;
import seedu.smartlib.model.reader.Reader;

import java.time.LocalDate;

public class Record {
    static int overallId = 0; //Needs to update when reading internal memory, read the largest record id and set overallId = largest record id + 1;
    int recordId;
    Book book;
    Reader reader;
    LocalDate dateBorrowed;


    public Record(Book book, Reader reader) {
        this.book = book;
        this.reader = reader;
        this.dateBorrowed = LocalDate.now();
    }


}