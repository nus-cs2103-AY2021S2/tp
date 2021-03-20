package seedu.smartlib.model.book;

import static seedu.smartlib.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.smartlib.commons.core.name.Name;

/**
 * Represents a book in SmartLib.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Book {

    // Identity fields
    private final Name name;

    // Data fields
    private final Author author;
    private final Publisher publisher;
    private final Isbn isbn;
    private final Name borrowerName;

    /**
     * Every field must be present and not null.
     */
    public Book(Name name, Author author, Publisher publisher, Isbn isbn) {
        requireAllNonNull(name, author, publisher, isbn);
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.borrowerName = null;
    }

    /**
     * Every field must be present and not null.
     */
    public Book(Name name, Author author, Publisher publisher, Isbn isbn, Name borrowerName) {
        requireAllNonNull(name, author, publisher, isbn);
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.borrowerName = borrowerName;
    }

    public Name getBorrowerName() {
        return this.borrowerName;
    }

    public boolean isBorrowed() {
        return this.borrowerName != null;
    }

    /**
     * Gets the Name of Book
     * @return Name of Book
     */
    public Name getName() {
        return name;
    }

    /**
     * Gets the Author of Book
     * @return Author of Book
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * Gets the Publisher of Book
     * @return Publisher of Book
     */
    public Publisher getPublisher() {
        return publisher;
    }

    /**
     * Gets the Isbn of Book
     * @return Isbn of Book
     */
    public Isbn getIsbn() {
        return isbn;
    }

    /**
     * Returns true if both books have the same name.
     * This defines a weaker notion of equality between two books.
     */
    public boolean isSameBook(Book otherBook) {
        if (otherBook == this) {
            return true;
        }

        return otherBook != null
                && otherBook.getName().equals(getName());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Book)) {
            return false;
        }

        Book otherBook = (Book) other;
        return otherBook.getName().equals(getName())
                && otherBook.getAuthor().equals(getAuthor())
                && otherBook.getPublisher().equals(getPublisher())
                && otherBook.getIsbn().equals(getIsbn());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, author, publisher, isbn);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Author: ")
                .append(getAuthor())
                .append("; Publisher: ")
                .append(getPublisher())
                .append("; Isbn: ")
                .append(getIsbn())
                .append("; Borrower: ")
                .append(isBorrowed() ? this.borrowerName : "Not borrowed.");

        return builder.toString();
    }
}
