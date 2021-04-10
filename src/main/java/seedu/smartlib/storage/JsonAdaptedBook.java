package seedu.smartlib.storage;

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
 * Jackson-friendly version of {@link Book}.
 */
class JsonAdaptedBook {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Book's %s field is missing!";

    private final String name;
    private final String author;
    private final String publisher;
    private final String isbn;
    private final String barcode;
    private final String genre;
    private final String borrowerName;
    private final String dateBorrowed;

    /**
     * Constructs a {@code JsonAdaptedBook} with the given book details.
     *
     * @param name name of the book.
     * @param author author of the book.
     * @param publisher publisher of the book.
     * @param isbn ISBN of the book.
     * @param barcode barcode of the book.
     * @param genre genre of the book.
     * @param borrowerName reader who borrowed the book.
     */
    @JsonCreator
    public JsonAdaptedBook(@JsonProperty("name") String name, @JsonProperty("author") String author,
                           @JsonProperty("publisher") String publisher, @JsonProperty("isbn") String isbn,
                           @JsonProperty("barcode") String barcode, @JsonProperty("genre") String genre,
                           @JsonProperty("borrowerName") String borrowerName,
                           @JsonProperty("dateBorrowed") String dateBorrowed) {
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.barcode = barcode;
        this.genre = genre;
        this.borrowerName = borrowerName;
        this.dateBorrowed = dateBorrowed;
    }

    /**
     * Converts a given {@code Book} into this class for Jackson use.
     *
     * @param source book to be converted.
     */
    public JsonAdaptedBook(Book source) {
        name = source.getName().toString();
        author = source.getAuthor().toString();
        publisher = source.getPublisher().toString();
        isbn = source.getIsbn().toString();
        barcode = source.getBarcode().toString();
        genre = source.getGenre().toString();
        borrowerName = source.getBorrowerName() == null ? null : source.getBorrowerName().toString();
        dateBorrowed = source.getDateBorrowed() == null ? null : source.getDateBorrowed().toString();
    }

    /**
     * Verifies validity of the book name.
     *
     * @throws IllegalValueException if the book name is null or invalid.
     */
    private void verifyBookName() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Verifies validity of the book author.
     *
     * @throws IllegalValueException if the book author is null or invalid.
     */
    private void verifyBookAuthor() throws IllegalValueException {
        if (author == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Author.class.getSimpleName()));
        }
        if (!Author.isValidAuthor(author)) {
            throw new IllegalValueException(Author.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Verifies validity of the book publisher.
     *
     * @throws IllegalValueException if the book publisher is null or invalid.
     */
    private void verifyBookPublisher() throws IllegalValueException {
        if (publisher == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Publisher.class.getSimpleName()));
        }
        if (!Publisher.isValidPublisher(publisher)) {
            throw new IllegalValueException(Publisher.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Verifies validity of the book isbn.
     *
     * @throws IllegalValueException if the book isbn is null or invalid.
     */
    private void verifyBookIsbn() throws IllegalValueException {
        if (isbn == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Isbn.class.getSimpleName()));
        }
        if (!Isbn.isValidIsbn(isbn)) {
            throw new IllegalValueException(Isbn.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Verifies validity of the book barcode.
     *
     * @throws IllegalValueException if the book barcode is null or invalid.
     */
    private void verifyBookBarcode() throws IllegalValueException {
        if (barcode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Barcode.class.getSimpleName()));
        }
        if (!Barcode.isValidBarcode(Integer.parseInt(barcode))) {
            throw new IllegalValueException(Isbn.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Verifies validity of the book genre.
     *
     * @throws IllegalValueException if the book genre is null or invalid.
     */
    private void verifyBookGenre() throws IllegalValueException {
        if (genre == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Genre.class.getSimpleName()));
        }
        if (!Genre.isValidGenre(genre)) {
            throw new IllegalValueException(Genre.MESSAGE_CONSTRAINTS);
        }
    }


    /**
     * Converts this Jackson-friendly adapted book object into the model's {@code Book} object.
     *
     * @return Book object converted from the storage file.
     * @throws IllegalValueException if there were any data constraints violated in the adapted book.
     */
    public Book toModelType() throws IllegalValueException {

        verifyBookName();
        final Name modelName = new Name(name);

        verifyBookAuthor();
        final Author modelAuthor = new Author(new Name(author));

        verifyBookPublisher();
        final Publisher modelPublisher = new Publisher(new Name(publisher));

        verifyBookIsbn();
        final Isbn modelIsbn = new Isbn(isbn);

        verifyBookBarcode();
        final Barcode modelBarcode = new Barcode(Integer.parseInt(barcode));

        verifyBookGenre();
        final Genre modelGenre = new Genre(new Name(genre));

        if (borrowerName == null) {
            return new Book(modelName, modelAuthor, modelPublisher, modelIsbn, modelBarcode, modelGenre);
        } else {
            final Name readerName = new Name(borrowerName);
            final DateBorrowed newDateBorrowed = new DateBorrowed(dateBorrowed);
            return new Book(modelName, modelAuthor, modelPublisher,
                    modelIsbn, modelBarcode, modelGenre, readerName, newDateBorrowed);
        }
    }

}
