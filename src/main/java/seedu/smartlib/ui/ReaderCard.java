package seedu.smartlib.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.smartlib.model.reader.Reader;
import seedu.smartlib.model.tag.Tag;

/**
 * An UI component that displays information of a {@code Reader}.
 */
public class ReaderCard extends UiPart<Region> {

    private static final String FXML = "ReaderListCard.fxml";

    /**
     * The reader associated with this ReaderCard class.
     */
    public final Reader reader;

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */
    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private HBox tags;
    @FXML
    private VBox borrows;

    /**
     * Creates a {@code ReaderCard} with the given {@code Reader} and index to display.
     *
     * @param reader reader to be displayed.
     * @param displayedIndex index which the reader is displayed at.
     */
    public ReaderCard(Reader reader, int displayedIndex) {
        super(FXML);
        this.reader = reader;
        id.setText(displayedIndex + ". ");
        name.setText(reader.getName().toString());
        phone.setText(reader.getPhone().toString());
        address.setText(reader.getAddress().toString());
        email.setText(reader.getEmail().toString());
        reader.getTags().stream()
                .sorted(Comparator.comparing(Tag::getTagName))
                .forEach(tag -> {
                    Label l = new Label(tag.getTagName());
                    l.setWrapText(true);
                    tags.getChildren().add(l);
                });
        reader.getBorrows().forEach((key, value) -> {
            Label l = new Label(key.getName().toString() + ", borrowed: "
                    + LocalDateTime.parse(value.toString()).format(DateTimeFormatter.ofPattern("d MMM yyyy"))
                    + ".");
            l.setWrapText(true);
            borrows.getChildren().add(l);
        });
    }

    /**
     * Checks if this ReaderCard is equal to another ReaderCard.
     *
     * @param other the other ReaderCard to be compared.
     * @return true if this ReaderCard is equal to the other ReaderCard, and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ReaderCard)) {
            return false;
        }

        // state check
        ReaderCard card = (ReaderCard) other;
        return id.getText().equals(card.id.getText()) && reader.equals(card.reader);
    }

}
