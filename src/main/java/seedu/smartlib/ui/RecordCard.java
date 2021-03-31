package seedu.smartlib.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.smartlib.model.record.Record;

/**
 * An UI component that displays information of a {@code Record}.
 */
public class RecordCard extends UiPart<Region> {

    private static final String FXML = "RecordListCard.fxml";

    /**
     * The record associated with this RecordCard class.
     */
    public final Record record;

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressrecord-level4/issues/336">The issue on AddressRecord level 4</a>
     */
    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label bookName;
    @FXML
    private Label barcode;
    @FXML
    private Label readerName;
    @FXML
    private Label dateBorrowed;
    @FXML
    private Label dateReturned;

    /**
     * Creates a {@code RecordCard} with the given {@code Record} and index to display.
     *
     * @param record record to be displayed.
     * @param displayedIndex index which the record is displayed at.
     */
    public RecordCard(Record record, int displayedIndex) {
        super(FXML);
        this.record = record;
        id.setText(displayedIndex + ". ");
        bookName.setText(record.getBookName().toString());
        barcode.setText("Barcode: " + record.getBookBarcode().toString());
        readerName.setText("Borrower: " + record.getReaderName().toString());
        dateBorrowed.setText("Borrowed on: "
                + LocalDateTime
                    .parse(record.getDateBorrowed().toString())
                    .format(DateTimeFormatter.ofPattern("d MMM yyyy (h.mm a)"))
        );
        dateReturned.setText(record.isReturned()
                ? "Returned on " + LocalDateTime
                    .parse(record.getDateReturned().toString())
                    .format(DateTimeFormatter.ofPattern("d MMM yyyy (h.mm a)"))
                : "Not returned yet");
    }

    /**
     * Checks if this RecordCard is equal to another RecordCard.
     *
     * @param other the other RecordCard to be compared.
     * @return true if this RecordCard is equal to the other RecordCard, and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RecordCard)) {
            return false;
        }

        // state check
        RecordCard card = (RecordCard) other;
        return id.getText().equals(card.id.getText())
                && record.equals(card.record);
    }

}

