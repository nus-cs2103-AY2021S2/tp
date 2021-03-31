package seedu.smartlib.testutil;

import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.model.book.Author;
import seedu.smartlib.model.book.Barcode;
import seedu.smartlib.model.book.Book;
import seedu.smartlib.model.book.Genre;
import seedu.smartlib.model.book.Isbn;
import seedu.smartlib.model.book.Publisher;

/**
 * A utility class to help with building Book objects.
 */
public class BookBuilder {

    public static final String DEFAULT_NAME = "Harry Potter and the Sorcerer Stone";
    public static final String DEFAULT_AUTHOR = "JK Rowling";
    public static final String DEFAULT_PUBLISHER = "Scholastic";
    public static final String DEFAULT_ISBN = "9780439708180";
    public static final String DEFAULT_BARCODE = "1234567890";
    public static final String DEFAULT_GENRE = "Fantasy";

    private Name name;
    private Author author;
    private Publisher publisher;
    private Isbn isbn;
    private Barcode barcode;
    private Genre genre;

    /**
     * Creates a {@code BookBuilder} with the default details.
     */
    public BookBuilder() {
        name = new Name(DEFAULT_NAME);
        author = new Author(new Name(DEFAULT_AUTHOR));
        publisher = new Publisher(new Name(DEFAULT_PUBLISHER));
        isbn = new Isbn(DEFAULT_ISBN);
        barcode = new Barcode(Integer.parseInt(DEFAULT_BARCODE));
        genre = new Genre(new Name(DEFAULT_GENRE));
    }

    /**
     * Initializes the BookBuilder with the data of {@code bookToCopy}.
     */
    public BookBuilder(Book bookToCopy) {
        name = bookToCopy.getName();
        author = bookToCopy.getAuthor();
        publisher = bookToCopy.getPublisher();
        isbn = bookToCopy.getIsbn();
        barcode = bookToCopy.getBarcode();
        genre = bookToCopy.getGenre();
    }

    /**
     * Sets the {@code Name} of the {@code Book} that we are building.
     */
    public BookBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Author} of the {@code Book} that we are building.
     */
    public BookBuilder withAuthor(String author) {
        this.author = new Author(new Name(author));
        return this;
    }

    /**
     * Sets the {@code Publisher} of the {@code Book} that we are building.
     */
    public BookBuilder withPublisher(String publisher) {
        this.publisher = new Publisher(new Name(publisher));
        return this;
    }

    /**
     * Sets the {@code Isbn} of the {@code Book} that we are building.
     */
    public BookBuilder withIsbn(String isbn) {
        this.isbn = new Isbn(isbn);
        return this;
    }

    /**
     * Sets the {@code Barcode} of the {@code Book} that we are building.
     */
    public BookBuilder withBarcode(String barcode) {
        this.barcode = new Barcode(Integer.parseInt(barcode));
        return this;
    }

    /**
     * Sets the {@code Genre} of the {@code Book} that we are building.
     */
    public BookBuilder withGenre(String genre) {
        this.genre = new Genre(new Name(genre));
        return this;
    }

    public Book build() {
        return new BookStub(name, author, publisher, isbn, barcode, genre);
    }

}
