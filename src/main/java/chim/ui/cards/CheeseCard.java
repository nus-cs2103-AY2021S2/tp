package chim.ui.cards;

import chim.model.cheese.Cheese;
import chim.model.cheese.ExpiryDate;
import chim.ui.UiPart;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

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
    private Label status;
    @FXML
    private Label manufactureDate;
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
        status.setText("Assigned: " + (cheese.isCheeseAssigned() ? "Yes" : "No"));
        manufactureDate.setText("Manufacture Date: " + cheese.getManufactureDate().toString());
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
