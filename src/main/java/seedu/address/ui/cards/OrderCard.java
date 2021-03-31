package seedu.address.ui.cards;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.customer.Customer;
import seedu.address.model.order.CompletedDate;
import seedu.address.model.order.Order;
import seedu.address.ui.UiPart;

/**
 * A UI component that displays the information of an {@code Order}.
 */
public class OrderCard extends UiPart<Region> {

    private static final String FXML = "OrderCard.fxml";

    public final Order order;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label cheeseTypeAndQuantity;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label quantity;
    @FXML
    private Label orderDate;
    @FXML
    private Label completedDate;

    /**
     * Creates an {@code OrderCard} with the given {@code Order} and index to display.
     */
    public OrderCard(Order order, Customer customer, int displayedIndex) {
        super(FXML);
        this.order = order;
        id.setText(displayedIndex + ". ");
        cheeseTypeAndQuantity.setText(order.getCheeseType().toString() + "   x " + order.getQuantity());
        name.setText(customer.getName().toString());
        phone.setText(customer.getPhone().toString());
        orderDate.setText("Order Date: " + order.getOrderDate().toString());
        completedDate.setText("Completed Date: " + order.getCompletedDate().map(CompletedDate::toString).orElse("-"));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof OrderCard)) {
            return false;
        }

        OrderCard card = (OrderCard) other;
        return id.getText().equals(card.id.getText()) && order.equals(card.order);
    }
}
