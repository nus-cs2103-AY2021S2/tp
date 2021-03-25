package seedu.smartlib.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.smartlib.model.book.Book;


/**
 * An UI component that displays information of a {@code Book}.
 */
public class BookCard extends UiPart<Region> {

    private static final String FXML = "BookListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Book book;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label author;
    @FXML
    private Label publisher;
    @FXML
    private Label isbn;
    @FXML
    private Label barcode;
    @FXML
    private Label genre;
    @FXML
    private FlowPane tags;
    @FXML
    private Label borrowerName;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public BookCard(Book book, int displayedIndex) {
        super(FXML);
        this.book = book;
        id.setText(displayedIndex + ". ");
        name.setText(book.getName().fullName);
        author.setText("Author: " + book.getAuthor().toString());
        publisher.setText("Publisher: " + book.getPublisher().toString());
        isbn.setText("ISBN: " + book.getIsbn().toString());
        barcode.setText("Barcode: " + book.getBarcode().toString());
        genre.setText("Genre: " + book.getGenre().toString());
        borrowerName.setText(book.isBorrowed() ? "Borrowed by: " + book.getBorrowerName().fullName : "Available");
        //Todo:
        //      book.getTags().stream()
        //                .sorted(Comparator.comparing(tag -> tag.tagName))
        //                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BookCard)) {
            return false;
        }

        // state check
        BookCard card = (BookCard) other;
        return id.getText().equals(card.id.getText())
                && book.equals(card.book);
    }
}

