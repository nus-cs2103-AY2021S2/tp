package seedu.address.ui;

import java.text.DecimalFormat;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.fee.MonthlyFee;

/**
 * An UI component that displays information of a {@code MonthlyFee}.
 */
public class MonthlyFeeCard extends UiPart<Region> {

    private static final String FXML = "MonthlyFeeListCard.fxml";

    // Used to round the fee to 2 decimal place
    private static DecimalFormat twoDecimalFormat = new DecimalFormat("0.00");

    @FXML
    private Label monthlyFeeLabel;
    @FXML
    private Label monthLabel;
    @FXML
    private Label yearLabel;

    /**
     * Creates a {@code MonthlyFeeCard} with the given {@code MonthlyFee}.
     */
    public MonthlyFeeCard(MonthlyFee monthlyFee) {
        super(FXML);
        monthlyFeeLabel.setText("$" + twoDecimalFormat.format(monthlyFee.getMonthlyFee()));
        monthLabel.setText(monthlyFee.getMonth().getMonthName());
        yearLabel.setText(monthlyFee.getYear().toString());
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
        return monthlyFeeLabel.getText().equals(card.monthlyFeeLabel.getText())
            && monthLabel.equals(card.monthLabel)
            && yearLabel.equals(card.yearLabel);
    }
}
