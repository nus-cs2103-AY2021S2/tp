package seedu.cakecollate.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.cakecollate.model.order.Order;

/**
 * An UI component that displays information of a {@code Order}.
 */
public class OrderCard extends UiPart<Region> {

    private static final String FXML = "OrderListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on CakeCollate level 4</a>
     */

    public final Order order;

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
    private FlowPane orderDescriptions;
    @FXML
    private Label deliveryDate;
    @FXML
    private Label deliveryStatus;
    @FXML
    private Label request;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code OrderCode} with the given {@code Order} and index to display.
     */
    public OrderCard(Order order, int displayedIndex) {
        super(FXML);
        this.order = order;
        id.setText(displayedIndex + ". ");
        name.setText(order.getName().fullName);
        phone.setText(order.getPhone().value);
        address.setText(order.getAddress().value);
        email.setText(order.getEmail().value);
        order.getOrderDescriptions().stream()
                .sorted(Comparator.comparing(orderDescription -> orderDescription.value))
                .forEach(orderDescription -> orderDescriptions.getChildren().add(new Label(orderDescription.value)));
        order.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        deliveryDate.setText(order.getDeliveryDate().toString());
        deliveryStatus.setText(order.getDeliveryStatus().toString());
        request.getStyleClass();
        request.setText(order.getRequest().toString());
        setDeliveryStatusStyle();
    }

    public void setDeliveryStatusStyle() {
        deliveryStatus.getStyleClass().add("cell_deliveryStatus_label_" + order.getDeliveryStatus().toString());
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
