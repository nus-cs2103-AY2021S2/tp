package seedu.smartlib.model;

import javafx.collections.ObservableList;
import seedu.smartlib.model.book.Book;
import seedu.smartlib.model.reader.Reader;
import seedu.smartlib.model.record.Record;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlySmartLib {

    /**
     * Returns an unmodifiable view of the books list.
     * This list will not contain any duplicate books.
     */
    ObservableList<Book> getBookList();

    /**
     * Returns an unmodifiable view of the readers list.
     * This list will not contain any duplicate readers.
     */
    ObservableList<Reader> getReaderList();

    /**
     * Returns an unmodifiable view of the record list.
     * This list will not contain any duplicate records.
     */
    ObservableList<Record> getRecordList();


}
