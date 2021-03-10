package seedu.budgetbaby.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.budgetbaby.model.record.FinancialRecord;

/**
 * An UI component that displays information of a {@code FinancialRecord}.
 */
public class FinancialRecordCard extends UiPart<Region> {

    private static final String FXML = "FinancialRecordListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final FinancialRecord financialRecord;

    @FXML
    private HBox cardPane;
    @FXML
    private Label description;
    @FXML
    private Label id;
    @FXML
    private Label amount;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code FinancialRecordCard} with the given {@code FinancialRecord} and index to display.
     */
    public FinancialRecordCard(FinancialRecord financialRecord, int displayedIndex) {
        super(FXML);
        this.financialRecord = financialRecord;
        id.setText(displayedIndex + ". ");
        description.setText(financialRecord.getDescription().toString());
        amount.setText("$" + financialRecord.getAmount().toString());
        // person.getTags().stream()
        //     .sorted(Comparator.comparing(tag -> tag.tagName))
        //     .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FinancialRecordCard)) {
            return false;
        }

        // state check
        FinancialRecordCard card = (FinancialRecordCard) other;
        return id.getText().equals(card.id.getText())
                && financialRecord.equals(card.financialRecord);
    }
}
