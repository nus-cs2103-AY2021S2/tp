package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.fee.MonthlyFee;

/**
 * An UI component that displays information of a {@code MonthlyFee}.
 */
public class MonthlyFeeCard extends UiPart<Region> {

    private static final String FXML = "MonthlyFeeListCard.fxml";

    @FXML
    private Label fee;
    @FXML
    private Label month;
    @FXML
    private Label year;

    /**
     * Creates a {@code MonthlyFeeCard} with the given {@code MonthlyFee}.
     */
    public MonthlyFeeCard(MonthlyFee monthlyFee) {
        super(FXML);
        fee.setText("$" + monthlyFee.getMonthlyFee());
        month.setText(monthlyFee.getMonth().getMonthName());
        year.setText(monthlyFee.getYear().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MonthlyFeeCard)) {
            return false;
        }

        // state check
        MonthlyFeeCard card = (MonthlyFeeCard) other;
        return fee.getText().equals(card.fee.getText())
            && month.equals(card.month)
            && year.equals(card.year);
    }
}
