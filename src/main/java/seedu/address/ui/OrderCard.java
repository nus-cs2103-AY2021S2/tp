package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.order.Order;

/**
 * An UI component that displays information of a {@code order}.
 */
public class OrderCard extends UiPart<Region> {

    private static final String FXML = "OrderListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */
    public final Order order;

    @FXML
    private HBox orderCardPane;
    @FXML
    private Label id;
    @FXML
    private Label status;
    @FXML
    private Label customerName;
    @FXML
    private Label dateTime;
    @FXML
    private Label dishes;
    @FXML
    private Label totalPrice;


    /**
     * Creates a {@code OrderCode} with the given {@code Order} and index to display.
     */
    public OrderCard(Order order, int displayedIndex) {
        super(FXML);
        this.order = order;
        id.setText(displayedIndex + ". ");
        status.setText("[" + order.getState().name() + "]");
        customerName.setText(order.getCustomer().getName());
        dateTime.setText("- " + order.getStrDatetime());
        dishes.setText(order.getDishesString());
        totalPrice.setText("Total price: SGD " + String.format("%.2f", order.getTotalPrice()));
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
        OrderCard card = (OrderCard) other;
        return id.getText().equals(card.id.getText())
                && order.equals(card.order);
    }
}
