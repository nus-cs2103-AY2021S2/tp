package seedu.cakecollate.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.cakecollate.model.orderitem.OrderItem;

public class OrderItemCard extends UiPart<Region> {
    private static final String FXML = "OrderItemCard.fxml";

    @FXML
    private Label index;
    @FXML
    private Label type;

    private final OrderItem orderItem;

    /**
     * Creates a {@code OrderCode} with the given {@code Order} and index to display.
     */
    public OrderItemCard(OrderItem orderItem, String displayedIndex) {
        super(FXML);
        this.orderItem = orderItem;
        index.setText(displayedIndex);
        type.setText(orderItem.getType().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof OrderCard)) {
            return false;
        }

        // state check
        OrderItemCard card = (OrderItemCard) other;
        return index.getText().equals(card.index.getText())
                && type.equals(card.type);
    }
}
