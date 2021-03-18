package seedu.address.ui.cards;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.cheese.Cheese;
import seedu.address.model.cheese.ExpiryDate;
import seedu.address.model.cheese.MaturityDate;
import seedu.address.ui.UiPart;

/**
 * A UI component that displays the information of a {@code Cheese}.
 */
public class CheeseCard extends UiPart<Region> {

    private static final String FXML = "CheeseCard.fxml";

    public final Cheese cheese;

    @FXML
    private HBox cardPane;
    @FXML
    private Label cheeseType;
    @FXML
    private Label id;
    @FXML
    private Label manufactureDate;
    @FXML
    private Label maturityDate;
    @FXML
    private Label expiryDate;

    /**
     * Creates a {@code CheeseCard} with the given {@code Cheese} and index to display
     */
    public CheeseCard(Cheese cheese, int displayedIndex) {
        super(FXML);
        this.cheese = cheese;
        id.setText(displayedIndex + ". ");
        cheeseType.setText(cheese.getCheeseType().toString());
        manufactureDate.setText("Manufacture Date: " + cheese.getManufactureDate().toString());
        maturityDate.setText("Maturity Date: " + cheese.getMaturityDate().map(MaturityDate::toString).orElse("-"));
        expiryDate.setText("Expiry Date: " + cheese.getExpiryDate().map(ExpiryDate::toString).orElse("-"));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof CheeseCard)) {
            return false;
        }

        CheeseCard card = (CheeseCard) other;
        return id.getText().equals(card.id.getText())
                && cheese.equals(card.cheese);
    }
}
