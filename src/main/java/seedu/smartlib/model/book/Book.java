package seedu.smartlib.model.book;

import seedu.smartlib.commons.core.name.Name;

public class Book {

    private final Name name;
    private final Author author;
    private final Publisher publisher;
    private final Isbn isbn;

    Book(Name name, Author author, Publisher publisher, Isbn isbn) {
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
    }
}
