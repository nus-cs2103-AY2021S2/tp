package seedu.smartlib.testutil;

import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.model.book.Author;
import seedu.smartlib.model.book.Barcode;
import seedu.smartlib.model.book.Book;
import seedu.smartlib.model.book.Genre;
import seedu.smartlib.model.book.Isbn;
import seedu.smartlib.model.book.Publisher;
import seedu.smartlib.model.record.DateBorrowed;

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
    private Name borrowerName;
    private DateBorrowed dateBorrowed;

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
     *
     * @param bookToCopy a Book object containing data which we want to copy from.
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
     *
     * @param name name of the Book that we are building.
     * @return a BookBuilder object with the updated name.
     */
    public BookBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Author} of the {@code Book} that we are building.
     *
     * @param author author of the Book that we are building.
     * @return a BookBuilder object with the updated author.
     */
    public BookBuilder withAuthor(String author) {
        this.author = new Author(new Name(author));
        return this;
    }

    /**
     * Sets the {@code Publisher} of the {@code Book} that we are building.
     *
     * @param publisher publisher of the Book that we are building.
     * @return a BookBuilder object with the updated publisher.
     */
    public BookBuilder withPublisher(String publisher) {
        this.publisher = new Publisher(new Name(publisher));
        return this;
    }

    /**
     * Sets the {@code Isbn} of the {@code Book} that we are building.
     *
     * @param isbn ISBN of the Book that we are building.
     * @return a BookBuilder object with the updated ISBN.
     */
    public BookBuilder withIsbn(String isbn) {
        this.isbn = new Isbn(isbn);
        return this;
    }

    /**
     * Sets the {@code Barcode} of the {@code Book} that we are building.
     *
     * @param barcode barcode of the Book that we are building.
     * @return a BookBuilder object with the updated Barcode.
     */
    public BookBuilder withBarcode(String barcode) {
        this.barcode = new Barcode(Integer.parseInt(barcode));
        return this;
    }

    /**
     * Sets the {@code Genre} of the {@code Book} that we are building.
     *
     * @param genre genre of the Book that we are building.
     * @return a BookBuilder object with the updated Genre.
     */
    public BookBuilder withGenre(String genre) {
        this.genre = new Genre(new Name(genre));
        return this;
    }

    /**
     * Sets the {@code borrowerName} of the {@code Book} that we are building.
     *
     * @param borrowerName borrowerName of the Book that we are building.
     * @return a BookBuilder object with the updated borrowerName.
     */
    public BookBuilder withBorrowerName(String borrowerName) {
        this.borrowerName = new Name(borrowerName);
        return this;
    }

    /**
     * Sets the {@code dateBorrowed} of the {@code Book} that we are building.
     *
     * @param dateBorrowed dateBorrowed of the Book that we are building.
     * @return a BookBuilder object with the updated dateBorrowed.
     */
    public BookBuilder withDateBorrowed(String dateBorrowed) {
        this.dateBorrowed = new DateBorrowed(dateBorrowed);
        return this;
    }

    /**
     * Builds a Book as a BookStub object with the given values.
     *
     * @return a BookStub object with the given parameters.
     */
    public Book build() {
        if (borrowerName != null && dateBorrowed != null) {
            return new BookStub(name, author, publisher, isbn, barcode, genre, borrowerName, dateBorrowed);
        } else {
            return new BookStub(name, author, publisher, isbn, barcode, genre);
        }
    }

}
