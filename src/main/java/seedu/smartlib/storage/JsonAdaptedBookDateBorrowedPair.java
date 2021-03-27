package seedu.smartlib.storage;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.commons.exceptions.IllegalValueException;
import seedu.smartlib.model.book.Author;
import seedu.smartlib.model.book.Barcode;
import seedu.smartlib.model.book.Book;
import seedu.smartlib.model.book.Genre;
import seedu.smartlib.model.book.Isbn;
import seedu.smartlib.model.book.Publisher;
import seedu.smartlib.model.record.DateBorrowed;

/**
 * Jackson-friendly version of {@link Book DateBorrowed Pair}.
 */
class JsonAdaptedBookDateBorrowedPair {

    private static final String MESSAGE_CONSTRAINTS = "NameDateBorrowedPair creation exception";
    private final List<String> book;
    private final String dateBorrowed;

    /**
     * Constructs a {@code JsonAdaptedBookDateBorrowedPair} with the given {@code NameDateBorrowedPair}.
     */
    @JsonCreator
    public JsonAdaptedBookDateBorrowedPair(@JsonProperty("book") List<String> book,
                                           @JsonProperty("dateBorrowed") String dateBorrowed) {
        this.book = book;
        this.dateBorrowed = dateBorrowed;
    }

    /**
     * Converts a given {@code NameDateBorrowedPair} into this class for Jackson use.
     */
    public JsonAdaptedBookDateBorrowedPair(Map.Entry<Book, DateBorrowed> entry) {
        Book book = entry.getKey();
        this.book = new ArrayList<>();
        this.book.add(book.getName().toString());
        this.book.add(book.getAuthor().toString());
        this.book.add(book.getPublisher().toString());
        this.book.add(book.getIsbn().toString());
        this.book.add(book.getBarcode().toString());
        this.book.add(book.getGenre().toString());
        this.dateBorrowed = entry.getValue().getValue();
    }

    /**
     * Converts a given {@code NameDateBorrowedPair} into this class for Jackson use.
     */
    public JsonAdaptedBookDateBorrowedPair(Book book, DateBorrowed dateBorrowed) {
        this.book = new ArrayList<>();
        this.book.add(book.getName().toString());
        this.book.add(book.getAuthor().toString());
        this.book.add(book.getPublisher().toString());
        this.book.add(book.getIsbn().toString());
        this.book.add(book.getBarcode().toString());
        this.book.add(book.getGenre().toString());
        this.dateBorrowed = dateBorrowed.getValue();
    }

    /**
     * Converts this Jackson-friendly adapted NameDateBorrowedPair
     * object into the model's {@code NameDateBorrowedPair} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted NameDateBorrowedPair.
     */
    public Map.Entry<Book, DateBorrowed> toModelType() throws IllegalValueException {
        if (!DateBorrowed.isValidDate(dateBorrowed)) {
            throw new IllegalValueException(MESSAGE_CONSTRAINTS);
        }
        return new AbstractMap.SimpleEntry<>(
                new Book(new Name(book.get(0)),
                        new Author(new Name(book.get(1))),
                        new Publisher(new Name(book.get(2))),
                        new Isbn(book.get(3)),
                        new Barcode(Integer.parseInt(book.get(4))),
                        new Genre(new Name(book.get(5)))
                ), // there is no need to store details of the reader who borrowed the book here
                new DateBorrowed(dateBorrowed)
        );
    }

}
