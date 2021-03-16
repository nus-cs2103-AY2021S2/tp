package seedu.smartlib.storage;

import static seedu.smartlib.logic.commands.BorrowCommand.MESSAGE_DUPLICATE_RECORD;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.smartlib.commons.exceptions.IllegalValueException;
import seedu.smartlib.model.ReadOnlySmartLib;
import seedu.smartlib.model.SmartLib;
import seedu.smartlib.model.book.Book;
import seedu.smartlib.model.reader.Reader;
import seedu.smartlib.model.record.Record;

/**
 * An Immutable SmartLib that is serializable to JSON format.
 */
@JsonRootName(value = "smartlib")
class JsonSerializableSmartLib {

    public static final String MESSAGE_DUPLICATE_BOOK = "Books list contains duplicate book(s).";
    public static final String MESSAGE_DUPLICATE_READER = "Readers list contains duplicate reader(s).";

    private final List<JsonAdaptedBook> books = new ArrayList<>();
    private final List<JsonAdaptedReader> readers = new ArrayList<>();
    private final List<JsonAdaptedRecord> records = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableSmartLib} with the given books, readers and records.
     */
    @JsonCreator
    public JsonSerializableSmartLib(@JsonProperty("books") List<JsonAdaptedBook> books,
                                    @JsonProperty("readers") List<JsonAdaptedReader> readers,
                                    @JsonProperty("records") List<JsonAdaptedRecord> records) {
        this.books.addAll(books);
        this.readers.addAll(readers);
        this.records.addAll(records);
    }

    /**
     * Converts a given {@code ReadOnlySmartLib} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableSmartLib}.
     */
    public JsonSerializableSmartLib(ReadOnlySmartLib source) {
        books.addAll(source.getBookList().stream().map(JsonAdaptedBook::new).collect(Collectors.toList()));
        readers.addAll(source.getReaderList().stream().map(JsonAdaptedReader::new).collect(Collectors.toList()));
        records.addAll(source.getRecordList().stream().map(JsonAdaptedRecord::new).collect(Collectors.toList()));
    }

    /**
     * Converts this smartlib into the model's {@code SmartLib} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public SmartLib toModelType() throws IllegalValueException {
        SmartLib smartLib = new SmartLib();
        for (JsonAdaptedBook jsonAdaptedBook : books) {
            Book book = jsonAdaptedBook.toModelType();
            if (smartLib.hasBook(book)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_BOOK);
            }
            smartLib.addBook(book);
        }

        for (JsonAdaptedReader jsonAdaptedReader : readers) {
            Reader reader = jsonAdaptedReader.toModelType();
            if (smartLib.hasReader(reader)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_READER);
            }
            smartLib.addReader(reader);
        }

        for (JsonAdaptedRecord jsonAdaptedRecord : records) {
            Record record = jsonAdaptedRecord.toModelType();
            if (smartLib.hasRecord(record)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_RECORD);
            }
            smartLib.addRecord(record);
        }

        return smartLib;
    }

}
